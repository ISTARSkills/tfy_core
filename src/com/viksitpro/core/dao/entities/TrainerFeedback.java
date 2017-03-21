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
 * TrainerFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainer_feedback", schema = "public")

public class TrainerFeedback implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Batch batch;
	private String eventId;
	private Integer rating;
	private Boolean noise;
	private Boolean attendance;
	private Boolean sick;
	private Boolean content;
	private Boolean assignment;
	private Boolean internals;
	private Boolean internet;
	private Boolean electricity;
	private Boolean time;
	private String comments;

	// Constructors

	/** default constructor */
	public TrainerFeedback() {
	}

	/** minimal constructor */
	public TrainerFeedback(Boolean noise, Boolean attendance) {
		this.noise = noise;
		this.attendance = attendance;
	}

	/** full constructor */
	public TrainerFeedback(IstarUser istarUser, Batch batch, String eventId, Integer rating, Boolean noise,
			Boolean attendance, Boolean sick, Boolean content, Boolean assignment, Boolean internals, Boolean internet,
			Boolean electricity, Boolean time, String comments) {
		this.istarUser = istarUser;
		this.batch = batch;
		this.eventId = eventId;
		this.rating = rating;
		this.noise = noise;
		this.attendance = attendance;
		this.sick = sick;
		this.content = content;
		this.assignment = assignment;
		this.internals = internals;
		this.internet = internet;
		this.electricity = electricity;
		this.time = time;
		this.comments = comments;
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

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	@Column(name = "event_id")

	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Column(name = "rating")

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Column(name = "noise", nullable = false)

	public Boolean getNoise() {
		return this.noise;
	}

	public void setNoise(Boolean noise) {
		this.noise = noise;
	}

	@Column(name = "attendance", nullable = false)

	public Boolean getAttendance() {
		return this.attendance;
	}

	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}

	@Column(name = "sick")

	public Boolean getSick() {
		return this.sick;
	}

	public void setSick(Boolean sick) {
		this.sick = sick;
	}

	@Column(name = "content")

	public Boolean getContent() {
		return this.content;
	}

	public void setContent(Boolean content) {
		this.content = content;
	}

	@Column(name = "assignment")

	public Boolean getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Boolean assignment) {
		this.assignment = assignment;
	}

	@Column(name = "internals")

	public Boolean getInternals() {
		return this.internals;
	}

	public void setInternals(Boolean internals) {
		this.internals = internals;
	}

	@Column(name = "internet")

	public Boolean getInternet() {
		return this.internet;
	}

	public void setInternet(Boolean internet) {
		this.internet = internet;
	}

	@Column(name = "electricity")

	public Boolean getElectricity() {
		return this.electricity;
	}

	public void setElectricity(Boolean electricity) {
		this.electricity = electricity;
	}

	@Column(name = "time")

	public Boolean getTime() {
		return this.time;
	}

	public void setTime(Boolean time) {
		this.time = time;
	}

	@Column(name = "comments")

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}