package de.unihamburg.informatik.nlp4web.finalproject.feature;

import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.extractor.CleartkExtractorException;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;


public class DictionaryMappingExtractor<T extends Annotation> implements NamedFeatureExtractor1 {

	
	List<String> businessList = new ArrayList<String>();
	List<String> entertainmentList = new ArrayList<String>();
	List<String> healthList = new ArrayList<String>();
	List<String> scienceList = new ArrayList<String>();
	
	private Class<? extends Annotation> annotationType;

    private String name;
    
    private void buildLists() {
    	businessList.add( "banking");
    	businessList.add( "wall street");
    	
    	entertainmentList.add( "George Zimmerman");
    	
    	healthList.add( "addiction");
    	
    	scienceList.add( "asteroid");
    }
    
    public DictionaryMappingExtractor(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
        this.name = "DictionaryM_" + this.annotationType.getName();
        buildLists();
    }
    
	@Override
	public List extract(JCas view, Annotation focusAnnotation) throws CleartkExtractorException {
		List<?> annotations = JCasUtil.selectCovered(this.annotationType, focusAnnotation);
        if(annotations.size()<1) {
            System.out.println("hre");
        }
        
        String sentence = ((Annotation) annotations.get(0)).getCoveredText();
        for ( String word : businessList ) {
        	if ( sentence.contains( word ) ) {
        		return Arrays.asList(new Feature(this.name, 1));
        	}
        }
        for ( String word : entertainmentList ) {
        	if ( sentence.contains( word ) ) {
        		return Arrays.asList(new Feature(this.name, 2));
        	}
        }
        for ( String word : healthList ) {
        	if ( sentence.contains( word ) ) {
        		return Arrays.asList(new Feature(this.name, 3));
        	}
        }
        for ( String word : scienceList ) {
        	if ( sentence.contains( word ) ) {
        		return Arrays.asList(new Feature(this.name, 4));
        	}     
        }
        
        return Arrays.asList(new Feature(this.name, 0));
	}

	@Override
	public String getFeatureName() {
		return this.name;
	}

}
