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
 * AssessmentOption entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment_option", schema = "public")

public class AssessmentOption implements java.io.Serializable {

	// Fields

	private Integer id;
	private Question question;
	private String text;
	private Integer markingScheme;

	// Constructors

	/** default constructor */
	public AssessmentOption() {
	}

	/** minimal constructor */
	public AssessmentOption(Question question) {
		this.question = question;
	}

	/** full constructor */
	public AssessmentOption(Question question, String text, Integer markingScheme) {
		this.question = question;
		this.text = text;
		this.markingScheme = markingScheme;
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
	@JoinColumn(name = "question_id", nullable = false)

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Column(name = "text")

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "marking_scheme")

	public Integer getMarkingScheme() {
		return this.markingScheme;
	}

	public void setMarkingScheme(Integer markingScheme) {
		this.markingScheme = markingScheme;
	}

}