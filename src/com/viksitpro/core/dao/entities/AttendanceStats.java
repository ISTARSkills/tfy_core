package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * AttendanceStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendance_stats", schema = "public")

public class AttendanceStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseId;
	private Integer batchGroupId;
	private Float percentageAttendance;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public AttendanceStats() {
	}

	/** full constructor */
	public AttendanceStats(Integer courseId, Integer batchGroupId, Float percentageAttendance, Timestamp createdAt) {
		this.courseId = courseId;
		this.batchGroupId = batchGroupId;
		this.percentageAttendance = percentageAttendance;
		this.createdAt = createdAt;
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

	@Column(name = "course_id")

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "batch_group_id")

	public Integer getBatchGroupId() {
		return this.batchGroupId;
	}

	public void setBatchGroupId(Integer batchGroupId) {
		this.batchGroupId = batchGroupId;
	}

	@Column(name = "percentage_attendance", precision = 8, scale = 8)

	public Float getPercentageAttendance() {
		return this.percentageAttendance;
	}

	public void setPercentageAttendance(Float percentageAttendance) {
		this.percentageAttendance = percentageAttendance;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}