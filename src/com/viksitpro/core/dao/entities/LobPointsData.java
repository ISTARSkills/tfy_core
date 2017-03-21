package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * LobPointsData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lob_points_data", schema = "public")

public class LobPointsData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lobjId;
	private Integer studentId;
	private Integer pointsEarned;
	private Integer maxPoints;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public LobPointsData() {
	}

	/** full constructor */
	public LobPointsData(Integer lobjId, Integer studentId, Integer pointsEarned, Integer maxPoints,
			Timestamp createdAt) {
		this.lobjId = lobjId;
		this.studentId = studentId;
		this.pointsEarned = pointsEarned;
		this.maxPoints = maxPoints;
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

	@Column(name = "lobj_id")

	public Integer getLobjId() {
		return this.lobjId;
	}

	public void setLobjId(Integer lobjId) {
		this.lobjId = lobjId;
	}

	@Column(name = "student_id")

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "points_earned")

	public Integer getPointsEarned() {
		return this.pointsEarned;
	}

	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	@Column(name = "max_points")

	public Integer getMaxPoints() {
		return this.maxPoints;
	}

	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}