package com.viksitpro.core.dao.entities;

import java.util.HashSet;
import java.util.List;
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
import org.hibernate.annotations.OrderBy;

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
	private List<Cmsession> cmsessions;
	private Set<Course> courses = new HashSet<Course>(0);
	private List<SkillObjective> skillObjectives;// = new HashSet<SkillObjective>(0);
	private String module_description;
	private String Image_url;
	
	
	// Constructors
	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(String moduleName) {
		this.moduleName = moduleName;
	}

	/** full constructor */
	public Module(String moduleName, Integer orderId, Boolean isDeleted, List<Cmsession> cmsessions, Set<Course> courses,
			List<SkillObjective> skillObjectives) {
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
	@OrderBy(clause = "oid asc")
	public List<Cmsession> getCmsessions() {
		return this.cmsessions;
	}

	public void setCmsessions(List<Cmsession> cmsessions) {
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

	public List<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(List<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@Column(name = "module_description", nullable = true)

	public String getModule_description() {
		return module_description;
	}

	public void setModule_description(String module_description) {
		this.module_description = module_description;
	}

	@Column(name = "image_url", nullable = true)
	
	public String getImage_url() {
		if(Image_url == null) {
			return "/content/assets/images/Aplied_economics.png";
		} else {
			return Image_url;
		}
	}

	public void setImage_url(String image_url) {
		Image_url = image_url;
	}
	
	
}