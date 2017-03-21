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
 * RecruiterJobTaskCollegeMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "recruiter_job_task_college_mapping", schema = "public")

public class RecruiterJobTaskCollegeMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Job job;
	private Organization organization;
	private Task task;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public RecruiterJobTaskCollegeMapping() {
	}

	/** full constructor */
	public RecruiterJobTaskCollegeMapping(IstarUser istarUser, Job job, Organization organization, Task task,
			Timestamp createdAt, Timestamp updatedAt) {
		this.istarUser = istarUser;
		this.job = job;
		this.organization = organization;
		this.task = task;
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
	@JoinColumn(name = "recruiter")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job")

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task")

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
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