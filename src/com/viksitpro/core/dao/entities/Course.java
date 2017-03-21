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
	private Set<Module> modules = new HashSet<Module>(0);
	private Set<SkillObjective> skillObjectives = new HashSet<SkillObjective>(0);
	private Set<Batch> batchs = new HashSet<Batch>(0);

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
			Set<StudentPlaylist> studentPlaylists, Set<Module> modules, Set<SkillObjective> skillObjectives,
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courses")

	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courses")

	public Set<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(Set<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")

	public Set<Batch> getBatchs() {
		return this.batchs;
	}

	public void setBatchs(Set<Batch> batchs) {
		this.batchs = batchs;
	}

}