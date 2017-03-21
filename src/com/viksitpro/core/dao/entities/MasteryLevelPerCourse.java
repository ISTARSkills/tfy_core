package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MasteryLevelPerCourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mastery_level_per_course", schema = "public")

public class MasteryLevelPerCourse implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer courseId;
	private Integer collegeId;
	private Integer batchGroupId;
	private Integer skillId;
	private Integer master;
	private Integer rookie;
	private Integer apprentice;
	private Integer wizard;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public MasteryLevelPerCourse() {
	}

	/** full constructor */
	public MasteryLevelPerCourse(Integer courseId, Integer collegeId, Integer batchGroupId, Integer skillId,
			Integer master, Integer rookie, Integer apprentice, Integer wizard, Timestamp createdAt) {
		this.courseId = courseId;
		this.collegeId = collegeId;
		this.batchGroupId = batchGroupId;
		this.skillId = skillId;
		this.master = master;
		this.rookie = rookie;
		this.apprentice = apprentice;
		this.wizard = wizard;
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

	@Column(name = "college_id")

	public Integer getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "batch_group_id")

	public Integer getBatchGroupId() {
		return this.batchGroupId;
	}

	public void setBatchGroupId(Integer batchGroupId) {
		this.batchGroupId = batchGroupId;
	}

	@Column(name = "skill_id")

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@Column(name = "master")

	public Integer getMaster() {
		return this.master;
	}

	public void setMaster(Integer master) {
		this.master = master;
	}

	@Column(name = "rookie")

	public Integer getRookie() {
		return this.rookie;
	}

	public void setRookie(Integer rookie) {
		this.rookie = rookie;
	}

	@Column(name = "apprentice")

	public Integer getApprentice() {
		return this.apprentice;
	}

	public void setApprentice(Integer apprentice) {
		this.apprentice = apprentice;
	}

	@Column(name = "wizard")

	public Integer getWizard() {
		return this.wizard;
	}

	public void setWizard(Integer wizard) {
		this.wizard = wizard;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}