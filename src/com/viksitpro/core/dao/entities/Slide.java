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
 * Slide entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "slide", schema = "public")

public class Slide implements java.io.Serializable {

	// Fields

	private Integer id;
	private Presentation presentation;
	private String slideText;
	private String studentNotes;
	private String teacherNotes;
	private String title;
	private String template;
	private Timestamp createdAt;
	private Integer orderId;
	private String itemType;
	private Integer itemId;
	private String bgImage;
	private Set<SlideVersion> slideVersions = new HashSet<SlideVersion>(0);

	// Constructors

	/** default constructor */
	public Slide() {
	}

	/** minimal constructor */
	public Slide(Presentation presentation) {
		this.presentation = presentation;
	}

	/** full constructor */
	public Slide(Presentation presentation, String slideText, String studentNotes, String teacherNotes, String title,
			String template, Timestamp createdAt, Integer orderId, String itemType, Integer itemId, String bgImage,
			Set<SlideVersion> slideVersions) {
		this.presentation = presentation;
		this.slideText = slideText;
		this.studentNotes = studentNotes;
		this.teacherNotes = teacherNotes;
		this.title = title;
		this.template = template;
		this.createdAt = createdAt;
		this.orderId = orderId;
		this.itemType = itemType;
		this.itemId = itemId;
		this.bgImage = bgImage;
		this.slideVersions = slideVersions;
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
	@JoinColumn(name = "presentation_id", nullable = false)

	public Presentation getPresentation() {
		return this.presentation;
	}

	public void setPresentation(Presentation presentation) {
		this.presentation = presentation;
	}

	@Column(name = "slide_text")

	public String getSlideText() {
		return this.slideText;
	}

	public void setSlideText(String slideText) {
		this.slideText = slideText;
	}

	@Column(name = "student_notes")

	public String getStudentNotes() {
		return this.studentNotes;
	}

	public void setStudentNotes(String studentNotes) {
		this.studentNotes = studentNotes;
	}

	@Column(name = "teacher_notes")

	public String getTeacherNotes() {
		return this.teacherNotes;
	}

	public void setTeacherNotes(String teacherNotes) {
		this.teacherNotes = teacherNotes;
	}

	@Column(name = "title")

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "template")

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "order_id")

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "item_type")

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "item_id")

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "bg_image")

	public String getBgImage() {
		return this.bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "slide")

	public Set<SlideVersion> getSlideVersions() {
		return this.slideVersions;
	}

	public void setSlideVersions(Set<SlideVersion> slideVersions) {
		this.slideVersions = slideVersions;
	}

}