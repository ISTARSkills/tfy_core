/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class DeliveryAssessmentTree {

	Integer Id;
	String assessmentTitle;
	ArrayList<DeliveryQuestion> questions;
	boolean isValid;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getAssessmentTitle() {
		return assessmentTitle;
	}
	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}
	public ArrayList<DeliveryQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<DeliveryQuestion> questions) {
		this.questions = questions;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	
}
