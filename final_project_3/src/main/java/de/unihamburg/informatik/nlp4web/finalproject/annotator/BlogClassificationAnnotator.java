package de.unihamburg.informatik.nlp4web.finalproject.annotator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.cleartk.ml.CleartkAnnotator;
import org.cleartk.ml.CleartkProcessingException;
import org.cleartk.ml.Instance;
import org.cleartk.ml.feature.extractor.*;
import org.cleartk.ml.feature.transform.InstanceStream;
import org.cleartk.ml.feature.transform.extractor.CentroidTfidfSimilarityExtractor;
import org.cleartk.ml.feature.transform.extractor.MinMaxNormalizationExtractor;
import org.cleartk.ml.feature.transform.extractor.TfidfExtractor;
import org.cleartk.ml.libsvm.LibSvmStringOutcomeDataWriter;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unihamburg.informatik.nlp4web.finalproject.feature.CountAnnotationExtractor;
import de.unihamburg.informatik.nlp4web.finalproject.feature.DictionaryMappingExtractor;
import de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.apache.uima.fit.util.JCasUtil.select;

public class BlogClassificationAnnotator extends CleartkAnnotator<String> {
    public static final String PARAM_TF_IDF_URI = "tfIdfUri";
    public static final String PARAM_TF_IDF_CENTROID_SIMILARITY_URI = "tfIdfCentroidSimilarityUri";
    public static final String PARAM_ZMUS_URI = "zmusUri";
    public static final String PARAM_MINMAX_URI = "minmaxUri";
    public static final String PARAM_DIRECTORY_NAME = "modelOutputDir";
    public static final String TFIDF_EXTRACTOR_KEY = "Token";
    public static final String CENTROID_TFIDF_SIM_EXTRACTOR_KEY = "CentroidTfIdfSimilarity";
    public static final String MINMAX_EXTRACTOR_KEY = "MINMAXFeatures";
    @ConfigurationParameter(
            name = PARAM_TF_IDF_URI,
            mandatory = false,
            description = "provides a URI where the tf*idf map will be written")
    protected URI tfIdfUri;
    @ConfigurationParameter(
            name = PARAM_TF_IDF_CENTROID_SIMILARITY_URI,
            mandatory = false,
            description = "provides a URI where the tf*idf centroid data will be written")
    protected URI tfIdfCentroidSimilarityUri;

    @ConfigurationParameter(
            name = PARAM_MINMAX_URI,
            mandatory = false,
            description = "provides a URI where the min-max feature normalizaation data will be written")
    protected URI minmaxUri;

    @ConfigurationParameter(name = PARAM_DIRECTORY_NAME,
            mandatory = false)
    private File modelOutputDir;
    private CombinedExtractor1<BlogAnnotation> extractor;

    public static URI createTokenTfIdfDataURI(File outputDirectoryName) {
        File f = new File(outputDirectoryName, TFIDF_EXTRACTOR_KEY + "_tfidf_extractor.dat");
        return f.toURI();
    }

    public static URI createIdfCentroidSimilarityDataURI(File outputDirectoryName) {
        File f = new File(outputDirectoryName, CENTROID_TFIDF_SIM_EXTRACTOR_KEY);
        return f.toURI();
    }

    public static URI createMinMaxDataURI(File outputDirectoryName) {
        File f = new File(outputDirectoryName, MINMAX_EXTRACTOR_KEY + "_minmax_extractor.dat");
        return f.toURI();
    }

    List<FeatureExtractor1<BlogAnnotation>> features = new ArrayList<>();



    List<FeatureExtractor1<Token>> tokenFeatures = new ArrayList<>();


