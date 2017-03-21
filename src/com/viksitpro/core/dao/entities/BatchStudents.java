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
 * BatchStudents entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch_students", schema = "public")

public class BatchStudents implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private BatchGroup batchGroup;
	private String userType;

	// Constructors

	/** default constructor */
	public BatchStudents() {
	}

	/** minimal constructor */
	public BatchStudents(IstarUser istarUser, BatchGroup batchGroup) {
		this.istarUser = istarUser;
		this.batchGroup = batchGroup;
	}

	/** full constructor */
	public BatchStudents(IstarUser istarUser, BatchGroup batchGroup, String userType) {
		this.istarUser = istarUser;
		this.batchGroup = batchGroup;
		this.userType = userType;
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
	@JoinColumn(name = "student_id", nullable = false)

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_group_id", nullable = false)

	public BatchGroup getBatchGroup() {
		return this.batchGroup;
	}

	public void setBatchGroup(BatchGroup batchGroup) {
		this.batchGroup = batchGroup;
	}

	@Column(name = "user_type")

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}