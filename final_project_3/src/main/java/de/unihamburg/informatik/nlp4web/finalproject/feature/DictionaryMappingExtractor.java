package de.unihamburg.informatik.nlp4web.finalproject.feature;

import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.FeatureCollection;
import org.cleartk.ml.feature.extractor.CleartkExtractorException;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;


public class DictionaryMappingExtractor<T extends Annotation> implements NamedFeatureExtractor1 {

	
	List<String> dictList = new ArrayList<String>();
	//List<String> femaleDom = new ArrayList<String>();
	//List<String> healthList = new ArrayList<String>();
	//List<String> scienceList = new ArrayList<String>();
	
	private Class<? extends Annotation> annotationType;

    private String name;
    private int dict;
    
    private void buildLists() {
    	
    	if(dict==0) {
    		dictList.addAll(Arrays.asList("linux","microsoft","gaming","server","software","gb","programming","google","data","graphics","india","nations","democracy","users","economic"));
    	}
    	else if(dict == 1) {
    		dictList.addAll(Arrays.asList("shopping","mom","cried","freaked","pink","cute","gosh","kisses","yummy","mommy","boyfriend","skirt","adorable","husband","hubby"));
    	}
    	else if(dict == 2) {
    		dictList.addAll(Arrays.asList("work","home","sleep","today","eat","tired","wake","watch","watched","dinner","ate","bed","day","house","tv","early","boring","yesterday","watching","sit","central","familiar","family","native","at","ease","rest","down","home","homely","homey"));
    	}
    	else if(dict == 3) {
    		dictList.addAll(Arrays.asList("know","people","think","person","tell","feel","friends","talk","new","talking","mean","ask","understand","feelings","care","thinking","friend","relationship","realize","question","answer","saying"));
    	}
    	else if(dict == 4) {
    		dictList.addAll(Arrays.asList("friday","saturday","weekend","week","sunday","night","monday","tuesday","thursday","wednesday","morning","tommorrow","tonight","evening","days","afternoon","weeks","hours","july","busy","meeting","hour","months","month","june","january","february","march","april","may","august","september","october","november","december"));
    	}
    	else if(dict == 5) {
    		dictList.addAll(Arrays.asList("work","working","works","job","trying","right","met","figure","meet","better","ting","try","worked","idea"));
    	}
    	else if(dict == 6) {
    		dictList.addAll(Arrays.asList("money","cash","cheque","debit","credit","borrow","bank","accountant","deposit","income","salary","savings","expenditure","allowance","millionaire","billionaire","bonus"));
    	}
    	else if(dict == 7) {
    		dictList.addAll(Arrays.asList("said","asked","told","looked","walked","called","talked","wanted","kept","took","sat","gave","knew","felt","turned","stopped","saw","ran","tried","picked","left","ended"));
    	}
    	else if(dict == 8) {
    		dictList.addAll(Arrays.asList("games","game","team","win","play","played","playing","won","season","beat","final","two","hit","first","video","second","run","third","shot","table","round","ten","chance","club","big","straight","athletics","pastime","action","amusement","ball","disport","diversion","exercise","frolic","gaiety","picnic","pleasure","recreation"));
    	}
    	    		
    	//healthList.add( "shiny");
    	
    	//scienceList.add( "asteroid");
    }
    
    public DictionaryMappingExtractor(Class<? extends Annotation> annotationType,int dict) {
        this.annotationType = annotationType;
        this.name = "DictionaryM_" + this.annotationType.getName()+"_Dict" + dict;
        this.dict = dict;
        buildLists();
    }
    
	@Override
	public List extract(JCas view, Annotation focusAnnotation) throws CleartkExtractorException {
		List<?> annotations = JCasUtil.selectCovered(this.annotationType, focusAnnotation);
        if(annotations.size()<1) {
            System.out.println("here");
        }
        String sentence = ((Annotation) annotations.get(0)).getCoveredText();
        int totalWords = sentence.length();
        ArrayList<Feature> featureList = new ArrayList<>();
        
        if(totalWords > 0) {
        	int countAppearance = 0;
        
	        for ( String word : dictList ) {
	        	if ( sentence.contains( word ) ) {
	        		countAppearance++;
	        	}
	        }
	        
	        double factorAppearance = (countAppearance / (1.0 * totalWords));   
	        System.out.println("Dict" + this.dict + " Appear" +countAppearance);
	        return Arrays.asList(new Feature(this.name,factorAppearance));// Arrays.asList(factorMaleDom,factorFemaleDom)));
        }
        //return Arrays.asList(new FeatureCollection(this.name,featureList));//Arrays.asList(0.0,0.0)));
        return Arrays.asList(new Feature(this.name,0.0));// Arrays.asList(factorMaleDom,factorFemaleDom)));
	}

	@Override
	public String getFeatureName() {
		return this.name;
	}

}
