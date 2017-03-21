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
 * BadgeSkill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "badge_skill", schema = "public")

public class BadgeSkill implements java.io.Serializable {

	// Fields

	private Integer id;
	private SkillObjective skillObjective;
	private Badge badge;
	private Integer points;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public BadgeSkill() {
	}

	/** full constructor */
	public BadgeSkill(SkillObjective skillObjective, Badge badge, Integer points, Timestamp createdAt) {
		this.skillObjective = skillObjective;
		this.badge = badge;
		this.points = points;
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
	@JoinColumn(name = "skill_id")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "badge_id")

	public Badge getBadge() {
		return this.badge;
	}

	public void setBadge(Badge badge) {
		this.badge = badge;
	}

	@Column(name = "points")

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}