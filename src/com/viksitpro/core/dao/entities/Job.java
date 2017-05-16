package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job", schema = "public")

public class Job implements java.io.Serializable {

	// Fields

	private Integer id;
	private TaskType taskType;
	private IstarUser istarUser;
	private Organization organization;
	private String title;
	private String description;
	private Integer maximumExperience;
	private Integer minimumExperience;
	private Integer availablePositions;
	private Integer totalPositions;
	private Double maximumSalary;
	private Double minimumSalary;
	private String socialCode;
	private String state;
	private String tags;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String location;
	private Set<Campaign> campaigns = new HashSet<Campaign>(0);
	private Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings = new HashSet<RecruiterJobTaskCollegeMapping>(
			0);
	private Set<SkillRating> skillRatings = new HashSet<SkillRating>(0);
	private Set<JobPanelistMapping> jobPanelistMappings = new HashSet<JobPanelistMapping>(0);
	private Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks = new HashSet<JobRoleSkillBenchmark>(0);

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** full constructor */
	public Job(TaskType taskType, IstarUser istarUser, Organization organization, String title, String description,
			Integer maximumExperience, Integer minimumExperience, Integer availablePositions, Integer totalPositions,
			Double maximumSalary, Double minimumSalary, String socialCode, String state, String tags,
			Timestamp createdAt, Timestamp updatedAt, String location, Set<Campaign> campaigns,
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings, Set<SkillRating> skillRatings,
			Set<JobPanelistMapping> jobPanelistMappings, Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks) {
		this.taskType = taskType;
		this.istarUser = istarUser;
		this.organization = organization;
		this.title = title;
		this.description = description;
		this.maximumExperience = maximumExperience;
		this.minimumExperience = minimumExperience;
		this.availablePositions = availablePositions;
		this.totalPositions = totalPositions;
		this.maximumSalary = maximumSalary;
		this.minimumSalary = minimumSalary;
		this.socialCode = socialCode;
		this.state = state;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.location = location;
		this.campaigns = campaigns;
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
		this.skillRatings = skillRatings;
		this.jobPanelistMappings = jobPanelistMappings;
		this.jobRoleSkillBenchmarks = jobRoleSkillBenchmarks;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_type")

	public TaskType getTaskType() {
		return this.taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recruiter")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name = "title")

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "maximum_experience")

	public Integer getMaximumExperience() {
		return this.maximumExperience;
	}

	public void setMaximumExperience(Integer maximumExperience) {
		this.maximumExperience = maximumExperience;
	}

	@Column(name = "minimum_experience")

	public Integer getMinimumExperience() {
		return this.minimumExperience;
	}

	public void setMinimumExperience(Integer minimumExperience) {
		this.minimumExperience = minimumExperience;
	}

	@Column(name = "available_positions")

	public Integer getAvailablePositions() {
		return this.availablePositions;
	}

	public void setAvailablePositions(Integer availablePositions) {
		this.availablePositions = availablePositions;
	}

	@Column(name = "total_positions")

	public Integer getTotalPositions() {
		return this.totalPositions;
	}

	public void setTotalPositions(Integer totalPositions) {
		this.totalPositions = totalPositions;
	}

	@Column(name = "maximum_salary", precision = 17, scale = 17)

	public Double getMaximumSalary() {
		return this.maximumSalary;
	}

	public void setMaximumSalary(Double maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	@Column(name = "minimum_salary", precision = 17, scale = 17)

	public Double getMinimumSalary() {
		return this.minimumSalary;
	}

	public void setMinimumSalary(Double minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	@Column(name = "social_code")

	public String getSocialCode() {
		return this.socialCode;
	}

	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}

	@Column(name = "state")

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "tags")

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 29)

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "location")

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")

	public Set<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")

	public Set<RecruiterJobTaskCollegeMapping> getRecruiterJobTaskCollegeMappings() {
		return this.recruiterJobTaskCollegeMappings;
	}

	public void setRecruiterJobTaskCollegeMappings(
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings) {
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")

	public Set<SkillRating> getSkillRatings() {
		return this.skillRatings;
	}

	public void setSkillRatings(Set<SkillRating> skillRatings) {
		this.skillRatings = skillRatings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")

	public Set<JobPanelistMapping> getJobPanelistMappings() {
		return this.jobPanelistMappings;
	}

	public void setJobPanelistMappings(Set<JobPanelistMapping> jobPanelistMappings) {
		this.jobPanelistMappings = jobPanelistMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "job")

	public Set<JobRoleSkillBenchmark> getJobRoleSkillBenchmarks() {
		return this.jobRoleSkillBenchmarks;
	}

	public void setJobRoleSkillBenchmarks(Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks) {
		this.jobRoleSkillBenchmarks = jobRoleSkillBenchmarks;
	}

}