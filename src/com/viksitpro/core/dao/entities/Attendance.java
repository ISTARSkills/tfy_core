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
 * Attendance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attendance", schema = "public")

public class Attendance implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUserByUserId;
	private IstarUser istarUserByTakenBy;
	private String status;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer eventId;

	// Constructors

	/** default constructor */
	public Attendance() {
	}

	/** full constructor */
	public Attendance(IstarUser istarUserByUserId, IstarUser istarUserByTakenBy, String status, Timestamp createdAt,
			Timestamp updatedAt, Integer eventId) {
		this.istarUserByUserId = istarUserByUserId;
		this.istarUserByTakenBy = istarUserByTakenBy;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.eventId = eventId;
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
	@JoinColumn(name = "user_id")

	public IstarUser getIstarUserByUserId() {
		return this.istarUserByUserId;
	}

	public void setIstarUserByUserId(IstarUser istarUserByUserId) {
		this.istarUserByUserId = istarUserByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "taken_by")

	public IstarUser getIstarUserByTakenBy() {
		return this.istarUserByTakenBy;
	}

	public void setIstarUserByTakenBy(IstarUser istarUserByTakenBy) {
		this.istarUserByTakenBy = istarUserByTakenBy;
	}

	@Column(name = "status")

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "event_id")

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

}