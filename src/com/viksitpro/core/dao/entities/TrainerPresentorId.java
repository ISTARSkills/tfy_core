package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TrainerPresentorId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TrainerPresentorId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer trainerId;
	private Integer presentorId;

	// Constructors

	/** default constructor */
	public TrainerPresentorId() {
	}

	/** minimal constructor */
	public TrainerPresentorId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TrainerPresentorId(Integer id, Integer trainerId, Integer presentorId) {
		this.id = id;
		this.trainerId = trainerId;
		this.presentorId = presentorId;
	}

	// Property accessors

	@Column(name = "id", nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "trainer_id")

	public Integer getTrainerId() {
		return this.trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	@Column(name = "presentor_id")

	public Integer getPresentorId() {
		return this.presentorId;
	}

	public void setPresentorId(Integer presentorId) {
		this.presentorId = presentorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TrainerPresentorId))
			return false;
		TrainerPresentorId castOther = (TrainerPresentorId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getTrainerId() == castOther.getTrainerId()) || (this.getTrainerId() != null
						&& castOther.getTrainerId() != null && this.getTrainerId().equals(castOther.getTrainerId())))
				&& ((this.getPresentorId() == castOther.getPresentorId())
						|| (this.getPresentorId() != null && castOther.getPresentorId() != null
								&& this.getPresentorId().equals(castOther.getPresentorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getTrainerId() == null ? 0 : this.getTrainerId().hashCode());
		result = 37 * result + (getPresentorId() == null ? 0 : this.getPresentorId().hashCode());
		return result;
	}

}