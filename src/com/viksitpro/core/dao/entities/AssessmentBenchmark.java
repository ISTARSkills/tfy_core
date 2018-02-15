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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer itemId;
	private String itemType;
	private SkillObjective skillObjective;
	private String maxPoints;
	private Integer contextId;

	// Constructors

	/** default constructor */
	public AssessmentBenchmark() {
	}

	/** full constructor */
	public AssessmentBenchmark(Assessment assessment, SkillObjective skillObjective, String maxPoints) {
		
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
	@JoinColumn(name = "skill_objective_id")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@Column(name = "max_points", precision = 17, scale = 17)

	public String getMaxPoints() {
		return this.maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	@Column(name="item_id")
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name="item_type")
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	@Column(name="context_id")
	public Integer getContextId() {
		return contextId;
	}

	public void setContextId(Integer contextId) {
		this.contextId = contextId;
	}

	
	
}