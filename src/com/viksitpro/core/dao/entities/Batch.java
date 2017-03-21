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
 * Batch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch", schema = "public")

public class Batch implements java.io.Serializable {

	// Fields

	private Integer id;
	private Course course;
	private BatchGroup batchGroup;
	private Timestamp createdat;
	private String name;
	private Timestamp updatedat;
	private Integer orderId;
	private Set<TrainerBatch> trainerBatchs = new HashSet<TrainerBatch>(0);
	private Set<TrainerFeedback> trainerFeedbacks = new HashSet<TrainerFeedback>(0);

	// Constructors

	/** default constructor */
	public Batch() {
	}

	/** minimal constructor */
	public Batch(BatchGroup batchGroup, Timestamp createdat, String name, Timestamp updatedat) {
		this.batchGroup = batchGroup;
		this.createdat = createdat;
		this.name = name;
		this.updatedat = updatedat;
	}

	/** full constructor */
	public Batch(Course course, BatchGroup batchGroup, Timestamp createdat, String name, Timestamp updatedat,
			Integer orderId, Set<TrainerBatch> trainerBatchs, Set<TrainerFeedback> trainerFeedbacks) {
		this.course = course;
		this.batchGroup = batchGroup;
		this.createdat = createdat;
		this.name = name;
		this.updatedat = updatedat;
		this.orderId = orderId;
		this.trainerBatchs = trainerBatchs;
		this.trainerFeedbacks = trainerFeedbacks;
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
	@JoinColumn(name = "course_id")

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_group_id", nullable = false)

	public BatchGroup getBatchGroup() {
		return this.batchGroup;
	}

	public void setBatchGroup(BatchGroup batchGroup) {
		this.batchGroup = batchGroup;
	}

	@Column(name = "createdat", nullable = false, length = 29)

	public Timestamp getCreatedat() {
		return this.createdat;
	}

	public void setCreatedat(Timestamp createdat) {
		this.createdat = createdat;
	}

	@Column(name = "name", nullable = false)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "updatedat", nullable = false, length = 29)

	public Timestamp getUpdatedat() {
		return this.updatedat;
	}

	public void setUpdatedat(Timestamp updatedat) {
		this.updatedat = updatedat;
	}

	@Column(name = "order_id")

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batch")

	public Set<TrainerBatch> getTrainerBatchs() {
		return this.trainerBatchs;
	}

	public void setTrainerBatchs(Set<TrainerBatch> trainerBatchs) {
		this.trainerBatchs = trainerBatchs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "batch")

	public Set<TrainerFeedback> getTrainerFeedbacks() {
		return this.trainerFeedbacks;
	}

	public void setTrainerFeedbacks(Set<TrainerFeedback> trainerFeedbacks) {
		this.trainerFeedbacks = trainerFeedbacks;
	}

}