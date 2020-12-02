/**
 * 
 */
package it.cnr.ilc.ga.action.management;

import it.cnr.ilc.ga.handlers.exist.ExistQuery;
import it.cnr.ilc.ga.model.analysis.PartOfSpeech;
import it.cnr.ilc.ga.model.indexsearch.Operator;
import it.cnr.ilc.ga.model.indexsearch.Query;
import it.cnr.ilc.ga.model.indexsearch.QueryForm;
import it.cnr.ilc.ga.model.indexsearch.QueryLang;
import it.cnr.ilc.ga.model.indexsearch.QueryLemma;
//import it.cnr.ilc.ga.model.indexsearch.QueryRoot;
import it.cnr.ilc.ga.model.indexsearch.QueryTerm;
import it.cnr.ilc.ga.model.indexsearch.QueryUnit;
import it.cnr.ilc.ga.model.pericope.Text;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.xmldb.api.base.CompiledExpression;

/**
 * @author Angelo Del Grosso
 *
 */
@ManagedBean(name="qmanager")
@SessionScoped
public class QueryManagerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4368268428027615736L;
	private ExistQuery eq = null;
	private Query query = null;
	private QueryLang qlGreek = null;
	private QueryLang qlArabic = null;
	private Operator opGreek = null;
	private Operator opArabic= null;
	private QueryUnit[] quGreekArray = null;
	private QueryUnit[] quArabicArray = null;
	
	private String esisto = "esisto!!!"; // TODO togliere
	
	CompiledExpression compiled = null;
	
	/**
	 * 
	 */
	public QueryManagerBean() {
		eq = new ExistQuery();
		eq.connect(); // TODO SEVERE togliere la connect dal costruttore
	}

	public boolean queryBuild(String queryOperator, 
							  String typegr, //operatori AND OR NEAR
							  String orderedgr,
							  String[] typesgr, // FORM LEMMA ROOT
							  String[] valuesgr, 
							  String[] posesgr){
		query = new Query();
		query.setQueryOperator(queryOperator);
		qlGreekBuild(typegr, orderedgr, typesgr, valuesgr,posesgr);
		query.setQlGreek(qlGreek);
		
		return true;
	}
	
	private boolean qlGreekBuild(String type, 
								 String ordered,
								 String[] typesgr,
								 String[] valuesgr,
								 String[] posesgr){
		qlGreek = new QueryLang();
		qlGreek.setLangType(Text.LangType.GREEK);
		opGreek.setType(type);
		opGreek.setOrdered(ordered);
		quGreekArray = quGreekArrayBuild(typesgr, valuesgr, posesgr);
		qlGreek.setQuArray(quGreekArray);
		return true;
		//query.setQlGreek(qlGreek);
	}
	
	private QueryUnit[] quGreekArrayBuild(String[] types, String[] values, String[] poses){
		 QueryUnit q0 = new QueryUnit();
		 QueryUnit q1 = new QueryUnit();
		 QueryUnit q2 = new QueryUnit();
		 
		 QueryTerm qt0, qt1, qt2;
		 PartOfSpeech pos0, pos1, pos2;
		 
		 /* Creazione prima queryUnit Greca */
		 
		 if(types[0].equals("form")){
			  qt0 = new QueryForm();
			 
		 }
		 else{
			  qt0 = new QueryLemma();
		 } 
		 
		 qt0.setValue(values[0]);
		 pos0 = new PartOfSpeech();
		 pos0.setValue(poses[0]);
		 
		 q0.setPos(pos0);
		 q0.setQterm(qt0);
		 
		 /* Creazione seconda queryUnit Greca */
		 if(types[1].equals("form")){
			  qt1 = new QueryForm();
			 
		 }
		 else{
			  qt1 = new QueryLemma();
		 } 
		 
		 qt1.setValue(values[1]);
		 pos1 = new PartOfSpeech();
		 pos1.setValue(poses[1]);
		 
		 q1.setPos(pos1);
		 q1.setQterm(qt1);
		 
		 
		 /* Creazione terza queryUnit Greca */
		 if(types[2].equals("form")){
			  qt2 = new QueryForm();
			 
		 }
		 else{
			  qt2 = new QueryLemma();
		 } 
		 
		 qt2.setValue(values[2]);
		 pos2 = new PartOfSpeech();
		 pos2.setValue(poses[2]);
		 
		 q2.setPos(pos2);
		 q2.setQterm(qt2);
		 
		 // tutti gli altri
		QueryUnit[] quArray = {q0, q1, q2}; 
		return quArray; 
	}
	
	
	
