

/* First created by JCasGen Mon Nov 12 23:01:29 CET 2018 */
package de.unihamburg.informatik.nlp4web.finalproject.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jan 23 14:59:17 CET 2019
 * XML source: C:/Dev/Repos/NLP_Final_Project/final_project_3/src/main/resources/desc/type/BlogAnnotation.xml
 * @generated */
public class BlogAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(BlogAnnotation.class);
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
  protected BlogAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public BlogAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public BlogAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public BlogAnnotation(JCas jcas, int begin, int end) {
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
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_goldValue == null)
      jcasType.jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_goldValue);}
    
  /** setter for goldValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGoldValue(String v) {
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_goldValue == null)
      jcasType.jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_goldValue, v);}    
   
    
  //*--------------*
  //* Feature: predictValue

  /** getter for predictValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPredictValue() {
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_predictValue == null)
      jcasType.jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_predictValue);}
    
  /** setter for predictValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPredictValue(String v) {
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_predictValue == null)
      jcasType.jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_predictValue, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (BlogAnnotation_Type.featOkTst && ((BlogAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((BlogAnnotation_Type)jcasType).casFeatCode_text, v);}    
  }

    