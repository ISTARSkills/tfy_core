package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * OpsEventSessionLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ops_event_session_log", schema = "public")

public class OpsEventSessionLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer trainerId;
	private String eventId;
	private Integer courseId;
	private Integer cmsessionId;
	private Integer lessonId;
	private Integer pptId;
	private Integer slideId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer batchId;
	private Integer moduleId;
	private Integer assessmentId;
	private String lessonType;
	private String url;

	// Constructors

	/** default constructor */
	public OpsEventSessionLog() {
	}

	/** full constructor */
	public OpsEventSessionLog(Integer trainerId, String eventId, Integer courseId, Integer cmsessionId,
			Integer lessonId, Integer pptId, Integer slideId, Timestamp createdAt, Timestamp updatedAt, Integer batchId,
			Integer moduleId, Integer assessmentId, String lessonType, String url) {
		this.trainerId = trainerId;
		this.eventId = eventId;
		this.courseId = courseId;
		this.cmsessionId = cmsessionId;
		this.lessonId = lessonId;
		this.pptId = pptId;
		this.slideId = slideId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.batchId = batchId;
		this.moduleId = moduleId;
		this.assessmentId = assessmentId;
		this.lessonType = lessonType;
		this.url = url;
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

	@Column(name = "trainer_id")

	public Integer getTrainerId() {
		return this.trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	@Column(name = "event_id")

	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "course_id")

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "cmsession_id")

	public Integer getCmsessionId() {
		return this.cmsessionId;
	}

	public void setCmsessionId(Integer cmsessionId) {
		this.cmsessionId = cmsessionId;
	}

	@Column(name = "lesson_id")

	public Integer getLessonId() {
		return this.lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	@Column(name = "ppt_id")

	public Integer getPptId() {
		return this.pptId;
	}

	public void setPptId(Integer pptId) {
		this.pptId = pptId;
	}

	@Column(name = "slide_id")

	public Integer getSlideId() {
		return this.slideId;
	}

	public void setSlideId(Integer slideId) {
		this.slideId = slideId;
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

	@Column(name = "batch_id")

	public Integer getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Column(name = "module_id")

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "assessment_id")

	public Integer getAssessmentId() {
		return this.assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	@Column(name = "lesson_type")

	public String getLessonType() {
		return this.lessonType;
	}

	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	@Column(name = "url")

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}