
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
 * Updated by JCasGen Mon Nov 12 23:18:45 CET 2018
 * @generated */
public class NewsAnnotation_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NewsAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
 
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
      jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_goldValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGoldValue(int addr, String v) {
        if (featOkTst && casFeat_goldValue == null)
      jcas.throwFeatMissing("goldValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
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
      jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_predictValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPredictValue(int addr, String v) {
        if (featOkTst && casFeat_predictValue == null)
      jcas.throwFeatMissing("predictValue", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_predictValue, v);}
    
  
 
  /** @generated */
  final Feature casFeat_title;
  /** @generated */
  final int     casFeatCode_title;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTitle(int addr) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_title);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTitle(int addr, String v) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "de.unihamburg.informatik.nlp4web.tutorial.tut5.type.NewsAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_title, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NewsAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_goldValue = jcas.getRequiredFeatureDE(casType, "goldValue", "uima.cas.String", featOkTst);
    casFeatCode_goldValue  = (null == casFeat_goldValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_goldValue).getCode();

 
    casFeat_predictValue = jcas.getRequiredFeatureDE(casType, "predictValue", "uima.cas.String", featOkTst);
    casFeatCode_predictValue  = (null == casFeat_predictValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_predictValue).getCode();

 
    casFeat_title = jcas.getRequiredFeatureDE(casType, "title", "uima.cas.String", featOkTst);
    casFeatCode_title  = (null == casFeat_title) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_title).getCode();

  }
}



    