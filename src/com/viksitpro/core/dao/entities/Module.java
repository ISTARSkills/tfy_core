package com.viksitpro.core.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "module", schema = "public")

public class Module implements java.io.Serializable {

	// Fields

	private Integer id;
	private String moduleName;
	private Integer orderId;
	private Boolean isDeleted;
	private Set<Cmsession> cmsessions = new HashSet<Cmsession>(0);
	private Set<Course> courses = new HashSet<Course>(0);
	private Set<SkillObjective> skillObjectives = new HashSet<SkillObjective>(0);

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(String moduleName) {
		this.moduleName = moduleName;
	}

	/** full constructor */
	public Module(String moduleName, Integer orderId, Boolean isDeleted, Set<Cmsession> cmsessions, Set<Course> courses,
			Set<SkillObjective> skillObjectives) {
		this.moduleName = moduleName;
		this.orderId = orderId;
		this.isDeleted = isDeleted;
		this.cmsessions = cmsessions;
		this.courses = courses;
		this.skillObjectives = skillObjectives;
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

	@Column(name = "module_name", nullable = false)

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "order_id")

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "is_deleted")

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "cmsession_module", schema = "public", joinColumns = {
			@JoinColumn(name = "module_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "cmsession_id", nullable = false, updatable = false) })

	public Set<Cmsession> getCmsessions() {
		return this.cmsessions;
	}

	public void setCmsessions(Set<Cmsession> cmsessions) {
		this.cmsessions = cmsessions;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "module_course", schema = "public", joinColumns = {
			@JoinColumn(name = "module_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", nullable = false, updatable = false) })

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modules")

	public Set<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(Set<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

}