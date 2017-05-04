package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

/**
 * Question entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "question", schema = "public")

public class Question implements java.io.Serializable {

	// Fields

	private Integer id;
	private String questionText;
	private String questionType;
	private Timestamp createdAt;
	private Integer difficultyLevel;
	private Integer specifier;
	private Integer durationInSec;
	private String explanation;
	private String comprehensivePassageText;
	private Integer points;
	private Set<AssessmentQuestion> assessmentQuestions = new HashSet<AssessmentQuestion>(0);
	private Set<StudentAssessment> studentAssessments = new HashSet<StudentAssessment>(0);
	private Set<SkillObjective> skillObjectives = new HashSet<SkillObjective>(0);
	private Set<AssessmentOption> assessmentOptions = new HashSet<AssessmentOption>(0);
	private Integer context_id;

	// Constructors

	/** default constructor */
	public Question() {
	}

	/** minimal constructor */
	public Question(String questionText, String questionType, Timestamp createdAt, Integer difficultyLevel) {
		this.questionText = questionText;
		this.questionType = questionType;
		this.createdAt = createdAt;
		this.difficultyLevel = difficultyLevel;
	}

	/** full constructor */
	public Question(String questionText, String questionType, Timestamp createdAt, Integer difficultyLevel,
			Integer specifier, Integer durationInSec, String explanation, String comprehensivePassageText,
			Integer points, Set<AssessmentQuestion> assessmentQuestions, Set<StudentAssessment> studentAssessments,
			Set<SkillObjective> skillObjectives, Set<AssessmentOption> assessmentOptions) {
		this.questionText = questionText;
		this.questionType = questionType;
		this.createdAt = createdAt;
		this.difficultyLevel = difficultyLevel;
		this.specifier = specifier;
		this.durationInSec = durationInSec;
		this.explanation = explanation;
		this.comprehensivePassageText = comprehensivePassageText;
		this.points = points;
		this.assessmentQuestions = assessmentQuestions;
		this.studentAssessments = studentAssessments;
		this.skillObjectives = skillObjectives;
		this.assessmentOptions = assessmentOptions;
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

	@Column(name = "question_text", nullable = false)

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Column(name = "question_type", nullable = false)

	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Column(name = "created_at", nullable = false, length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "difficulty_level", nullable = false)

	public Integer getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@Column(name = "specifier")

	public Integer getSpecifier() {
		return this.specifier;
	}

	public void setSpecifier(Integer specifier) {
		this.specifier = specifier;
	}

	@Column(name = "duration_in_sec")

	public Integer getDurationInSec() {
		return this.durationInSec;
	}

	public void setDurationInSec(Integer durationInSec) {
		this.durationInSec = durationInSec;
	}

	@Column(name = "explanation")

	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Column(name = "comprehensive_passage_text")

	public String getComprehensivePassageText() {
		return this.comprehensivePassageText;
	}

	public void setComprehensivePassageText(String comprehensivePassageText) {
		this.comprehensivePassageText = comprehensivePassageText;
	}

	@Column(name = "points")

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")

	public Set<AssessmentQuestion> getAssessmentQuestions() {
		return this.assessmentQuestions;
	}

	public void setAssessmentQuestions(Set<AssessmentQuestion> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")

	public Set<StudentAssessment> getStudentAssessments() {
		return this.studentAssessments;
	}

	public void setStudentAssessments(Set<StudentAssessment> studentAssessments) {
		this.studentAssessments = studentAssessments;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "questions")

	public Set<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(Set<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
	@OrderBy(clause = "id")
	public Set<AssessmentOption> getAssessmentOptions() {
		return this.assessmentOptions;
	}

	public void setAssessmentOptions(Set<AssessmentOption> assessmentOptions) {
		this.assessmentOptions = assessmentOptions;
	}

	@Column(name = "context_id")
	public Integer getContext_id() {
		return context_id;
	}

	public void setContext_id(Integer context_id) {
		this.context_id = context_id;
	}

}