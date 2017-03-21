package com.viksitpro.core.dao.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TrainerPresentor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainer_presentor", schema = "public")

public class TrainerPresentor implements java.io.Serializable {

	// Fields

	private TrainerPresentorId id;
	private IstarUser istarUserByTrainerId;
	private IstarUser istarUserByPresentorId;

	// Constructors

	/** default constructor */
	public TrainerPresentor() {
	}

	/** minimal constructor */
	public TrainerPresentor(TrainerPresentorId id) {
		this.id = id;
	}

	/** full constructor */
	public TrainerPresentor(TrainerPresentorId id, IstarUser istarUserByTrainerId, IstarUser istarUserByPresentorId) {
		this.id = id;
		this.istarUserByTrainerId = istarUserByTrainerId;
		this.istarUserByPresentorId = istarUserByPresentorId;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "trainerId", column = @Column(name = "trainer_id")),
			@AttributeOverride(name = "presentorId", column = @Column(name = "presentor_id")) })

	public TrainerPresentorId getId() {
		return this.id;
	}

	public void setId(TrainerPresentorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainer_id", insertable = false, updatable = false)

	public IstarUser getIstarUserByTrainerId() {
		return this.istarUserByTrainerId;
	}

	public void setIstarUserByTrainerId(IstarUser istarUserByTrainerId) {
		this.istarUserByTrainerId = istarUserByTrainerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "presentor_id", insertable = false, updatable = false)

	public IstarUser getIstarUserByPresentorId() {
		return this.istarUserByPresentorId;
	}

	public void setIstarUserByPresentorId(IstarUser istarUserByPresentorId) {
		this.istarUserByPresentorId = istarUserByPresentorId;
	}

}