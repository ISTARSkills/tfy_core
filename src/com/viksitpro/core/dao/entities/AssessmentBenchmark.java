package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Integer skillId;
	private String maxPoints;
	private Integer courseId;

	// Constructors

	/** default constructor */
	public AssessmentBenchmark() {
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

	

	
	@Column(name = "skill_id")
	public Integer getSkillId() {
		return skillId;
	}



	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
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
	@Column(name="course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	
	
}