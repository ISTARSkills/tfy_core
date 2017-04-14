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
 * AssessmentBenchmark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assessment_benchmark", schema = "public")

public class AssessmentBenchmark implements java.io.Serializable {

	// Fields

	private Integer id;
	private Assessment assessment;
	private SkillObjective skillObjective;
	private Double maxPoints;

	// Constructors

	/** default constructor */
	public AssessmentBenchmark() {
	}

	/** full constructor */
	public AssessmentBenchmark(Assessment assessment, SkillObjective skillObjective, Double maxPoints) {
		this.assessment = assessment;
		this.skillObjective = skillObjective;
		this.maxPoints = maxPoints;
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
	@JoinColumn(name = "assessment_id")

	public Assessment getAssessment() {
		return this.assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_objective_id")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@Column(name = "max_points", precision = 17, scale = 17)

	public Double getMaxPoints() {
		return this.maxPoints;
	}

	public void setMaxPoints(Double maxPoints) {
		this.maxPoints = maxPoints;
	}

}