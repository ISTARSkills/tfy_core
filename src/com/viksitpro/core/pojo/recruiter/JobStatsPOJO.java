package com.viksitpro.core.pojo.recruiter;

import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jobStats")
public class JobStatsPOJO {

	Integer jobId;
	Integer campaignId;
	Integer recruiterId;
	String city;
	String college;
	String jobTitle;
	LinkedHashMap<String, Integer> stageCount;

	@XmlAttribute(name = "jobId", required = false)
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	@XmlAttribute(name = "campaignId", required = false)
	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	@XmlAttribute(name = "jobTitle", required = false)
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@XmlAttribute(name = "stageCount", required = false)
	public LinkedHashMap<String, Integer> getStageCount() {
		return stageCount;
	}

	public void setStageCount(LinkedHashMap<String, Integer> stageCount) {
		this.stageCount = stageCount;
	}

	@XmlAttribute(name = "recruiterId", required = false)
	public Integer getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Integer recruiterId) {
		this.recruiterId = recruiterId;
	}

	@XmlAttribute(name = "city", required = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlAttribute(name = "college", required = false)
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
}
