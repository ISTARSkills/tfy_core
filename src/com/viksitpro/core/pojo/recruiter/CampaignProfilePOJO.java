package com.viksitpro.core.pojo.recruiter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campaignProfile")
public class CampaignProfilePOJO {

	private Integer campaignId;
	private String rank;
	private HashMap<String,String> ugDegreesAndSpecializations = new HashMap<String, String>();
	private HashMap<String,String> pgDegreesAndSpecializations = new HashMap<String, String>();
	private ArrayList<String> cities = new ArrayList<String>();
	private ArrayList<String> colleges = new ArrayList<String>();
	private HashMap<String, String> skills = new HashMap<String, String>();
	private Float ugCutOff = 30f;
	private Float pgCutOff =30f;
	private Float highschoolCutOff = 30f;
	private Float intermediateCutOff =30f;

	
	@XmlAttribute(name = "campaignId", required=false)
	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	@XmlAttribute(name = "rank", required=false)
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	
	@XmlAttribute(name = "cities", required=false)
	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	
	@XmlAttribute(name = "colleges", required=false)
	public ArrayList<String> getColleges() {
		return colleges;
	}

	public void setColleges(ArrayList<String> colleges) {
		this.colleges = colleges;
	}

	
	@XmlAttribute(name = "skills", required=false)
	public HashMap<String, String> getSkills() {
		return skills;
	}

	public void setSkills(HashMap<String, String> skills) {
		this.skills = skills;
	}
	
	@XmlAttribute(name = "ugCutOff", required=false)
	public Float getUgCutOff() {
		return ugCutOff;
	}

	public void setUgCutOff(Float ugCutOff) {
		this.ugCutOff = ugCutOff;
	}
	
	@XmlAttribute(name = "ugDegreesAndSpecializations", required=false)
	public HashMap<String, String> getUgDegreesAndSpecializations() {
		return ugDegreesAndSpecializations;
	}

	public void setUgDegreesAndSpecializations(HashMap<String, String> ugDegreesAndSpecializations) {
		this.ugDegreesAndSpecializations = ugDegreesAndSpecializations;
	}

	@XmlAttribute(name = "pgDegreesAndSpecializations", required=false)
	public HashMap<String, String> getPgDegreesAndSpecializations() {
		return pgDegreesAndSpecializations;
	}

	public void setPgDegreesAndSpecializations(HashMap<String, String> pgDegreesAndSpecializations) {
		this.pgDegreesAndSpecializations = pgDegreesAndSpecializations;
	}

	
	@XmlAttribute(name = "pgCutOff", required=false)
	public Float getPgCutOff() {
		return pgCutOff;
	}

	public void setPgCutOff(Float pgCutOff) {
		this.pgCutOff = pgCutOff;
	}

	
	@XmlAttribute(name = "highschoolCutOff", required=false)
	public Float getHighschoolCutOff() {
		return highschoolCutOff;
	}

	public void setHighschoolCutOff(Float highschoolCutOff) {
		this.highschoolCutOff = highschoolCutOff;
	}

	
	@XmlAttribute(name = "intermediateCutOff", required=false)
	public Float getIntermediateCutOff() {
		return intermediateCutOff;
	}

	public void setIntermediateCutOff(Float intermediateCutOff) {
		this.intermediateCutOff = intermediateCutOff;
	}
	
}
