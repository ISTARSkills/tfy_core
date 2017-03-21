package com.viksitpro.core.pojo.recruiter;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "job")
public class JobPOJO {

	private Integer jobId;
	private Integer istarUser;
	private Integer organizationId;
	private String organization;
	private Integer taskType;
	private String title;
	private String description;
	private ArrayList<String> tags;
	private ArrayList<String> locations;
	private Integer maximumExperience;
	private Integer minimumExperience;
	private Integer availablePositions;
	private Integer totalPositions;
	private Double maximumSalary;
	private Double minimumSalary;
	private String socialCode;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private JobProfilePOJO jobProfile;

	public JobPOJO() {
	}

	@XmlAttribute(name = "jobId", required = false)
	public Integer getJobId() {
		return this.jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	@XmlAttribute(name = "recruiter", required = false)
	public Integer getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(Integer istarUser) {
		this.istarUser = istarUser;
	}

	@XmlAttribute(name = "organizationId", required = false)
	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@XmlAttribute(name = "organization", required = false)
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@XmlAttribute(name = "title", required = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlAttribute(name = "description", required = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute(name = "taskType", required = false)
	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	@XmlAttribute(name = "maximumExperience", required = false)
	public Integer getMaximumExperience() {
		return maximumExperience;
	}

	public void setMaximumExperience(Integer maximumExperience) {
		this.maximumExperience = maximumExperience;
	}

	@XmlAttribute(name = "minimumExperience", required = false)
	public Integer getMinimumExperience() {
		return minimumExperience;
	}

	public void setMinimumExperience(Integer minimumExperience) {
		this.minimumExperience = minimumExperience;
	}

	@XmlAttribute(name = "availablePositions", required = false)
	public Integer getAvailablePositions() {
		return availablePositions;
	}

	public void setAvailablePositions(Integer availablePositions) {
		this.availablePositions = availablePositions;
	}

	@XmlAttribute(name = "totalPositions", required = false)
	public Integer getTotalPositions() {
		return totalPositions;
	}

	public void setTotalPositions(Integer totalPositions) {
		this.totalPositions = totalPositions;
	}

	@XmlAttribute(name = "maximumSalary", required = false)
	public Double getMaximumSalary() {
		return maximumSalary;
	}

	public void setMaximumSalary(Double maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	@XmlAttribute(name = "minimumSalary", required = false)
	public Double getMinimumSalary() {
		return minimumSalary;
	}

	public void setMinimumSalary(Double minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	@XmlAttribute(name = "socialCode", required = false)
	public String getSocialCode() {
		return socialCode;
	}

	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}

	@XmlAttribute(name = "createdAt", required = false)
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@XmlAttribute(name = "updatedAt", required = false)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@XmlAttribute(name = "tags", required = false)
	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	@XmlAttribute(name = "locations", required = false)
	public ArrayList<String> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
	}

	@XmlAttribute(name = "jobProfile", required = false)
	public JobProfilePOJO getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(JobProfilePOJO jobProfile) {
		this.jobProfile = jobProfile;
	}
}
