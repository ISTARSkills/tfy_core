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
 * TaskFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task_feedback", schema = "public")

public class TaskFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Task task;
	private String status;
	private String feedback;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String stage;
	private Integer rating;

	// Constructors

	/** default constructor */
	public TaskFeedback() {
	}

	/** full constructor */
	public TaskFeedback(IstarUser istarUser, Task task, String status, String feedback, Timestamp createdAt,
			Timestamp updatedAt, String stage, Integer rating) {
		this.istarUser = istarUser;
		this.task = task;
		this.status = status;
		this.feedback = feedback;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.stage = stage;
		this.rating = rating;
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

	@Column(name = "feedback")

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	@Column(name = "stage")

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name = "rating")

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}