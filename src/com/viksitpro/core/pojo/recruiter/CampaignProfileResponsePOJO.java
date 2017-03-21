package com.viksitpro.core.pojo.recruiter;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campaignProfileResponse")
public class CampaignProfileResponsePOJO {

	private Integer campaignId;
	private Integer rank;
	private Integer ugDegreesAndSpecializations;
	private Integer pgDegreesAndSpecializations;
	private Integer cities;
	private Integer colleges;
	private Integer skills;
	private Integer ugCutOff;
	private Integer pgCutOff;
	private Integer highschoolCutOff;
	private Integer intermediateCutOff;
	private ArrayList<StudentPOJO> allApplicants;
	
	@XmlAttribute(name = "campaignId", required=false)
	public Integer getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	@XmlAttribute(name = "rank", required=false)
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	@XmlAttribute(name = "ugDegreesAndSpecializations", required=false)
	public Integer getUgDegreesAndSpecializations() {
		return ugDegreesAndSpecializations;
	}
	public void setUgDegreesAndSpecializations(Integer ugDegreesAndSpecializations) {
		this.ugDegreesAndSpecializations = ugDegreesAndSpecializations;
	}
	
	@XmlAttribute(name = "pgDegreesAndSpecializations", required=false)
	public Integer getPgDegreesAndSpecializations() {
		return pgDegreesAndSpecializations;
	}
	public void setPgDegreesAndSpecializations(Integer pgDegreesAndSpecializations) {
		this.pgDegreesAndSpecializations = pgDegreesAndSpecializations;
	}
	
	@XmlAttribute(name = "cities", required=false)
	public Integer getCities() {
		return cities;
	}
	public void setCities(Integer cities) {
		this.cities = cities;
	}
	
	@XmlAttribute(name = "colleges", required=false)
	public Integer getColleges() {
		return colleges;
	}
	public void setColleges(Integer colleges) {
		this.colleges = colleges;
	}
	
	@XmlAttribute(name = "skills", required=false)
	public Integer getSkills() {
		return skills;
	}
	public void setSkills(Integer skills) {
		this.skills = skills;
	}
	
	@XmlAttribute(name = "ugCutOff", required=false)
	public Integer getUgCutOff() {
		return ugCutOff;
	}
	public void setUgCutOff(Integer ugCutOff) {
		this.ugCutOff = ugCutOff;
	}
	
	@XmlAttribute(name = "pgCutOff", required=false)
	public Integer getPgCutOff() {
		return pgCutOff;
	}
	public void setPgCutOff(Integer pgCutOff) {
		this.pgCutOff = pgCutOff;
	}
	
	@XmlAttribute(name = "highschoolCutOff", required=false)
	public Integer getHighschoolCutOff() {
		return highschoolCutOff;
	}
	public void setHighschoolCutOff(Integer highschoolCutOff) {
		this.highschoolCutOff = highschoolCutOff;
	}
	
	@XmlAttribute(name = "intermediateCutOff", required=false)
	public Integer getIntermediateCutOff() {
		return intermediateCutOff;
	}
	public void setIntermediateCutOff(Integer intermediateCutOff) {
		this.intermediateCutOff = intermediateCutOff;
	}
	
	@XmlAttribute(name = "allApplicants", required=false)
	public ArrayList<StudentPOJO> getAllApplicants() {
		return allApplicants;
	}
	public void setAllApplicants(ArrayList<StudentPOJO> allApplicants) {
		this.allApplicants = allApplicants;
	}
	
	
}
