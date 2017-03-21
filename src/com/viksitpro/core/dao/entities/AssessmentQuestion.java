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
 * AssessmentQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment_question", schema = "public")

public class AssessmentQuestion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Assessment assessment;
	private Question question;
	private Timestamp createdAt;
	private Integer orderId;

	// Constructors

	/** default constructor */
	public AssessmentQuestion() {
	}

	/** minimal constructor */
	public AssessmentQuestion(Assessment assessment, Question question) {
		this.assessment = assessment;
		this.question = question;
	}

	/** full constructor */
	public AssessmentQuestion(Assessment assessment, Question question, Timestamp createdAt, Integer orderId) {
		this.assessment = assessment;
		this.question = question;
		this.createdAt = createdAt;
		this.orderId = orderId;
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
	@JoinColumn(name = "assessmentid", nullable = false)

	public Assessment getAssessment() {
		return this.assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionid", nullable = false)

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "order_id")

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}