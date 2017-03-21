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
 * Campaign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "campaign", schema = "public")

public class Campaign implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUserByRecruiter;
	private Job job;
	private IstarUser istarUserByOwner;
	private Task task;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String type;
	private Boolean isActive;

	// Constructors

	/** default constructor */
	public Campaign() {
	}

	/** full constructor */
	public Campaign(IstarUser istarUserByRecruiter, Job job, IstarUser istarUserByOwner, Task task, Timestamp createdAt,
			Timestamp updatedAt, String type, Boolean isActive) {
		this.istarUserByRecruiter = istarUserByRecruiter;
		this.job = job;
		this.istarUserByOwner = istarUserByOwner;
		this.task = task;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.type = type;
		this.isActive = isActive;
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

	public IstarUser getIstarUserByRecruiter() {
		return this.istarUserByRecruiter;
	}

	public void setIstarUserByRecruiter(IstarUser istarUserByRecruiter) {
		this.istarUserByRecruiter = istarUserByRecruiter;
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
	@JoinColumn(name = "owner")

	public IstarUser getIstarUserByOwner() {
		return this.istarUserByOwner;
	}

	public void setIstarUserByOwner(IstarUser istarUserByOwner) {
		this.istarUserByOwner = istarUserByOwner;
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

	@Column(name = "type")

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "is_active")

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}