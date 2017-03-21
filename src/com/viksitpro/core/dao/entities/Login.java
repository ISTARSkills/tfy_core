package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "login", schema = "public")

public class Login implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Timestamp createdAt;
	private String jsessionId;
	private String action;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** full constructor */
	public Login(Integer userId, Timestamp createdAt, String jsessionId, String action) {
		this.userId = userId;
		this.createdAt = createdAt;
		this.jsessionId = jsessionId;
		this.action = action;
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

	@Column(name = "user_id")

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "jsession_id")

	public String getJsessionId() {
		return this.jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	@Column(name = "action")

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}