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
 * StudentAssessment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_assessment", schema = "public")

public class StudentAssessment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Assessment assessment;
	private Question question;
	private IstarUser istarUser;
	private Boolean correct;
	private Boolean option1;
	private Boolean option2;
	private Boolean option3;
	private Boolean option4;
	private Boolean option5;
	private Integer countryId;
	private Integer organizationId;
	private Integer batchGroupId;
	private Integer timeTaken;

	// Constructors

	/** default constructor */
	public StudentAssessment() {
	}

	/** minimal constructor */
	public StudentAssessment(Assessment assessment, Question question, IstarUser istarUser) {
		this.assessment = assessment;
		this.question = question;
		this.istarUser = istarUser;
	}

	/** full constructor */
	public StudentAssessment(Assessment assessment, Question question, IstarUser istarUser, Boolean correct,
			Boolean option1, Boolean option2, Boolean option3, Boolean option4, Boolean option5, Integer countryId,
			Integer organizationId, Integer batchGroupId, Integer timeTaken) {
		this.assessment = assessment;
		this.question = question;
		this.istarUser = istarUser;
		this.correct = correct;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.option5 = option5;
		this.countryId = countryId;
		this.organizationId = organizationId;
		this.batchGroupId = batchGroupId;
		this.timeTaken = timeTaken;
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
	@JoinColumn(name = "assessment_id", nullable = false)

	public Assessment getAssessment() {
		return this.assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "correct")

	public Boolean getCorrect() {
		return this.correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	@Column(name = "option1")

	public Boolean getOption1() {
		return this.option1;
	}

	public void setOption1(Boolean option1) {
		this.option1 = option1;
	}

	@Column(name = "option2")

	public Boolean getOption2() {
		return this.option2;
	}

	public void setOption2(Boolean option2) {
		this.option2 = option2;
	}

	@Column(name = "option3")

	public Boolean getOption3() {
		return this.option3;
	}

	public void setOption3(Boolean option3) {
		this.option3 = option3;
	}

	@Column(name = "option4")

	public Boolean getOption4() {
		return this.option4;
	}

	public void setOption4(Boolean option4) {
		this.option4 = option4;
	}

	@Column(name = "option5")

	public Boolean getOption5() {
		return this.option5;
	}

	public void setOption5(Boolean option5) {
		this.option5 = option5;
	}

	@Column(name = "country_id")

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "organization_id")

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "batch_group_id")

	public Integer getBatchGroupId() {
		return this.batchGroupId;
	}

	public void setBatchGroupId(Integer batchGroupId) {
		this.batchGroupId = batchGroupId;
	}

	@Column(name = "time_taken")

	public Integer getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

}