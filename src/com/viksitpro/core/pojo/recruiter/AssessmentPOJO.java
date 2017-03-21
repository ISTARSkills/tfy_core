package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "assessment")
public class AssessmentPOJO {

	private Integer id;
	private String assessmentType;
	private Integer numberOfQuestions;
	private Integer assessmentdurationhours;
	private Integer assessmentdurationminutes;
	private String assessmentTitle;
	private Boolean retryAble;
	private String category;
	
	public AssessmentPOJO(){
		
	}
	
	@XmlAttribute(name = "assessmentId", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "assessmentType", required=false)
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	
	@XmlAttribute(name = "numberOfQuestions", required=false)
	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(Integer numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	
	@XmlAttribute(name = "assessmentdurationhours", required=false)
	public Integer getAssessmentdurationhours() {
		return assessmentdurationhours;
	}
	public void setAssessmentdurationhours(Integer assessmentdurationhours) {
		this.assessmentdurationhours = assessmentdurationhours;
	}
	
	@XmlAttribute(name = "assessmentdurationminutes", required=false)
	public Integer getAssessmentdurationminutes() {
		return assessmentdurationminutes;
	}
	public void setAssessmentdurationminutes(Integer assessmentdurationminutes) {
		this.assessmentdurationminutes = assessmentdurationminutes;
	}
	
	@XmlAttribute(name = "assessmentTitle", required=false)
	public String getAssessmentTitle() {
		return assessmentTitle;
	}
	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}
	
	@XmlAttribute(name = "retryAble", required=false)
	public Boolean getRetryAble() {
		return retryAble;
	}
	public void setRetryAble(Boolean retryAble) {
		this.retryAble = retryAble;
	}
	
	@XmlAttribute(name = "category", required=false)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
