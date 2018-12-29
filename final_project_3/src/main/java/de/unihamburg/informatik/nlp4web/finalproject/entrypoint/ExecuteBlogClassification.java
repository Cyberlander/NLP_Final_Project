package de.unihamburg.informatik.nlp4web.finalproject.entrypoint;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.testing.util.HideOutput;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.cleartk.ml.feature.transform.InstanceDataWriter;
import org.cleartk.ml.jar.DefaultDataWriterFactory;
import org.cleartk.ml.jar.DirectoryDataWriterFactory;
import org.cleartk.ml.jar.GenericJarClassifierFactory;
import org.cleartk.ml.jar.JarClassifierBuilder;
import org.cleartk.util.cr.FilesCollectionReader;

import de.unihamburg.informatik.nlp4web.finalproject.annotator.BlogClassificationAnnotator;
import de.unihamburg.informatik.nlp4web.finalproject.reader.BlogReader;
import de.unihamburg.informatik.nlp4web.finalproject.writer.BlogClassificationAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

public class ExecuteBlogClassification {
	
	
	private static final String TRAIN_DATA_PATH = "src/main/resources/blog_entries/train_data_1000.csv";
	private static final String TEST_DATA_PATH = "src/main/resources/blog_entries/test_data_1000.csv";

    public static void writeModel(File saTrain, File modelDirectory, File stopwordsFile)
            throws ResourceInitializationException, UIMAException, IOException {
        System.err.println("Step 1: Extracting features and writing raw instances data");

        runPipeline(
                FilesCollectionReader.getCollectionReaderWithSuffixes(saTrain.getAbsolutePath(),
                        BlogReader.CONLL_VIEW, saTrain.getName()),
                createEngine(BlogReader.class, BlogReader.PARAM_STOP_WORDS, stopwordsFile),
                createEngine(AnalysisEngineFactory.createEngineDescription(
                        BlogClassificationAnnotator.class,
                        BlogClassificationAnnotator.PARAM_IS_TRAINING, true,
                        BlogClassificationAnnotator.PARAM_DIRECTORY_NAME, modelDirectory,
                        DefaultDataWriterFactory.PARAM_DATA_WRITER_CLASS_NAME,
                        InstanceDataWriter.class.getName(),
                        DirectoryDataWriterFactory.PARAM_OUTPUT_DIRECTORY,
                        modelDirectory))

        );
    }

    public static void trainModel(File modelDirectory, String[] arguments) throws Exception {
        /** Stage 3: Train and write model
         * Now that the features have been extracted and normalized, we can proceed
         *in running machine learning to train and package a model **/

        System.err.println("Train model and write model.jar file.");
        HideOutput hider = new HideOutput();
        JarClassifierBuilder.trainAndPackage(modelDirectory, arguments);
        hider.restoreOutput();
    }

    public static void classifyTestFile(File modelDirectory, File saTest, File result, File stopwordsFile)
            throws ResourceInitializationException, UIMAException, IOException {

        runPipeline(
                FilesCollectionReader.getCollectionReaderWithSuffixes(saTest.getAbsolutePath(),
                        BlogReader.CONLL_VIEW, saTest.getName()),
                createEngine(BlogReader.class, BlogReader.PARAM_STOP_WORDS, stopwordsFile),
                createEngine(AnalysisEngineFactory.createEngineDescription(
                        BlogClassificationAnnotator.class,
                        BlogClassificationAnnotator.PARAM_IS_TRAINING, false,
                        BlogClassificationAnnotator.PARAM_DIRECTORY_NAME, modelDirectory,
                        BlogClassificationAnnotator.PARAM_TF_IDF_URI,
                        BlogClassificationAnnotator.createTokenTfIdfDataURI(modelDirectory),
                        BlogClassificationAnnotator.PARAM_TF_IDF_CENTROID_SIMILARITY_URI,
                        BlogClassificationAnnotator.createIdfCentroidSimilarityDataURI(modelDirectory),
                        BlogClassificationAnnotator.PARAM_MINMAX_URI,
                        BlogClassificationAnnotator.createMinMaxDataURI(modelDirectory),
                        GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH, modelDirectory + "/model.jar")
                ),
                createEngine(BlogClassificationAnalyzer.class, BlogClassificationAnalyzer.PARAM_OUTPUT_DIR, result.getAbsolutePath()));

    }

    public static void main(String[] args) throws Exception {

        String[] trainingArguments = new String[]{"-t", "0"};
        long start = System.currentTimeMillis();
        
   ;
     
        File trainDataFile = new File( TRAIN_DATA_PATH );
		File testDataFile = new File( TEST_DATA_PATH );
		
		
        String modelPath = "src/test/resources/model/";
        new File(modelPath).mkdirs();
        File modelDir = new File(modelPath);
        
        String resltPath = "src/test/resources/results/";
        new File(resltPath).mkdirs();
        File resultDir = new File(resltPath+"news.csv");

        //File newsTrain = new File("src/main/resources/news/uci-news-aggregator.csv.train.small");
        //File newsTest = new File("src/main/resources/news/uci-news-aggregator.csv.test.small");
        
        File stopWordsFile = new File("src/main/resources/stopwords.txt");
       
        writeModel(trainDataFile, modelDir, stopWordsFile);
        trainModel(modelDir, trainingArguments);
        classifyTestFile(modelDir, testDataFile, resultDir, stopWordsFile);
        long now = System.currentTimeMillis();
        UIMAFramework.getLogger().log(Level.INFO, "Time: " + (now - start) + "ms");
    }

}
