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
 * TrainerBatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trainer_batch", schema = "public")

public class TrainerBatch implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Batch batch;

	// Constructors

	/** default constructor */
	public TrainerBatch() {
	}

	/** full constructor */
	public TrainerBatch(IstarUser istarUser, Batch batch) {
		this.istarUser = istarUser;
		this.batch = batch;
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
	@JoinColumn(name = "trainer_id")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_id")

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}