//	private QueryUnit[] quArabicArrayBuild(String[] types, String[] values, String[] poses){
//		 QueryUnit q0 = new QueryUnit();
//		 QueryUnit q1 = new QueryUnit();
//		 QueryUnit q2 = new QueryUnit();
//		 
//		 QueryTerm qt0, qt1, qt2;
//		 PartOfSpeech pos0, pos1, pos2;
//		 
//		 /* Creazione prima queryUnit Greca */
//		 
//		 if(types[0].equals("form")){
//			  qt0 = new QueryForm();
//			 
//		 }
//		 else if(types[0].equals("lemma")){
//			  qt0 = new QueryLemma();
//		 } else{
//			 qt0 = new QueryRoot();
//		 }
//		 
//		 qt0.setValue(values[0]);
//		 pos0 = new PartOfSpeech();
//		 pos0.setValue(poses[0]);
//		 
//		 q0.setPos(pos0);
//		 q0.setQterm(qt0);
//		 
//		 /* Creazione seconda queryUnit Greca */
//		 if(types[1].equals("form")){
//			  qt1 = new QueryForm();
//			 
//		 }
//		 else if (types[1].equals("lemma")){
//			  qt1 = new QueryLemma();
//		 }else{
//			 qt1 = new QueryRoot();
//		 } 
//		 
//		 qt1.setValue(values[1]);
//		 pos1 = new PartOfSpeech();
//		 pos1.setValue(poses[1]);
//		 
//		 q1.setPos(pos1);
//		 q1.setQterm(qt1);
//		 
//		 
//		 /* Creazione terza queryUnit Greca */
//		 if(types[2].equals("form")){
//			  qt2 = new QueryForm();
//			 
//		 }
//		 else if (types[2].equals("lemma")){
//			  qt2 = new QueryLemma();
//		 }else{
//			 qt2 = new QueryRoot();
//		 } 
//		 
//		 qt2.setValue(values[2]);
//		 pos2 = new PartOfSpeech();
//		 pos2.setValue(poses[2]);
//		 
//		 q2.setPos(pos2);
//		 q2.setQterm(qt2);
//		 
//		 // tutti gli altri
//		QueryUnit[] quArray = {q0, q1, q2}; 
//		return quArray; 
//	}
	
	
	/**
	 * @return the eq
	 */
	public ExistQuery getEq() {
		return eq;
	}

	/**
	 * @param eq the eq to set
	 */
	public void setEq(ExistQuery eq) {
		this.eq = eq;
	}

	/**
	 * @return the query
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(Query query) {
		this.query = query;
	}

	/**
	 * @return the qlGreek
	 */
	public QueryLang getQlGreek() {
		return qlGreek;
	}

	/**
	 * @param qlGreek the qlGreek to set
	 */
	public void setQlGreek(QueryLang qlGreek) {
		this.qlGreek = qlGreek;
	}

	/**
	 * @return the qlArabic
	 */
	public QueryLang getQlArabic() {
		return qlArabic;
	}

	/**
	 * @param qlArabic the qlArabic to set
	 */
	public void setQlArabic(QueryLang qlArabic) {
		this.qlArabic = qlArabic;
	}

	/**
	 * @return the opGreek
	 */
	public Operator getOpGreek() {
		return opGreek;
	}

	/**
	 * @param opGreek the opGreek to set
	 */
	public void setOpGreek(Operator opGreek) {
		this.opGreek = opGreek;
	}

	/**
	 * @return the opArabic
	 */
	public Operator getOpArabic() {
		return opArabic;
	}

	/**
	 * @param opArabic the opArabic to set
	 */
	public void setOpArabic(Operator opArabic) {
		this.opArabic = opArabic;
	}

	/**
	 * @return the quGreekArray
	 */
	public QueryUnit[] getQuGreekArray() {
		return quGreekArray;
	}

	/**
	 * @param quGreekArray the quGreekArray to set
	 */
	public void setQuGreekArray(QueryUnit[] quGreekArray) {
		this.quGreekArray = quGreekArray;
	}

	/**
	 * @return the quArabicArray
	 */
	public QueryUnit[] getQuArabicArray() {
		return quArabicArray;
	}

	/**
	 * @param quArabicArray the quArabicArray to set
	 */
	public void setQuArabicArray(QueryUnit[] quArabicArray) {
		this.quArabicArray = quArabicArray;
	}

	/**
	 * @return the esisto
	 */
	public String getEsisto() {
		return esisto;
	}

	/**
	 * @param esisto the esisto to set
	 */
	public void setEsisto(String esisto) {
		this.esisto = esisto;
	}

	public String search(
			 	String ntgs,
	            String ntas,

	            String fg1,
	            String tg1,
	            String pg1,
	            
	            String fg2,
	            String tg2,
	            String pg2,
	            
	            String fg3,
	            String tg3,
	            String pg3,
	            
	            String fa1,
	            String ta1,
	            String pa1,
	            
	            String fa2,
	            String ta2,
	            String pa2,
	            
	            String fa3,
	            String ta3,
	            String pa3,
	            
	            String bgs,
	            String bas,
	            String bs  
			
			){
		return eq.query(
				ntgs,
	            ntas,

	            fg1,
	            tg1,
	            pg1,
	            
	            fg2,
	            tg2,
	            pg2,
	            
	            fg3,
	            tg3,
	            pg3,
	            
	            fa1,
	            ta1,
	            pa1,
	            
	            fa2,
	            ta2,
	            pa2,
	            
	            fa3,
	            ta3,
	            pa3,
	            
	            bgs,
	            bas,
	            bs 
		);
	}

	public String searchAr(String term){
		eq = new ExistQuery();
		eq.connect();
		
		String query=""
			+"     declare boundary-space preserve;\n"
			+"     declare variable $tg1 as xs:string external;\n"
			+"let $wcontext:=/add/doc/field[@name='analysis_ar']/w\n"
			+"let $w1:=$wcontext[@form=$tg1]\n"
			+"for $wm in $w1\n"
			+" let $prog1:=$wm/@prog/xs:string(.)\n"
			+" return <div class=\"result\"><span class=\"info_ar\"> {xs:string($wm/../../field[@name='info_ar'])} </span> {\n"
			+"  for $wx in $wm/../w\n"
			+"   return if($wx/@prog=$prog1) then <font color=\"red\"> {xs:string($wx/@form)} </font>  else xs:string($wx/@form)\n"
			+"  }"
			+"  <br/> <span class='info_gr'> {xs:string($wm/../../field[@name='info_gr'])} </span> {for $wa in $wm/../../field[@name='analysis_gr']/w/@form\n"
			+"   return  xs:string($wa)\n"
			+"       } \n"
			+" <br/><br/></div>\n";
		
		return null; //eq.query(query,term);
	}
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
