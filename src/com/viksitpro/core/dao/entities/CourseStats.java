package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CourseStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course_stats", schema = "public")

public class CourseStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseId;
	private String courseName;
	private Float attendancePerc;
	private Integer avgFeedback;
	private Integer stuEnrolled;
	private Float completionPerc;
	private Timestamp createdAt;
	private Integer batchGroupId;
	private Integer collegeId;
	private String courseDescription;

	// Constructors

	/** default constructor */
	public CourseStats() {
	}

	/** full constructor */
	public CourseStats(Integer courseId, String courseName, Float attendancePerc, Integer avgFeedback,
			Integer stuEnrolled, Float completionPerc, Timestamp createdAt, Integer batchGroupId, Integer collegeId,
			String courseDescription) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.attendancePerc = attendancePerc;
		this.avgFeedback = avgFeedback;
		this.stuEnrolled = stuEnrolled;
		this.completionPerc = completionPerc;
		this.createdAt = createdAt;
		this.batchGroupId = batchGroupId;
		this.collegeId = collegeId;
		this.courseDescription = courseDescription;
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

	@Column(name = "course_name")

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	@Column(name = "college_id")

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "course_description")

	public String getCourseDescription() {
		return this.courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

}