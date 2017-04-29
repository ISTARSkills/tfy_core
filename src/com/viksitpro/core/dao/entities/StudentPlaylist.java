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
 * StudentPlaylist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_playlist", schema = "public")

public class StudentPlaylist implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lesson lesson;
	private Course course;
	private IstarUser istarUser;
	private String status;
	private Module module;
	private Cmsession cmsession;

	// Constructors

	/** default constructor */
	public StudentPlaylist() {
	}

	/** full constructor */
	public StudentPlaylist(Lesson lesson, Cmsession cmsession, Module module, Course course, IstarUser istarUser, String status) {
		this.lesson = lesson;
		this.course = course;
		this.istarUser = istarUser;
		this.status = status;
		this.module = module;
		this.cmsession = cmsession;
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
	@JoinColumn(name = "lesson_id")

	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cmsession_id")

	public Cmsession getCmsession() {
		return this.cmsession;
	}

	public void setCmsession(Cmsession cmsession) {
		this.cmsession = cmsession;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "status")

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}