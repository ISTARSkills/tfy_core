package com.viksitpro.core.dao.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * JobRoleSkillBenchmark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job_role_skill_benchmark", schema = "public")

public class JobRoleSkillBenchmark implements java.io.Serializable {

	// Fields

	private JobRoleSkillBenchmarkId id;
	private Job job;
	private SkillObjective skillObjective;

	// Constructors

	/** default constructor */
	public JobRoleSkillBenchmark() {
	}

	/** minimal constructor */
	public JobRoleSkillBenchmark(JobRoleSkillBenchmarkId id) {
		this.id = id;
	}

	/** full constructor */
	public JobRoleSkillBenchmark(JobRoleSkillBenchmarkId id, Job job, SkillObjective skillObjective) {
		this.id = id;
		this.job = job;
		this.skillObjective = skillObjective;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "vacancyId", column = @Column(name = "vacancy_id")),
			@AttributeOverride(name = "skillId", column = @Column(name = "skill_id")),
			@AttributeOverride(name = "masterLevel", column = @Column(name = "master_level", precision = 8, scale = 8)),
			@AttributeOverride(name = "wizardLevel", column = @Column(name = "wizard_level", precision = 8, scale = 8)),
			@AttributeOverride(name = "apprenticeLevel", column = @Column(name = "apprentice_level", precision = 8, scale = 8)),
			@AttributeOverride(name = "rookieLevel", column = @Column(name = "rookie_level", precision = 8, scale = 8)),
			@AttributeOverride(name = "createdAt", column = @Column(name = "created_at", length = 29)) })

	public JobRoleSkillBenchmarkId getId() {
		return this.id;
	}

	public void setId(JobRoleSkillBenchmarkId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vacancy_id", insertable = false, updatable = false)

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id", insertable = false, updatable = false)

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

}