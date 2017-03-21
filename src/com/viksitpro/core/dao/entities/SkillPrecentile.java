package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * SkillPrecentile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "skill_precentile", schema = "public")

public class SkillPrecentile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp timestamp;
	private Integer studentId;
	private Integer skillId;
	private Integer percentileCountry;
	private Integer percentileGlobe;
	private Integer percentileBatch;
	private Integer percentileOrganization;
	private Integer accuracy;
	private Integer industryBenchmark;
	private Integer pointsEarned;
	private Integer maxPoints;
	private Integer rankCountry;
	private Integer rankGlobe;
	private Integer rankBatch;
	private Integer rankOrganization;
	private Integer percentage;

	// Constructors

	/** default constructor */
	public SkillPrecentile() {
	}

	/** full constructor */
	public SkillPrecentile(Integer studentId, Integer skillId, Integer percentileCountry, Integer percentileGlobe,
			Integer percentileBatch, Integer percentileOrganization, Integer accuracy, Integer industryBenchmark,
			Integer pointsEarned, Integer maxPoints, Integer rankCountry, Integer rankGlobe, Integer rankBatch,
			Integer rankOrganization, Integer percentage) {
		this.studentId = studentId;
		this.skillId = skillId;
		this.percentileCountry = percentileCountry;
		this.percentileGlobe = percentileGlobe;
		this.percentileBatch = percentileBatch;
		this.percentileOrganization = percentileOrganization;
		this.accuracy = accuracy;
		this.industryBenchmark = industryBenchmark;
		this.pointsEarned = pointsEarned;
		this.maxPoints = maxPoints;
		this.rankCountry = rankCountry;
		this.rankGlobe = rankGlobe;
		this.rankBatch = rankBatch;
		this.rankOrganization = rankOrganization;
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

	@Version
	@Column(name = "timestamp", length = 29)

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "student_id")

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "skill_id")

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
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

	@Column(name = "accuracy")

	public Integer getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	@Column(name = "industry_benchmark")

	public Integer getIndustryBenchmark() {
		return this.industryBenchmark;
	}

	public void setIndustryBenchmark(Integer industryBenchmark) {
		this.industryBenchmark = industryBenchmark;
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

	@Column(name = "rank_country")

	public Integer getRankCountry() {
		return this.rankCountry;
	}

	public void setRankCountry(Integer rankCountry) {
		this.rankCountry = rankCountry;
	}

	@Column(name = "rank_globe")

	public Integer getRankGlobe() {
		return this.rankGlobe;
	}

	public void setRankGlobe(Integer rankGlobe) {
		this.rankGlobe = rankGlobe;
	}

	@Column(name = "rank_batch")

	public Integer getRankBatch() {
		return this.rankBatch;
	}

	public void setRankBatch(Integer rankBatch) {
		this.rankBatch = rankBatch;
	}

	@Column(name = "rank_organization")

	public Integer getRankOrganization() {
		return this.rankOrganization;
	}

	public void setRankOrganization(Integer rankOrganization) {
		this.rankOrganization = rankOrganization;
	}

	@Column(name = "percentage")

	public Integer getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

}