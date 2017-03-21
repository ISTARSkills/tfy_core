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
 * Report entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "report", schema = "public")

public class Report implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Assessment assessment;
	private Integer progress;
	private Integer score;
	private Timestamp createdAt;
	private Integer timeTaken;
	private Integer pointsEarned;
	private Integer totalPoints;
	private Integer rank;
	private Integer percentage;
	private Integer rankCountry;
	private Integer rankGlobe;
	private Integer rankBatch;
	private Integer rankOrganization;
	private Integer percentileBatch;
	private Integer percentileOrganization;
	private Integer percentileGlobe;
	private Integer percentileCountry;

	// Constructors

	/** default constructor */
	public Report() {
	}

	/** minimal constructor */
	public Report(IstarUser istarUser, Assessment assessment, Integer progress, Integer score) {
		this.istarUser = istarUser;
		this.assessment = assessment;
		this.progress = progress;
		this.score = score;
	}

	/** full constructor */
	public Report(IstarUser istarUser, Assessment assessment, Integer progress, Integer score, Timestamp createdAt,
			Integer timeTaken, Integer pointsEarned, Integer totalPoints, Integer rank, Integer percentage,
			Integer rankCountry, Integer rankGlobe, Integer rankBatch, Integer rankOrganization,
			Integer percentileBatch, Integer percentileOrganization, Integer percentileGlobe,
			Integer percentileCountry) {
		this.istarUser = istarUser;
		this.assessment = assessment;
		this.progress = progress;
		this.score = score;
		this.createdAt = createdAt;
		this.timeTaken = timeTaken;
		this.pointsEarned = pointsEarned;
		this.totalPoints = totalPoints;
		this.rank = rank;
		this.percentage = percentage;
		this.rankCountry = rankCountry;
		this.rankGlobe = rankGlobe;
		this.rankBatch = rankBatch;
		this.rankOrganization = rankOrganization;
		this.percentileBatch = percentileBatch;
		this.percentileOrganization = percentileOrganization;
		this.percentileGlobe = percentileGlobe;
		this.percentileCountry = percentileCountry;
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
	@JoinColumn(name = "user_id", nullable = false)

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assessment_id", nullable = false)

	public Assessment getAssessment() {
		return this.assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	@Column(name = "progress", nullable = false)

	public Integer getProgress() {
		return this.progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	@Column(name = "score", nullable = false)

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "time_taken")

	public Integer getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(Integer timeTaken) {
		this.timeTaken = timeTaken;
	}

	@Column(name = "points_earned")

	public Integer getPointsEarned() {
		return this.pointsEarned;
	}

	public void setPointsEarned(Integer pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	@Column(name = "total_points")

	public Integer getTotalPoints() {
		return this.totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	@Column(name = "rank")

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "percentage")

	public Integer getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
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

	@Column(name = "percentile_globe")

	public Integer getPercentileGlobe() {
		return this.percentileGlobe;
	}

	public void setPercentileGlobe(Integer percentileGlobe) {
		this.percentileGlobe = percentileGlobe;
	}

	@Column(name = "percentile_country")

	public Integer getPercentileCountry() {
		return this.percentileCountry;
	}

	public void setPercentileCountry(Integer percentileCountry) {
		this.percentileCountry = percentileCountry;
	}

}