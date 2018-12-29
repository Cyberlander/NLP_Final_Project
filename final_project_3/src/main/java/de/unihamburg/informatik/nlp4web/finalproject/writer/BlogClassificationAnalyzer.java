package de.unihamburg.informatik.nlp4web.finalproject.writer;

import org.apache.commons.io.IOUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

import de.unihamburg.informatik.nlp4web.finalproject.type.NewsAnnotation;

import java.io.*;

import static org.apache.uima.fit.util.JCasUtil.select;

public class BlogClassificationAnalyzer extends JCasAnnotator_ImplBase {
    Logger logger = UIMAFramework.getLogger(BlogClassificationAnalyzer.class);

    public static final String PARAM_OUTPUT_DIR = "InputFile";

    @ConfigurationParameter(name = PARAM_OUTPUT_DIR, mandatory = true)
    private String outputDir;

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        int tot = 0;
        int corr = 0;
        try {
            FileOutputStream result = new FileOutputStream(outputDir);
            IOUtils.write("TITLE\tGOLD\tPREDICTED\n", result,"UTF-8");
            for (NewsAnnotation news : select(jCas, NewsAnnotation.class)) {
                
                String predicted = news.getPredictValue();
                String gold = news.getGoldValue();
                if (predicted.equals(gold)) {
                    corr++;
                }
                tot++;
                IOUtils.write(news.getTitle() +"\t"+ gold +"\t"+predicted+"\n", result,"UTF-8");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        double acc = 100 * ((double) corr / tot);

        logger.log(Level.INFO, "Total news " + tot);
        logger.log(Level.INFO, "Total correct classifications " + corr);
        logger.log(Level.INFO, "Total incorrect classifications " + (tot - corr));
        logger.log(Level.INFO, "Accuracy " + String.format("%1$,.2f", acc) + "%");

    }

}
