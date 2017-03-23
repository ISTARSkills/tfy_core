package com.viksitpro.core.pojo.recruiter;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jobFilter")
public class JobProfilePOJO {
	
	private String jobId;
	private String jobCategory;
	private String jobPositionType;
	private String jobExperienceLevel;
	private ArrayList<String> graduationYears = new ArrayList<String>();
	private ArrayList<String> skills = new ArrayList<String>();

	public JobProfilePOJO(){
		
	}
	
	@XmlAttribute(name = "jobId", required=false)
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@XmlAttribute(name = "jobCategory", required=false)
	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	
	@XmlAttribute(name = "jobPositionType", required=false)
	public String getJobPositionType() {
		return jobPositionType;
	}

	public void setJobPositionType(String jobPositionType) {
		this.jobPositionType = jobPositionType;
	}

	
	@XmlAttribute(name = "jobExperienceLevel", required=false)
	public String getJobExperienceLevel() {
		return jobExperienceLevel;
	}

	public void setJobExperienceLevel(String jobExperienceLevel) {
		this.jobExperienceLevel = jobExperienceLevel;
	}

	@XmlAttribute(name = "graduationYears", required=false)
	public ArrayList<String> getGraduationYears() {
		return graduationYears;
	}

	public void setGraduationYears(ArrayList<String> graduationYears) {
		this.graduationYears = graduationYears;
	}

	@XmlAttribute(name = "skills", required=false)
	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}	
}
