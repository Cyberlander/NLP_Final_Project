

/* First created by JCasGen Mon Nov 12 23:01:29 CET 2018 */
package de.unihamburg.informatik.nlp4web.finalproject.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Nov 12 23:18:45 CET 2018
 * XML source: /Users/seidmuhieyimam/ownCloud/Teaching/NLP4Web2018-19/practice/lecture5Pos/classification/classification/src/main/resources/desc/type/NewsAnnotation.xml
 * @generated */
public class NewsAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NewsAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NewsAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NewsAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NewsAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NewsAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: goldValue

  /** getter for goldValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGoldValue() {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_goldValue == null)
      jcasType.jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_goldValue);}
    
  /** setter for goldValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGoldValue(String v) {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_goldValue == null)
      jcasType.jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_goldValue, v);}    
   
    
  //*--------------*
  //* Feature: predictValue

  /** getter for predictValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPredictValue() {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_predictValue == null)
      jcasType.jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_predictValue);}
    
  /** setter for predictValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPredictValue(String v) {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_predictValue == null)
      jcasType.jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_predictValue, v);}    
   
    
  //*--------------*
  //* Feature: title

  /** getter for title - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTitle() {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_title);}
    
  /** setter for title - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTitle(String v) {
    if (NewsAnnotation_Type.featOkTst && ((NewsAnnotation_Type)jcasType).casFeat_title == null)
      jcasType.jcas.throwFeatMissing("title", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((NewsAnnotation_Type)jcasType).casFeatCode_title, v);}    
  }

    