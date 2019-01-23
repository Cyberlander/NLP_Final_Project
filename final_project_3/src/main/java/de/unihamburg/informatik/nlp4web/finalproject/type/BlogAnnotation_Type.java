
/* First created by JCasGen Mon Nov 12 23:01:29 CET 2018 */
package de.unihamburg.informatik.nlp4web.finalproject.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Jan 23 14:59:17 CET 2019
 * @generated */
public class BlogAnnotation_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = BlogAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
 
  /** @generated */
  final Feature casFeat_goldValue;
  /** @generated */
  final int     casFeatCode_goldValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getGoldValue(int addr) {
        if (featOkTst && casFeat_goldValue == null)
      jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_goldValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGoldValue(int addr, String v) {
        if (featOkTst && casFeat_goldValue == null)
      jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_goldValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_predictValue;
  /** @generated */
  final int     casFeatCode_predictValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPredictValue(int addr) {
        if (featOkTst && casFeat_predictValue == null)
      jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_predictValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPredictValue(int addr, String v) {
        if (featOkTst && casFeat_predictValue == null)
      jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_predictValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "de.unihamburg.informatik.nlp4web.finalproject.type.BlogAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public BlogAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_goldValue = jcas.getRequiredFeatureDE(casType, "goldValue", "uima.cas.String", featOkTst);
    casFeatCode_goldValue  = (null == casFeat_goldValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_goldValue).getCode();

 
    casFeat_predictValue = jcas.getRequiredFeatureDE(casType, "predictValue", "uima.cas.String", featOkTst);
    casFeatCode_predictValue  = (null == casFeat_predictValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_predictValue).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

  }
}



    