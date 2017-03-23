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
 * SubUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sub_user", schema = "public")

public class SubUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUserByIstarUser;
	private IstarUser istarUserBySubIstarUser;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public SubUser() {
	}

	/** full constructor */
	public SubUser(IstarUser istarUserByIstarUser, IstarUser istarUserBySubIstarUser, Timestamp createdAt,
			Timestamp updatedAt) {
		this.istarUserByIstarUser = istarUserByIstarUser;
		this.istarUserBySubIstarUser = istarUserBySubIstarUser;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "istar_user")

	public IstarUser getIstarUserByIstarUser() {
		return this.istarUserByIstarUser;
	}

	public void setIstarUserByIstarUser(IstarUser istarUserByIstarUser) {
		this.istarUserByIstarUser = istarUserByIstarUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sub_istar_user")

	public IstarUser getIstarUserBySubIstarUser() {
		return this.istarUserBySubIstarUser;
	}

	public void setIstarUserBySubIstarUser(IstarUser istarUserBySubIstarUser) {
		this.istarUserBySubIstarUser = istarUserBySubIstarUser;
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