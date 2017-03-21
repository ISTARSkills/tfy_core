package com.viksitpro.core.dao.entities;

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
 * JobPanelistMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job_panelist_mapping", schema = "public")

public class JobPanelistMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private Job job;
	private IstarUser istarUser;

	// Constructors

	/** default constructor */
	public JobPanelistMapping() {
	}

	/** full constructor */
	public JobPanelistMapping(Job job, IstarUser istarUser) {
		this.job = job;
		this.istarUser = istarUser;
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
	@JoinColumn(name = "job")

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panelist")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

}