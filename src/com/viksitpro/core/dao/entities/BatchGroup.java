package com.viksitpro.core.dao.entities;

import java.sql.Date;
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
 * BatchGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch_group", schema = "public")

public class BatchGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private Organization organization;
	private Timestamp createdAt;
	private String name;
	private Timestamp updatedAt;
	private String batchCode;
	private Integer assessmentId;
	private String bgDesc;
	private Set<Batch> batchs = new HashSet<Batch>(0);
	private Set<BatchStudents> batchStudentses = new HashSet<BatchStudents>(0);
	private Set<Notification> notifications = new HashSet<Notification>(0);
	private Integer parentGroupId;
	private String type;
	private Integer year; 
	private Boolean isPrimary;
	private Boolean isHistorical;
	private String modeType ;
	private Date startDate;
	private Integer numberOfStudents;
	// Constructors

	/** default constructor */
	public BatchGroup() {
	}

	/** minimal constructor */
	public BatchGroup(Timestamp createdAt, String name, Timestamp updatedAt) {
		this.createdAt = createdAt;
		this.name = name;
		this.updatedAt = updatedAt;
	}

	/** full constructor */
	public BatchGroup(Organization organization, Timestamp createdAt, String name, Timestamp updatedAt,
			String batchCode, Integer assessmentId, String bgDesc, Set<Batch> batchs,
			Set<BatchStudents> batchStudentses, Set<Notification> notifications) {
		this.organization = organization;
		this.createdAt = createdAt;
		this.name = name;
		this.updatedAt = updatedAt;
		this.batchCode = batchCode;
		this.assessmentId = assessmentId;
		this.bgDesc = bgDesc;
		this.batchs = batchs;
		this.batchStudentses = batchStudentses;
		this.notifications = notifications;
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
	@JoinColumn(name = "college_id")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name = "created_at", nullable = false, length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "name", nullable = false)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "updated_at", nullable = false, length = 29)

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "batch_code")

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Column(name = "assessment_id")

	public Integer getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	@Column(name = "bg_desc")

	public String getBgDesc() {
		return this.bgDesc;
	}

	public void setBgDesc(String bgDesc) {
		this.bgDesc = bgDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batchGroup")

	public Set<Batch> getBatchs() {
		return this.batchs;
	}

	public void setBatchs(Set<Batch> batchs) {
		this.batchs = batchs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batchGroup")

	public Set<BatchStudents> getBatchStudentses() {
		return this.batchStudentses;
	}

	public void setBatchStudentses(Set<BatchStudents> batchStudentses) {
		this.batchStudentses = batchStudentses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batchGroup")

	public Set<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	@Column(name = "parent_group_id", nullable = true)
	public Integer getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(Integer parentBatchGroupId) {
		this.parentGroupId = parentBatchGroupId;
	}
	@Column(name = "type", nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "year", nullable = true)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "is_primary", nullable = true)
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Column(name = "mode_type", nullable = true)
	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	@Column(name = "start_date", nullable = true)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "is_historical_group", nullable = true)
	public Boolean getIsHistorical() {
		return isHistorical;
	}

	public void setIsHistorical(Boolean isHistorical) {
		this.isHistorical = isHistorical;
	}

	@Column(name = "enrolled_students", nullable = true)
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	
	
	

}