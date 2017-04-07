package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * Lesson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lesson", schema = "public")

public class Lesson implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Integer duration;
	private String tags;
	private String title;
	private String subject;
	private String description;
	private Integer orderId;
	private Timestamp createdAt;
	private Boolean isDeleted;
	private Set<SkillObjective> skillObjectives = new HashSet<SkillObjective>(0);
	private Set<Cmsession> cmsessions = new HashSet<Cmsession>(0);
	private Set<StudentPlaylist> studentPlaylists = new HashSet<StudentPlaylist>(0);
	private List<Presentation> presentations;// = new HashSet<Presentation>(0);
	private String Image_url;
	// Constructors

	/** default constructor */
	public Lesson() {
	}

	/** minimal constructor */
	public Lesson(String type, String title, String subject) {
		this.type = type;
		this.title = title;
		this.subject = subject;
	}

	/** full constructor */
	public Lesson(String type, Integer duration, String tags, String title, String subject, Integer orderId,
			Timestamp createdAt, Set<SkillObjective> skillObjectives, Set<Cmsession> cmsessions,
			Set<StudentPlaylist> studentPlaylists, List<Presentation> presentations) {
		this.type = type;
		this.duration = duration;
		this.tags = tags;
		this.title = title;
		this.subject = subject;
		this.orderId = orderId;
		this.createdAt = createdAt;
		this.skillObjectives = skillObjectives;
		this.cmsessions = cmsessions;
		this.studentPlaylists = studentPlaylists;
		this.presentations = presentations;
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

	@Column(name = "type", nullable = false, length = 31)

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "duration")

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Column(name = "tags")

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "title", nullable = false)

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "subject", nullable = false)

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "order_id")

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "lesson_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "lessonid", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "learning_objectiveid", nullable = false, updatable = false) })

	public Set<SkillObjective> getSkillObjectives() {
		return this.skillObjectives;
	}

	public void setSkillObjectives(Set<SkillObjective> skillObjectives) {
		this.skillObjectives = skillObjectives;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "lesson_cmsession", schema = "public", joinColumns = {
			@JoinColumn(name = "lesson_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "cmsession_id", nullable = false, updatable = false) })

	public Set<Cmsession> getCmsessions() {
		return this.cmsessions;
	}

	public void setCmsessions(Set<Cmsession> cmsessions) {
		this.cmsessions = cmsessions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lesson")

	public Set<StudentPlaylist> getStudentPlaylists() {
		return this.studentPlaylists;
	}

	public void setStudentPlaylists(Set<StudentPlaylist> studentPlaylists) {
		this.studentPlaylists = studentPlaylists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lesson")

	public List<Presentation> getPresentations() {
		return this.presentations;
	}

	public void setPresentations(List<Presentation> presentations) {
		this.presentations = presentations;
	}

	@Column(name = "is_deleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "image_url")
	public String getImage_url() {
		return Image_url;
	}

	public void setImage_url(String image_url) {
		Image_url = image_url;
	}
	
	
	
	
	
}