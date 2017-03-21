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
 * TaskLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task_log", schema = "public")

public class TaskLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Task task;
	private String status;
	private String entityType;
	private String title;
	private String jsessionId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String body;

	// Constructors

	/** default constructor */
	public TaskLog() {
	}

	/** full constructor */
	public TaskLog(IstarUser istarUser, Task task, String status, String entityType, String title, String jsessionId,
			Timestamp createdAt, Timestamp updatedAt, String body) {
		this.istarUser = istarUser;
		this.task = task;
		this.status = status;
		this.entityType = entityType;
		this.title = title;
		this.jsessionId = jsessionId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.body = body;
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
	@JoinColumn(name = "pm_member")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task")

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Column(name = "status")

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "entity_type")

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Column(name = "title")

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "jsession_id", length = 32)

	public String getJsessionId() {
		return this.jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
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

	@Column(name = "body")

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}