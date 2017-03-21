package com.viksitpro.core.pojo.recruiter;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class StudentPOJO {

	private Integer istarUser;
	private Integer task;
	private String firstName;
	private String lastName;
	private String gender;
	private String location;
	private String profileImage;
	private String underGraduationSpecializationName;
	private String underGraduationDegree;
	private String underGraduationCollege;
	private String postGraduationSpecializationName;
	private String postGraduationDegree;
	private String postGraduationCollege;    
	private String resumeURL;
	private Boolean noShowFlag;
	ArrayList<StageLogPOJO> stageResults;
	ArrayList<SkillPOJO> skills;
	ArrayList<TaskFeedbackPOJO> allTaskFeedbacks;
	
	@XmlAttribute(name = "istarUserId", required=false)
	public Integer getIstarUser() {
		return istarUser;
	}
	public void setIstarUser(Integer istarUser) {
		this.istarUser = istarUser;
	}
	
	@XmlAttribute(name = "task", required=false)
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	
	@XmlAttribute(name = "firstName", required=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlAttribute(name = "lastName", required=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlAttribute(name = "gender", required=false)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@XmlAttribute(name = "location", required=false)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlAttribute(name = "profileImage", required=false)
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	@XmlAttribute(name = "underGraduationSpecializationName", required=false)
	public String getUnderGraduationSpecializationName() {
		return underGraduationSpecializationName;
	}
	public void setUnderGraduationSpecializationName(String underGraduationSpecializationName) {
		this.underGraduationSpecializationName = underGraduationSpecializationName;
	}
	@XmlAttribute(name = "underGraduationDegree", required=false)
	public String getUnderGraduationDegree() {
		return underGraduationDegree;
	}
	public void setUnderGraduationDegree(String underGraduationDegree) {
		this.underGraduationDegree = underGraduationDegree;
	}
	
	@XmlAttribute(name = "underGraduationCollege", required=false)
	public String getUnderGraduationCollege() {
		return underGraduationCollege;
	}
	public void setUnderGraduationCollege(String underGraduationCollege) {
		this.underGraduationCollege = underGraduationCollege;
	}
	
	@XmlAttribute(name = "postGraduationSpecializationName", required=false)
	public String getPostGraduationSpecializationName() {
		return postGraduationSpecializationName;
	}
	public void setPostGraduationSpecializationName(String postGraduationSpecializationName) {
		this.postGraduationSpecializationName = postGraduationSpecializationName;
	}
	
	@XmlAttribute(name = "postGraduationDegree", required=false)
	public String getPostGraduationDegree() {
		return postGraduationDegree;
	}
	public void setPostGraduationDegree(String postGraduationDegree) {
		this.postGraduationDegree = postGraduationDegree;
	}
	
	@XmlAttribute(name = "postGraduationCollege", required=false)
	public String getPostGraduationCollege() {
		return postGraduationCollege;
	}
	public void setPostGraduationCollege(String postGraduationCollege) {
		this.postGraduationCollege = postGraduationCollege;
	}
	
	@XmlAttribute(name = "resumeURL", required=false)
	public String getResumeURL() {
		return resumeURL;
	}
	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}
	
	@XmlAttribute(name = "stageResults", required=false)
	public ArrayList<StageLogPOJO> getStageResults() {
		return stageResults;
	}
	public void setStageResults(ArrayList<StageLogPOJO> stageResults) {
		this.stageResults = stageResults;
	}
	
	@XmlAttribute(name = "noShowFlag", required=false)
	public Boolean getNoShowFlag() {
		return noShowFlag;
	}
	public void setNoShowFlag(Boolean noShowFlag) {
		this.noShowFlag = noShowFlag;
	}
	
	@XmlAttribute(name = "skills", required=false)
	public ArrayList<SkillPOJO> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<SkillPOJO> skills) {
		this.skills = skills;
	}
	
	@XmlAttribute(name = "allTaskFeedbacks", required=false)
	public ArrayList<TaskFeedbackPOJO> getAllTaskFeedbacks() {
		return allTaskFeedbacks;
	}
	public void setAllTaskFeedbacks(ArrayList<TaskFeedbackPOJO> allTaskFeedbacks) {
		this.allTaskFeedbacks = allTaskFeedbacks;
	}

}
