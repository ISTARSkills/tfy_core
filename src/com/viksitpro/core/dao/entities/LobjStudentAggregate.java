package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * LobjStudentAggregate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lobj_student_aggregate", schema = "public")

public class LobjStudentAggregate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lobjId;
	private Integer studentId;
	private Integer batchRank;
	private Integer countryRank;
	private Integer globeRank;
	private Integer organizationRank;
	private Integer percentileBatch;
	private Integer percentileOrganization;
	private Integer percentileCountry;
	private Integer percentileGlobe;
	private Timestamp createdAt;
	private Integer totalPoints;
	private Integer maxPoints;
	private Integer percentage;

	// Constructors

	/** default constructor */
	public LobjStudentAggregate() {
	}

	/** full constructor */
	public LobjStudentAggregate(Integer lobjId, Integer studentId, Integer batchRank, Integer countryRank,
			Integer globeRank, Integer organizationRank, Integer percentileBatch, Integer percentileOrganization,
			Integer percentileCountry, Integer percentileGlobe, Timestamp createdAt, Integer totalPoints,
			Integer maxPoints, Integer percentage) {
		this.lobjId = lobjId;
		this.studentId = studentId;
		this.batchRank = batchRank;
		this.countryRank = countryRank;
		this.globeRank = globeRank;
		this.organizationRank = organizationRank;
		this.percentileBatch = percentileBatch;
		this.percentileOrganization = percentileOrganization;
		this.percentileCountry = percentileCountry;
		this.percentileGlobe = percentileGlobe;
		this.createdAt = createdAt;
		this.totalPoints = totalPoints;
		this.maxPoints = maxPoints;
		this.percentage = percentage;
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

	@Column(name = "batch_rank")

	public Integer getBatchRank() {
		return this.batchRank;
	}

	public void setBatchRank(Integer batchRank) {
		this.batchRank = batchRank;
	}

	@Column(name = "country_rank")

	public Integer getCountryRank() {
		return this.countryRank;
	}

	public void setCountryRank(Integer countryRank) {
		this.countryRank = countryRank;
	}

	@Column(name = "globe_rank")

	public Integer getGlobeRank() {
		return this.globeRank;
	}

	public void setGlobeRank(Integer globeRank) {
		this.globeRank = globeRank;
	}

	@Column(name = "organization_rank")

	public Integer getOrganizationRank() {
		return this.organizationRank;
	}

	public void setOrganizationRank(Integer organizationRank) {
		this.organizationRank = organizationRank;
	}

	@Column(name = "percentile_batch")

	public Integer getPercentileBatch() {
		return this.percentileBatch;
	}

	public void setPercentileBatch(Integer percentileBatch) {
		this.percentileBatch = percentileBatch;
	}

	@Column(name = "percentile_organization")

	public Integer getPercentileOrganization() {
		return this.percentileOrganization;
	}

	public void setPercentileOrganization(Integer percentileOrganization) {
		this.percentileOrganization = percentileOrganization;
	}

	@Column(name = "percentile_country")

	public Integer getPercentileCountry() {
		return this.percentileCountry;
	}

	public void setPercentileCountry(Integer percentileCountry) {
		this.percentileCountry = percentileCountry;
	}

	@Column(name = "percentile_globe")

	public Integer getPercentileGlobe() {
		return this.percentileGlobe;
	}

	public void setPercentileGlobe(Integer percentileGlobe) {
		this.percentileGlobe = percentileGlobe;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "total_points")

	public Integer getTotalPoints() {
		return this.totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	@Column(name = "max_points")

	public Integer getMaxPoints() {
		return this.maxPoints;
	}

	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}

	@Column(name = "percentage")

	public Integer getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

}