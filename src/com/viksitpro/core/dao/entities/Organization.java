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
 * Organization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "organization", schema = "public")

public class Organization implements java.io.Serializable {

	// Fields

	private Integer id;
	private Address address;
	private String name;
	private String orgType;
	private String industry;
	private String profile;
	private String image;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String website;
	private Integer founded;
	private String employeeCount;
	private String contactName;
	private String contactEmail;
	private Long contactPhone;
	private Boolean isCompany;
	private Set<BatchGroup> batchGroups = new HashSet<BatchGroup>(0);
	private Set<ExceptionLog> exceptionLogs = new HashSet<ExceptionLog>(0);
	private Set<Job> jobs = new HashSet<Job>(0);
	private Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings = new HashSet<RecruiterJobTaskCollegeMapping>(
			0);
	private Set<CollegeRecruiterMapping> collegeRecruiterMappings = new HashSet<CollegeRecruiterMapping>(0);
	private Set<ClassroomDetails> classroomDetailses = new HashSet<ClassroomDetails>(0);
	private Set<UserOrgMapping> userOrgMappings = new HashSet<UserOrgMapping>(0);

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(String name, String orgType) {
		this.name = name;
		this.orgType = orgType;
	}

	/** full constructor */
	public Organization(Address address, String name, String orgType, String industry, String profile, String image,
			Timestamp createdAt, Timestamp updatedAt, String website, Integer founded, String employeeCount,
			String contactName, String contactEmail, Long contactPhone, Set<BatchGroup> batchGroups,
			Set<ExceptionLog> exceptionLogs, Set<Job> jobs,
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings,
			Set<CollegeRecruiterMapping> collegeRecruiterMappings, Set<ClassroomDetails> classroomDetailses,
			Set<UserOrgMapping> userOrgMappings,Boolean isCompany) {
		this.address = address;
		this.name = name;
		this.orgType = orgType;
		this.industry = industry;
		this.profile = profile;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.website = website;
		this.founded = founded;
		this.employeeCount = employeeCount;
		this.contactName = contactName;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.batchGroups = batchGroups;
		this.exceptionLogs = exceptionLogs;
		this.jobs = jobs;
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
		this.collegeRecruiterMappings = collegeRecruiterMappings;
		this.classroomDetailses = classroomDetailses;
		this.userOrgMappings = userOrgMappings;
		this.isCompany = isCompany;
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
	@JoinColumn(name = "address_id")

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "name", nullable = false)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Boolean getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(Boolean isCompany) {
		this.isCompany = isCompany;
	}

	@Column(name = "org_type", nullable = false)

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Column(name = "industry")

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "profile")

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Column(name = "image")

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Column(name = "website")

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "founded")

	public Integer getFounded() {
		return this.founded;
	}

	public void setFounded(Integer founded) {
		this.founded = founded;
	}

	@Column(name = "employee_count")

	public String getEmployeeCount() {
		return this.employeeCount;
	}

	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}

	@Column(name = "contact_name")

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "contact_email")

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name = "contact_phone")

	public Long getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(Long contactPhone) {
		this.contactPhone = contactPhone;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<BatchGroup> getBatchGroups() {
		return this.batchGroups;
	}

	public void setBatchGroups(Set<BatchGroup> batchGroups) {
		this.batchGroups = batchGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<ExceptionLog> getExceptionLogs() {
		return this.exceptionLogs;
	}

	public void setExceptionLogs(Set<ExceptionLog> exceptionLogs) {
		this.exceptionLogs = exceptionLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<RecruiterJobTaskCollegeMapping> getRecruiterJobTaskCollegeMappings() {
		return this.recruiterJobTaskCollegeMappings;
	}

	public void setRecruiterJobTaskCollegeMappings(
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings) {
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<CollegeRecruiterMapping> getCollegeRecruiterMappings() {
		return this.collegeRecruiterMappings;
	}

	public void setCollegeRecruiterMappings(Set<CollegeRecruiterMapping> collegeRecruiterMappings) {
		this.collegeRecruiterMappings = collegeRecruiterMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<ClassroomDetails> getClassroomDetailses() {
		return this.classroomDetailses;
	}

	public void setClassroomDetailses(Set<ClassroomDetails> classroomDetailses) {
		this.classroomDetailses = classroomDetailses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")

	public Set<UserOrgMapping> getUserOrgMappings() {
		return this.userOrgMappings;
	}

	public void setUserOrgMappings(Set<UserOrgMapping> userOrgMappings) {
		this.userOrgMappings = userOrgMappings;
	}

}