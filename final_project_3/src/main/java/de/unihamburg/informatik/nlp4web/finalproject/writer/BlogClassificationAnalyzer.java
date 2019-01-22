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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import static org.apache.uima.fit.util.JCasUtil.select;

public class BlogClassificationAnalyzer extends JCasAnnotator_ImplBase {
    Logger logger = UIMAFramework.getLogger(BlogClassificationAnalyzer.class);

    public static final String PARAM_OUTPUT_DIR = "InputFile";

    @ConfigurationParameter(name = PARAM_OUTPUT_DIR, mandatory = true)
    private String outputDir;

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
    	int tot = 0;
    	List<String> expectedLabels = new ArrayList<String>();
    	List<String> actualLabels = new ArrayList<String>();
    	int corr = 0;

    	try {
    		FileOutputStream result = new FileOutputStream(outputDir);
    		IOUtils.write("TITLE\tGOLD\tPREDICTED\n", result,"UTF-8");
	    	for (NewsAnnotation news : select(jCas, NewsAnnotation.class)) {
	
		    	String predicted = news.getPredictValue();
		    	actualLabels.add(predicted);
		    	String gold = news.getGoldValue();
		    	expectedLabels.add(gold);
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
    	
    	HashSet<String> hset = new HashSet<String>(expectedLabels);
        List<String> unique = new ArrayList<String>(hset);            
    	
    	int[][] cm = confusionMatrix(unique, expectedLabels, actualLabels);
    	printEvaluation(cm, unique);
    	
    	String resltPath = "src/test/resources/results/";
    	new File(resltPath).mkdirs();
    	File resultDir = new File(resltPath);
        
        /*Result results = new Result();
        results.setFscore(fmeasure.getFMeasure());
        results.setPrecision(fmeasure.getPrecision());
        results.setRecall(fmeasure.getRecall());

        Yaml yaml = new Yaml();
        yaml.dump(results, new OutputStreamWriter(
                new FileOutputStream(new File(targetFolder, "results.yaml")), "UTF-8"));*/

    }
    
    public int[][] confusionMatrix(List<String> unique, List<String> expected, List<String> actual) {
    	int size = unique.size();
    	
    	int[][] confusionMatrix = new int[size][size];
    	
    	for(int i = 0; i < expected.size(); i++) {
    		if (expected.get(i) == actual.get(i)) {
    			int index = unique.indexOf(expected.get(i));
    			confusionMatrix[index][index]++;
    		} else {
    			int indexExpected = unique.indexOf(expected.get(i));
    			int indexActual = unique.indexOf(actual.get(i));
    			confusionMatrix[indexExpected][indexActual]++;
    		}
    	}    	

    	return confusionMatrix;    	
    }
    
    public double fScore(double precision, double recall) {
    	return 2*(precision*recall)/(precision+recall);
    }
    
    public double precision(int[][] confusionMatrix, int classIndex) {
    	double truePositives = confusionMatrix[classIndex][classIndex];    	
    	double allPositives = 0;
    	for(int i = 0; i < confusionMatrix[classIndex].length; i++) {
    		allPositives += confusionMatrix[classIndex][i];
    	}    	
    	return truePositives/allPositives;
    }
    
    public double recall(int[][] confusionMatrix, int classIndex) {
    	double truePositives = confusionMatrix[classIndex][classIndex];    	
    	double allRelevant = 0;
    	for(int i = 0; i < confusionMatrix[classIndex].length; i++) {
    		allRelevant += confusionMatrix[i][classIndex];
    	}    
    	return truePositives/allRelevant;
    }
    
    public void printEvaluation(int[][] confusionMatrix, List<String> unique) {
    	//Print the confusion matrix and the f1 scores under each class
    	//-------------- print confusion matrix -------------------
    	
    	StringBuilder sb = new StringBuilder("EVALUATION:\n");
    	// header    	
    	for(int i = 0; i < unique.size(); i++) {
        	sb.append("\t" + unique.get(i));
    	}
    	sb.append("\n");
    	
    	// matrix
    	for(int i = 0; i < unique.size(); i++) {
    		sb.append(unique.get(i) + "\t");
    		for(int j = 0; j < unique.size(); j++) {
    			sb.append(confusionMatrix[i][j] + "\t");
    		}
    		sb.append("\n");
    	} 
    	
    	// precision
    	sb.append("precision\t");
    	for(int i = 0; i < unique.size(); i++) {
    		sb.append(precision(confusionMatrix, i) + "\t");
    	}
    	sb.append("\n");

    	// recall
    	sb.append("recall\t\t");
    	for(int i = 0; i < unique.size(); i++) {
    		sb.append(recall(confusionMatrix, i) + "\t");
    	}
    	sb.append("\n");
    	
    	// f1
    	sb.append("f1-score\t");
    	
    	for(int i = 0; i < unique.size(); i++) {
    		double fscore = fScore(precision(confusionMatrix, i), recall(confusionMatrix, i));
    		sb.append(fscore + "\t");
    	}
    	sb.append("\n");    
    	
    	// print to file
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        
    	try {
    		FileOutputStream result = new FileOutputStream(outputDir+ "_eval_" + sdf.format(cal.getTime()) );
    		IOUtils.write(sb, result,"UTF-8");	    	
    	} catch (IOException e) {
    	// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	
    	System.out.println(sb);
    	
    	
    }    

}
