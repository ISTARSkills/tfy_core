package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

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
 * SlideVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "slide_version", schema = "public")

public class SlideVersion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Slide slide;
	private String slideText;
	private String template;
	private String teacherNotes;
	private String studentNotes;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public SlideVersion() {
	}

	/** full constructor */
	public SlideVersion(Slide slide, String slideText, String template, String teacherNotes, String studentNotes,
			Timestamp createdAt) {
		this.slide = slide;
		this.slideText = slideText;
		this.template = template;
		this.teacherNotes = teacherNotes;
		this.studentNotes = studentNotes;
		this.createdAt = createdAt;
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
	@JoinColumn(name = "slide_id")

	public Slide getSlide() {
		return this.slide;
	}

	public void setSlide(Slide slide) {
		this.slide = slide;
	}

	@Column(name = "slide_text")

	public String getSlideText() {
		return this.slideText;
	}

	public void setSlideText(String slideText) {
		this.slideText = slideText;
	}

	@Column(name = "template")

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Column(name = "teacher_notes")

	public String getTeacherNotes() {
		return this.teacherNotes;
	}

	public void setTeacherNotes(String teacherNotes) {
		this.teacherNotes = teacherNotes;
	}

	@Column(name = "student_notes")

	public String getStudentNotes() {
		return this.studentNotes;
	}

	public void setStudentNotes(String studentNotes) {
		this.studentNotes = studentNotes;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}