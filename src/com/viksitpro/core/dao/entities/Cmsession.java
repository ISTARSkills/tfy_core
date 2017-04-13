package com.viksitpro.core.dao.entities;

import java.util.ArrayList;
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

/**
 * Cmsession entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "cmsession", schema = "public")

public class Cmsession implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String description;
	private Integer orderId;
	private Boolean isDeleted;
	private List<Lesson> lessons = new ArrayList<Lesson>();
	private List<SkillObjective> skillObjectives= new ArrayList<SkillObjective>();
	private Set<Module> modules = new HashSet<Module>(0);
	private String Image_url;

	// Constructors

	/** default constructor */
	public Cmsession() {
	}

	/** minimal constructor */
	public Cmsession(String title) {
		this.title = title;
	}

	/** full constructor */
	public Cmsession(String title, String description, Integer orderId, Boolean isDeleted, List<Lesson> lessons,
			List<SkillObjective> skillObjectives, Set<Module> modules) {
		this.title = title;
		this.description = description;
		this.orderId = orderId;
		this.isDeleted = isDeleted;
		this.lessons = lessons;
		this.skillObjectives = skillObjectives;
		this.modules = modules;
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

	@Column(name = "title", nullable = false)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cmsessions")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "lesson_cmsession", schema = "public", joinColumns = {
			@JoinColumn(name = "cmsession_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "lesson_id", nullable = false, updatable = false) })
	public List<Lesson> getLessons() {
		return this.lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cmsessions")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "cmsession_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "cmsession_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "skill_objective_id", nullable = false, updatable = false) })
	public List<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(List<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cmsessions")

	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	@Column(name = "image_url")
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