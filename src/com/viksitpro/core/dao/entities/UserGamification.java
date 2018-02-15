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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SkillObjective skillObjective;
	private IstarUser istarUser;
	private Double points;
	private Integer coins;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer itemId;
	private String itemType;

	// Constructors

	/** default constructor */
	public UserGamification() {
	}

	/** full constructor */
	public UserGamification(SkillObjective skillObjective, IstarUser istarUser, Double points, Integer coins,
			Timestamp createdAt, Timestamp updatedAt, Integer itemId, String itemType) {
		this.skillObjective = skillObjective;
		this.istarUser = istarUser;
		this.points = points;
		this.coins = coins;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.itemId = itemId;
		this.itemType = itemType;
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

	public Double getPoints() {
		return this.points;
	}

	public void setPoints(Double points) {
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

	@Column(name = "item_id")
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	@Column(name = "item_type")

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}