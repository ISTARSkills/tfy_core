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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Presentation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "presentation", schema = "public")

public class Presentation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Lesson lesson;
	private Timestamp createdAt;
	private Set<Slide> slides = new HashSet<Slide>(0);

	// Constructors

	/** default constructor */
	public Presentation() {
	}

	/** minimal constructor */
	public Presentation(Lesson lesson) {
		this.lesson = lesson;
	}

	/** full constructor */
	public Presentation(Lesson lesson, Timestamp createdAt, Set<Slide> slides) {
		this.lesson = lesson;
		this.createdAt = createdAt;
		this.slides = slides;
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
	@JoinColumn(name = "lesson_id", nullable = false)

	public Lesson getLesson() {
		return this.lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "presentation")

	public Set<Slide> getSlides() {
		return this.slides;
	}

	public void setSlides(Set<Slide> slides) {
		this.slides = slides;
	}

}