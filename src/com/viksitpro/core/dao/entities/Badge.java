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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Badge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "badge", schema = "public")

public class Badge implements java.io.Serializable {

	// Fields

	private Integer id;
	private String badgeTitle;
	private String badgeImage;
	private Timestamp createdAt;
	private Set<BadgeSkill> badgeSkills = new HashSet<BadgeSkill>(0);

	// Constructors

	/** default constructor */
	public Badge() {
	}

	/** full constructor */
	public Badge(String badgeTitle, String badgeImage, Timestamp createdAt, Set<BadgeSkill> badgeSkills) {
		this.badgeTitle = badgeTitle;
		this.badgeImage = badgeImage;
		this.createdAt = createdAt;
		this.badgeSkills = badgeSkills;
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

	@Column(name = "badge_title")

	public String getBadgeTitle() {
		return this.badgeTitle;
	}

	public void setBadgeTitle(String badgeTitle) {
		this.badgeTitle = badgeTitle;
	}

	@Column(name = "badge_image")

	public String getBadgeImage() {
		return this.badgeImage;
	}

	public void setBadgeImage(String badgeImage) {
		this.badgeImage = badgeImage;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "badge")

	public Set<BadgeSkill> getBadgeSkills() {
		return this.badgeSkills;
	}

	public void setBadgeSkills(Set<BadgeSkill> badgeSkills) {
		this.badgeSkills = badgeSkills;
	}

}