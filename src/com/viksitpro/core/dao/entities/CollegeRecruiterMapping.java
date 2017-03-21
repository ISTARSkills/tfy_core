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
 * CollegeRecruiterMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "college_recruiter_mapping", schema = "public")

public class CollegeRecruiterMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Organization organization;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String status;
	private String message;

	// Constructors

	/** default constructor */
	public CollegeRecruiterMapping() {
	}

	/** full constructor */
	public CollegeRecruiterMapping(IstarUser istarUser, Organization organization, Timestamp createdAt,
			Timestamp updatedAt, String status, String message) {
		this.istarUser = istarUser;
		this.organization = organization;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.message = message;
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
	@JoinColumn(name = "istar_user")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	@Column(name = "status")

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "message")

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}