    @Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        super.initialize(context);
        // add feature extractors
        try {

            TfidfExtractor<String, BlogAnnotation> tfIdfExtractor = initTfIdfExtractor();

            CentroidTfidfSimilarityExtractor<String, BlogAnnotation> simExtractor = initCentroidTfIdfSimilarityExtractor();
            MinMaxNormalizationExtractor<String, BlogAnnotation> minmaxExtractor = initMinMaxExtractor();

            /** Collecting all features in a CombinedExtractor1<T> **/
            this.extractor = new CombinedExtractor1<BlogAnnotation>(
                    tfIdfExtractor,
                    simExtractor,
                    minmaxExtractor
                    );

        } catch (IOException e) {
            throw new ResourceInitializationException(e);
        }

    }

    private TfidfExtractor<String, BlogAnnotation> initTfIdfExtractor() throws IOException {
        CleartkExtractor<BlogAnnotation, Token> countsExtractor = new CleartkExtractor<BlogAnnotation, Token>(
                Token.class,
                new CoveredTextExtractor<Token>(),
                new CleartkExtractor.Count(new CleartkExtractor.Covered()));

        TfidfExtractor<String, BlogAnnotation> tfIdfExtractor = new TfidfExtractor<String, BlogAnnotation>(
                BlogClassificationAnnotator.TFIDF_EXTRACTOR_KEY,
                countsExtractor);

        if (this.tfIdfUri != null) {
            tfIdfExtractor.load(this.tfIdfUri);
        }
        return tfIdfExtractor;
    }


    private CentroidTfidfSimilarityExtractor<String, BlogAnnotation> initCentroidTfIdfSimilarityExtractor()
            throws IOException {
        CleartkExtractor<BlogAnnotation, Token> countsExtractor = new CleartkExtractor<BlogAnnotation, Token>(
                Token.class,
                new CoveredTextExtractor<Token>(),
                new CleartkExtractor.Count(new CleartkExtractor.Covered()));

        CentroidTfidfSimilarityExtractor<String, BlogAnnotation> simExtractor = new CentroidTfidfSimilarityExtractor<String, BlogAnnotation>(
                BlogClassificationAnnotator.CENTROID_TFIDF_SIM_EXTRACTOR_KEY,
                countsExtractor);

        if (this.tfIdfCentroidSimilarityUri != null) {
            simExtractor.load(this.tfIdfCentroidSimilarityUri);
        }
        return simExtractor;
    }


    private MinMaxNormalizationExtractor<String, BlogAnnotation> initMinMaxExtractor()
            throws IOException {
        CombinedExtractor1<BlogAnnotation> featuresToNormalizeExtractor = new CombinedExtractor1<BlogAnnotation>(
                new CountAnnotationExtractor<BlogAnnotation>(Sentence.class),
                new CountAnnotationExtractor<BlogAnnotation>(Token.class),
                new DictionaryMappingExtractor<BlogAnnotation>(Sentence.class));

        MinMaxNormalizationExtractor<String, BlogAnnotation> minmaxExtractor = new MinMaxNormalizationExtractor<String, BlogAnnotation>(
                MINMAX_EXTRACTOR_KEY,
                featuresToNormalizeExtractor);

        if (this.minmaxUri != null) {
            minmaxExtractor.load(this.minmaxUri);
        }

        return minmaxExtractor;
    }

    /**
     * Recursively going through all annotated news.
     * During training we write each news instance in the modelOutputDir
     * alone with its gold value. The written instances are then
     * read by the collectFeatures method to transform and train the data.
     **/

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        System.err.println("extracting");
        for (BlogAnnotation news : select(jCas, BlogAnnotation.class)) {
            Instance<String> instance = new Instance<String>();

            instance.addAll(this.extractor.extract(jCas, news));
            
            if (this.isTraining()) {
                instance.setOutcome(news.getGoldValue());
                this.dataWriter.write(instance);

            } else {
                String result = this.classifier.classify(instance.getFeatures());
                news.setPredictValue(result);
                news.addToIndexes();

            }
        }
        if (this.isTraining()) {

            try {
                this.collectFeatures(this.modelOutputDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Transform features and write training data
     * In this phase, the normalization statistics are computed and the raw
     * features are transformed into normalized features.
     * Then the adjusted values are written with a DataWriter (libsvm in this case)
     * for training
     **/
    public void collectFeatures(File outputDirectory) throws IOException, CleartkProcessingException {

        Iterable<Instance<String>> instances = InstanceStream.loadFromDirectory(outputDirectory);

        System.err.println("Collection feature normalization statistics");
        System.err.println("tfIDF ...");
        /** Collect TF*IDF stats for computing tf*idf values on extracted tokens **/
        URI tfIdfDataURI = BlogClassificationAnnotator.createTokenTfIdfDataURI(outputDirectory);
        TfidfExtractor<String, BlogAnnotation> extractor = new TfidfExtractor<String, BlogAnnotation>(
                BlogClassificationAnnotator.TFIDF_EXTRACTOR_KEY);
        extractor.train(instances);
        extractor.save(tfIdfDataURI);

         System.err.println("tfIDF Done");
         System.err.println("similarity to corpus centroid ...");
        /** Collect TF*IDF Centroid stats for computing similarity to corpus centroid **/
        URI tfIdfCentroidSimDataURI = BlogClassificationAnnotator.createIdfCentroidSimilarityDataURI(outputDirectory);
        CentroidTfidfSimilarityExtractor<String, BlogAnnotation> simExtractor = new CentroidTfidfSimilarityExtractor<String, BlogAnnotation>(
                BlogClassificationAnnotator.CENTROID_TFIDF_SIM_EXTRACTOR_KEY);
        simExtractor.train(instances);
        simExtractor.save(tfIdfCentroidSimDataURI);

        System.err.println("similarity to corpus centroid Done");
        
        System.err.println("MinMax stats for feature normalization ...");
        /** Collect MinMax stats for feature normalization **/
        URI minmaxDataURI = BlogClassificationAnnotator.createMinMaxDataURI(outputDirectory);
        MinMaxNormalizationExtractor<String, BlogAnnotation> minmaxExtractor = new MinMaxNormalizationExtractor<String, BlogAnnotation>(
                BlogClassificationAnnotator.MINMAX_EXTRACTOR_KEY);
        minmaxExtractor.train(instances);
        minmaxExtractor.save(minmaxDataURI);
        System.err.println("MinMax stats for feature normalization Done");
        /** Rerun training data writer pipeline, to transform the extracted instances -- an alternative,
         * more costly approach would be to reinitialize the DocumentClassificationAnnotator above with
         * the URIs for the feature extractor.
         * In this example, we now write in the libsvm format **/

        System.err.println("Write out model training data");
        LibSvmStringOutcomeDataWriter dataWriter = new LibSvmStringOutcomeDataWriter(outputDirectory);
        for (Instance<String> instance : instances) {
            instance = extractor.transform(instance);
            instance = simExtractor.transform(instance);
            instance = minmaxExtractor.transform(instance);
            dataWriter.write(instance);
        }
        dataWriter.finish();
    }
}


