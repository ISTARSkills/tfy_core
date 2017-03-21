package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BatchStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch_stats", schema = "public")

public class BatchStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer batchId;
	private String batchName;
	private Float attendancePerc;
	private Integer avgFeedback;
	private Integer stuEnrolled;
	private Float completionPerc;
	private Integer collegeId;
	private Timestamp createdAt;
	private Integer batchGroupId;

	// Constructors

	/** default constructor */
	public BatchStats() {
	}

	/** full constructor */
	public BatchStats(Integer batchId, String batchName, Float attendancePerc, Integer avgFeedback, Integer stuEnrolled,
			Float completionPerc, Integer collegeId, Timestamp createdAt, Integer batchGroupId) {
		this.batchId = batchId;
		this.batchName = batchName;
		this.attendancePerc = attendancePerc;
		this.avgFeedback = avgFeedback;
		this.stuEnrolled = stuEnrolled;
		this.completionPerc = completionPerc;
		this.collegeId = collegeId;
		this.createdAt = createdAt;
		this.batchGroupId = batchGroupId;
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

	@Column(name = "batch_id")

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Column(name = "batch_name")

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	@Column(name = "attendance_perc", precision = 8, scale = 8)

	public Float getAttendancePerc() {
		return this.attendancePerc;
	}

	public void setAttendancePerc(Float attendancePerc) {
		this.attendancePerc = attendancePerc;
	}

	@Column(name = "avg_feedback")

	public Integer getAvgFeedback() {
		return this.avgFeedback;
	}

	public void setAvgFeedback(Integer avgFeedback) {
		this.avgFeedback = avgFeedback;
	}

	@Column(name = "stu_enrolled")

	public Integer getStuEnrolled() {
		return this.stuEnrolled;
	}

	public void setStuEnrolled(Integer stuEnrolled) {
		this.stuEnrolled = stuEnrolled;
	}

	@Column(name = "completion_perc", precision = 8, scale = 8)

	public Float getCompletionPerc() {
		return this.completionPerc;
	}

	public void setCompletionPerc(Float completionPerc) {
		this.completionPerc = completionPerc;
	}

	@Column(name = "college_id")

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "batch_group_id")

	public Integer getBatchGroupId() {
		return this.batchGroupId;
	}

	public void setBatchGroupId(Integer batchGroupId) {
		this.batchGroupId = batchGroupId;
	}

}