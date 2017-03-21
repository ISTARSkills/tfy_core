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
 * TrainerSkillDistrubutionStats entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainer_skill_distrubution_stats", schema = "public")

public class TrainerSkillDistrubutionStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private SkillObjective skillObjective;
	private IstarUser istarUser;

	// Constructors

	/** default constructor */
	public TrainerSkillDistrubutionStats() {
	}

	/** full constructor */
	public TrainerSkillDistrubutionStats(SkillObjective skillObjective, IstarUser istarUser) {
		this.skillObjective = skillObjective;
		this.istarUser = istarUser;
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
	@JoinColumn(name = "skill_objective_id")

	public SkillObjective getSkillObjective() {
		return this.skillObjective;
	}

	public void setSkillObjective(SkillObjective skillObjective) {
		this.skillObjective = skillObjective;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainer_id")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

}