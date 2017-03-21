package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * StudentBadge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_badge", schema = "public")

public class StudentBadge implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer studentId;
	private Integer badgeId;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public StudentBadge() {
	}

	/** full constructor */
	public StudentBadge(Integer studentId, Integer badgeId, Timestamp createdAt, Timestamp updatedAt) {
		this.studentId = studentId;
		this.badgeId = badgeId;
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

	@Column(name = "student_id")

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "badge_id")

	public Integer getBadgeId() {
		return this.badgeId;
	}

	public void setBadgeId(Integer badgeId) {
		this.badgeId = badgeId;
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