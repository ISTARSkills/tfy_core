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
 * UserGamification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_gamification", schema = "public")

public class UserGamification implements java.io.Serializable {

	// Fields

	private Integer id;
	private SkillObjective skillObjective;
	private IstarUser istarUser;
	private Integer points;
	private Integer coins;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public UserGamification() {
	}

	/** full constructor */
	public UserGamification(SkillObjective skillObjective, IstarUser istarUser, Integer points, Integer coins,
			Timestamp createdAt, Timestamp updatedAt) {
		this.skillObjective = skillObjective;
		this.istarUser = istarUser;
		this.points = points;
		this.coins = coins;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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
	@JoinColumn(name = "skill_objective")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "istar_user")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "points")

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Column(name = "coins")

	public Integer getCoins() {
		return this.coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 29)

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}