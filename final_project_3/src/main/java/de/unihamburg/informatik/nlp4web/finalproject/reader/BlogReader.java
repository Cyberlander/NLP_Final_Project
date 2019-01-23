package de.unihamburg.informatik.nlp4web.finalproject.reader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import org.apache.uima.util.Logger;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation;

/**
 * The processed news are formatted as
 * TITLE TAB CATEGGORY
 * CATEGORY : the category of the news item; one of: -- b : business -- t : science and technology -- e : entertainment -- m : health
 *
 *ExMPLE <br>
 *<b>Stocks slip as investors keep eye on Asia  b</b>
 *
 */
public class BlogReader extends JCasAnnotator_ImplBase {

    public static final String CONLL_VIEW = "ConnlView";
    private Logger logger = null;
    
    
    public static final String PARAM_STOP_WORDS = "stopwords";
    @ConfigurationParameter(name = PARAM_STOP_WORDS,
            description = "Stop words for the language",
            mandatory = true)
    private File stopwordFile;

    List<String> stopWords = new ArrayList<>();
    @Override
    public void initialize(UimaContext context)
            throws ResourceInitializationException {
        super.initialize(context);
        logger = context.getLogger();
        
        if(stopwordFile == null) {
            stopWords = new ArrayList<>(); 
        }
        else {
            try {
                for (String stopword: FileUtils.readFileToString(stopwordFile, "UTF-8").split("[\\s\n]")) {
                    stopWords.add(stopword);
                }
            }
            catch (Exception e) {
                stopWords = new ArrayList<>();     
            }
        }
    }

    @Override
    public void process(JCas jcas)
            throws AnalysisEngineProcessException {
        JCas docView;
        String tbText;
      //  Pattern p = Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
        StringBuffer docText = new StringBuffer();
        try {
            docView = jcas.getView(CAS.NAME_DEFAULT_SOFA);
            tbText = jcas.getView(CONLL_VIEW).getDocumentText();
        } catch (CASException e) {
            throw new AnalysisEngineProcessException(e);
        }
        // a new sentence always starts with a new line
        if (tbText.charAt(0) != '\n') {
            tbText = "\n" + tbText;
        }

        String[] tweets = tbText.split("(\r\n|\n)");
        
        int idx = 0;
        Token token = null;
        BlogAnnotation news = null;
        String newsCategory;
        Sentence sentence = null;

        for (String line : tweets) {
            if (line.trim().isEmpty()) {
                continue;
            }
            // new sentence if there's a new line
            String[] tag =line.split("\t");

                String tweet = preprocessed(tag[0]);
                if (tweet.split(" ").length <3) {
                    continue;
                }
                
                newsCategory = tag[1];
                
                docText.append(tweet);
                docText.append(" ");
                
                news = new BlogAnnotation(docView, idx, idx + tweet.length());
                sentence = new Sentence(docView, idx, idx + tweet.length());
                
                String[] words = tweet.split("\\s+");
                int current = 0;
                int position = 0;
                int tokenStart = 0;
                int tokenEnd = 0;
                
                for (String word : words){
                	do{
                		position = tweet.indexOf(word, current);
                	} while (position < current);
                	current = position + 1;
                	tokenStart = idx + position;
                	tokenEnd = tokenStart + word.length();
                	token = new Token(docView, tokenStart, tokenEnd);
                	token.addToIndexes();

                }

                
                idx++;

                // increment actual index of text
                idx += tweet.length();
                news.setGoldValue(newsCategory);           
                // we need the original title here
                news.setText(tag[0]);
                

                news.addToIndexes();
                sentence.addToIndexes();
        }

        docView.setSofaDataString(docText.toString(), "text/plain");
    }
    
    String preprocessed(String aTitle) {
       
        aTitle = aTitle.toLowerCase();
        for(String stopWord:stopWords) {
            aTitle = aTitle.replace(" "+stopWord+" ", " ");
        };
        return aTitle.replaceAll("[^a-zA-Z0-9]", " ").replace("  ", " ");
    }
}
