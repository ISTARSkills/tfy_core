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
	private String name;
	private String description;
	private Boolean is_deleted;
	private Set<Slide> slides = new HashSet<Slide>(0);
	private String type;
	private String presentationXml;
	private String Image_url;

	
	
	@Column(name="type", nullable=true)
	public String getType() {
	return type;
	}

	public void setType(String type) {
	this.type = type;
	}

	@Column(name="presentation_xml", nullable=true)
	public String getPresentationXml() {
	return presentationXml;
	}

	public void setPresentationXml(String presentationXml) {
	this.presentationXml = presentationXml;
	}
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
	@Column(name = "title", unique = true, nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_deleted", unique = false, nullable = false)
	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	@Column(name = "image_url")
	public String getImage_url() {
		if(Image_url == null) {
			return "/content/assets/images/Aplied_economics.png";
		} else {
			return Image_url;
		}	}

	public void setImage_url(String image_url) {
		Image_url = image_url;
	}
}