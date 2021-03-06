package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course", schema = "public")

public class Course implements java.io.Serializable {

	// Fields

	private Integer id;
	private String courseName;
	private String courseDescription;
	private String tags;
	private Timestamp createdAt;
	private Set<StudentPlaylist> studentPlaylists = new HashSet<StudentPlaylist>(0);
	private List<Module> modules = new ArrayList<Module>();
	private List<SkillObjective> skillObjectives = new ArrayList<SkillObjective>();
	private Set<Batch> batchs = new HashSet<Batch>(0);
	private String image_url;
	private List<Context> contexts  = new ArrayList<>();
	
	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(String courseName) {
		this.courseName = courseName;
	}

	/** full constructor */
	public Course(String courseName, String courseDescription, String tags, Timestamp createdAt,
			Set<StudentPlaylist> studentPlaylists, List<Module> modules, List<SkillObjective> skillObjectives,
			Set<Batch> batchs) {
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.tags = tags;
		this.createdAt = createdAt;
		this.studentPlaylists = studentPlaylists;
		this.modules = modules;
		this.skillObjectives = skillObjectives;
		this.batchs = batchs;
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

	@Column(name = "course_name", nullable = false)

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "course_description")

	public String getCourseDescription() {
		return this.courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	@Column(name = "tags")

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")

	public Set<StudentPlaylist> getStudentPlaylists() {
		return this.studentPlaylists;
	}

	public void setStudentPlaylists(Set<StudentPlaylist> studentPlaylists) {
		this.studentPlaylists = studentPlaylists;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "module_course", schema = "public", joinColumns = {
			@JoinColumn(name = "course_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "module_id", nullable = false, updatable = false) })
	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courses")

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "course_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "course_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "skill_objective_id", nullable = false, updatable = false) })
	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courses")

	public List<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(List<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")

	public Set<Batch> getBatchs() {
		return this.batchs;
	}

	public void setBatchs(Set<Batch> batchs) {
		this.batchs = batchs;
	}

	
	@Column(name = "image_url")
	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	@Transient 
	public String getCourselevelSkills() {
		String out = new String();
		for (SkillObjective skill : skillObjectives) {
			out = out+ skill.getName().replaceAll(" ", "").replaceAll("-", "_").toLowerCase() +" ,";
		}
		
		for(Module module : getModules()) {
			for (SkillObjective skill : module.getSkillObjectives()) {
				out = out+ skill.getName().toLowerCase().replaceAll(" ", "") +" ,";
				for(Cmsession cmsession : module.getCmsessions()) {
					for (SkillObjective skill1 : cmsession.getSkillObjectives()) {
						out = out+ skill1.getName().replaceAll(" ", "").replaceAll("-", "_").toLowerCase() +" ,";
					}
				}
			}
		}
		
		
		return out;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "course_context_mapping", schema = "public", joinColumns = {
			@JoinColumn(name = "course_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "context_id", nullable = false, updatable = false) })
	public List<Context> getContexts() {
		return contexts;
	}

	public void setContexts(List<Context> contexts) {
		this.contexts = contexts;
	}

}