package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SkillRating entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "skill_rating", schema = "public")

public class SkillRating implements java.io.Serializable {

	// Fields

	private Integer id;
	private SkillObjective skillObjective;
	private StageLog stageLog;
	private Job job;
	private Integer rating;
	private String comment;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public SkillRating() {
	}

	/** full constructor */
	public SkillRating(SkillObjective skillObjective, StageLog stageLog, Job job, Integer rating, String comment,
			Timestamp createdAt, Timestamp updatedAt) {
		this.skillObjective = skillObjective;
		this.stageLog = stageLog;
		this.job = job;
		this.rating = rating;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	@JoinColumn(name = "skill_id")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stage_log_id")

	public StageLog getStageLog() {
		return this.stageLog;
	}

	public void setStageLog(StageLog stageLog) {
		this.stageLog = stageLog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Column(name = "rating")

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "comment")

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

}