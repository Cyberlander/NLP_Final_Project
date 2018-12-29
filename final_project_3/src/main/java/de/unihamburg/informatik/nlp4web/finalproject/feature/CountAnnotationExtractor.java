package de.unihamburg.informatik.nlp4web.finalproject.feature;


import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.extractor.CleartkExtractorException;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import java.util.Arrays;
import java.util.List;

public class CountAnnotationExtractor<T extends Annotation> implements
            NamedFeatureExtractor1<T> {

        private Class<? extends Annotation> annotationType;

        private String name;

        public CountAnnotationExtractor(Class<? extends Annotation> annotationType) {
            this.annotationType = annotationType;
            this.name = "Count_" + this.annotationType.getName();
        }

        @Override
        public String getFeatureName() {
            return this.name;
        }

        @Override
        public List<Feature> extract(JCas view, Annotation focusAnnotation)
                throws CleartkExtractorException {
            List<?> annotations = JCasUtil.selectCovered(this.annotationType, focusAnnotation);
            if(annotations.size()<1) {
                System.out.println("hre");
            }
           // System.out.println(((Annotation) annotations.get(0)).getCoveredText().split(" "));
            return Arrays.asList(new Feature(this.name, annotations.size()));
        }
    }

