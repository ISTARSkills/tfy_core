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
 * ExceptionLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "exception_log", schema = "public")

public class ExceptionLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Organization organization;
	private IstarUser istarUser;
	private String exceptionType;
	private String description;
	private String exceptionComponent;
	private Timestamp createdAt;
	private Integer eventId;
	private String level;

	// Constructors

	/** default constructor */
	public ExceptionLog() {
	}

	/** full constructor */
	public ExceptionLog(Organization organization, IstarUser istarUser, String exceptionType, String description,
			String exceptionComponent, Timestamp createdAt, Integer eventId, String level) {
		this.organization = organization;
		this.istarUser = istarUser;
		this.exceptionType = exceptionType;
		this.description = description;
		this.exceptionComponent = exceptionComponent;
		this.createdAt = createdAt;
		this.eventId = eventId;
		this.level = level;
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
	@JoinColumn(name = "organization_id")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainer_id")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "exception_type")

	public String getExceptionType() {
		return this.exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	@Column(name = "description")

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "exception_component")

	public String getExceptionComponent() {
		return this.exceptionComponent;
	}

	public void setExceptionComponent(String exceptionComponent) {
		this.exceptionComponent = exceptionComponent;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "event_id")

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "level")

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}