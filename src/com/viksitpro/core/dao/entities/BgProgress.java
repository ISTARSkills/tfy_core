package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BgProgress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bg_progress", schema = "public")

public class BgProgress implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp createdAt;
	private Integer collegeId;
	private String collegeName;
	private Integer batchGroupId;
	private String batchGroupName;
	private Float avgScore;

	// Constructors

	/** default constructor */
	public BgProgress() {
	}

	/** full constructor */
	public BgProgress(Timestamp createdAt, Integer collegeId, String collegeName, Integer batchGroupId,
			String batchGroupName, Float avgScore) {
		this.createdAt = createdAt;
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.batchGroupId = batchGroupId;
		this.batchGroupName = batchGroupName;
		this.avgScore = avgScore;
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

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "college_id")

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "college_name")

	public String getCollegeName() {
		return this.collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Column(name = "batch_group_id")

	public Integer getBatchGroupId() {
		return this.batchGroupId;
	}

	public void setBatchGroupId(Integer batchGroupId) {
		this.batchGroupId = batchGroupId;
	}

	@Column(name = "batch_group_name")

	public String getBatchGroupName() {
		return this.batchGroupName;
	}

	public void setBatchGroupName(String batchGroupName) {
		this.batchGroupName = batchGroupName;
	}

	@Column(name = "avg_score", precision = 8, scale = 8)

	public Float getAvgScore() {
		return this.avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

}