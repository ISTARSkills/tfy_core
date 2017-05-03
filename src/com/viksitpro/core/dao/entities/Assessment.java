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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

/**
 * Assessment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment", schema = "public")

public class Assessment implements java.io.Serializable {

	// Fields

	private Integer id;
	private String assessmentType;
	private Timestamp createdAt;
	private Integer numberOfQuestions;
	private Integer assessmentdurationhours;
	private Integer assessmentdurationminutes;
	private String assessmenttitle;
	private Boolean retryAble;
	private String category;
	private String description;
	private Integer course;
	private Set<AssessmentQuestion> assessmentQuestions = new HashSet<AssessmentQuestion>(0);
	private Set<Report> reports = new HashSet<Report>(0);
	private Set<StudentAssessment> studentAssessments = new HashSet<StudentAssessment>(0);
	private Set<AssessmentBenchmark> assessmentBenchmarks = new HashSet<AssessmentBenchmark>(0);

	// Constructors

	/** default constructor */
	public Assessment() {
	}

	/** full constructor */
	public Assessment(String assessmentType, Timestamp createdAt, Integer numberOfQuestions,
			Integer assessmentdurationhours, Integer assessmentdurationminutes, String assessmenttitle, String description,
			Boolean retryAble, String category, Integer course, Set<AssessmentQuestion> assessmentQuestions, Set<Report> reports,
			Set<StudentAssessment> studentAssessments, Set<AssessmentBenchmark> assessmentBenchmarks) {
		this.assessmentType = assessmentType;
		this.createdAt = createdAt;
		this.numberOfQuestions = numberOfQuestions;
		this.assessmentdurationhours = assessmentdurationhours;
		this.assessmentdurationminutes = assessmentdurationminutes;
		this.assessmenttitle = assessmenttitle;
		this.retryAble = retryAble;
		this.category = category;
		this.assessmentQuestions = assessmentQuestions;
		this.reports = reports;
		this.studentAssessments = studentAssessments;
		this.course = course;
		this.assessmentBenchmarks = assessmentBenchmarks;
		this.description = description;
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

	@Column(name = "assessment_type")

	public String getAssessmentType() {
		return this.assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "number_of_questions")

	public Integer getNumberOfQuestions() {
		return this.numberOfQuestions;
	}

	public void setNumberOfQuestions(Integer numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	@Column(name = "assessmentdurationhours")

	public Integer getAssessmentdurationhours() {
		return this.assessmentdurationhours;
	}

	public void setAssessmentdurationhours(Integer assessmentdurationhours) {
		this.assessmentdurationhours = assessmentdurationhours;
	}

	@Column(name = "assessmentdurationminutes")

	public Integer getAssessmentdurationminutes() {
		return this.assessmentdurationminutes;
	}

	public void setAssessmentdurationminutes(Integer assessmentdurationminutes) {
		this.assessmentdurationminutes = assessmentdurationminutes;
	}

	@Column(name = "assessmenttitle")

	public String getAssessmenttitle() {
		return this.assessmenttitle;
	}

	public void setAssessmenttitle(String assessmenttitle) {
		this.assessmenttitle = assessmenttitle;
	}

	@Column(name = "retry_able")

	public Boolean getRetryAble() {
		return this.retryAble;
	}

	public void setRetryAble(Boolean retryAble) {
		this.retryAble = retryAble;
	}

	@Column(name = "category")

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name = "description")
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "course_id")

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")
	@OrderBy(clause = "orderId")
	public Set<AssessmentQuestion> getAssessmentQuestions() {
		return this.assessmentQuestions;
	}

	public void setAssessmentQuestions(Set<AssessmentQuestion> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")

	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")

	public Set<StudentAssessment> getStudentAssessments() {
		return this.studentAssessments;
	}

	public void setStudentAssessments(Set<StudentAssessment> studentAssessments) {
		this.studentAssessments = studentAssessments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")

	public Set<AssessmentBenchmark> getAssessmentBenchmarks() {
		return this.assessmentBenchmarks;
	}

	public void setAssessmentBenchmarks(Set<AssessmentBenchmark> assessmentBenchmarks) {
		this.assessmentBenchmarks = assessmentBenchmarks;
	}
}