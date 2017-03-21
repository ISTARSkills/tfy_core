package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * JobRoleSkillBenchmarkId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class JobRoleSkillBenchmarkId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer vacancyId;
	private Integer skillId;
	private Float masterLevel;
	private Float wizardLevel;
	private Float apprenticeLevel;
	private Float rookieLevel;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public JobRoleSkillBenchmarkId() {
	}

	/** minimal constructor */
	public JobRoleSkillBenchmarkId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JobRoleSkillBenchmarkId(Integer id, Integer vacancyId, Integer skillId, Float masterLevel, Float wizardLevel,
			Float apprenticeLevel, Float rookieLevel, Timestamp createdAt) {
		this.id = id;
		this.vacancyId = vacancyId;
		this.skillId = skillId;
		this.masterLevel = masterLevel;
		this.wizardLevel = wizardLevel;
		this.apprenticeLevel = apprenticeLevel;
		this.rookieLevel = rookieLevel;
		this.createdAt = createdAt;
	}

	// Property accessors

	@Column(name = "id", nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "vacancy_id")

	public Integer getVacancyId() {
		return this.vacancyId;
	}

	public void setVacancyId(Integer vacancyId) {
		this.vacancyId = vacancyId;
	}

	@Column(name = "skill_id")

	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	@Column(name = "master_level", precision = 8, scale = 8)

	public Float getMasterLevel() {
		return this.masterLevel;
	}

	public void setMasterLevel(Float masterLevel) {
		this.masterLevel = masterLevel;
	}

	@Column(name = "wizard_level", precision = 8, scale = 8)

	public Float getWizardLevel() {
		return this.wizardLevel;
	}

	public void setWizardLevel(Float wizardLevel) {
		this.wizardLevel = wizardLevel;
	}

	@Column(name = "apprentice_level", precision = 8, scale = 8)

	public Float getApprenticeLevel() {
		return this.apprenticeLevel;
	}

	public void setApprenticeLevel(Float apprenticeLevel) {
		this.apprenticeLevel = apprenticeLevel;
	}

	@Column(name = "rookie_level", precision = 8, scale = 8)

	public Float getRookieLevel() {
		return this.rookieLevel;
	}

	public void setRookieLevel(Float rookieLevel) {
		this.rookieLevel = rookieLevel;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JobRoleSkillBenchmarkId))
			return false;
		JobRoleSkillBenchmarkId castOther = (JobRoleSkillBenchmarkId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getVacancyId() == castOther.getVacancyId()) || (this.getVacancyId() != null
						&& castOther.getVacancyId() != null && this.getVacancyId().equals(castOther.getVacancyId())))
				&& ((this.getSkillId() == castOther.getSkillId()) || (this.getSkillId() != null
						&& castOther.getSkillId() != null && this.getSkillId().equals(castOther.getSkillId())))
				&& ((this.getMasterLevel() == castOther.getMasterLevel())
						|| (this.getMasterLevel() != null && castOther.getMasterLevel() != null
								&& this.getMasterLevel().equals(castOther.getMasterLevel())))
				&& ((this.getWizardLevel() == castOther.getWizardLevel())
						|| (this.getWizardLevel() != null && castOther.getWizardLevel() != null
								&& this.getWizardLevel().equals(castOther.getWizardLevel())))
				&& ((this.getApprenticeLevel() == castOther.getApprenticeLevel())
						|| (this.getApprenticeLevel() != null && castOther.getApprenticeLevel() != null
								&& this.getApprenticeLevel().equals(castOther.getApprenticeLevel())))
				&& ((this.getRookieLevel() == castOther.getRookieLevel())
						|| (this.getRookieLevel() != null && castOther.getRookieLevel() != null
								&& this.getRookieLevel().equals(castOther.getRookieLevel())))
				&& ((this.getCreatedAt() == castOther.getCreatedAt()) || (this.getCreatedAt() != null
						&& castOther.getCreatedAt() != null && this.getCreatedAt().equals(castOther.getCreatedAt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getVacancyId() == null ? 0 : this.getVacancyId().hashCode());
		result = 37 * result + (getSkillId() == null ? 0 : this.getSkillId().hashCode());
		result = 37 * result + (getMasterLevel() == null ? 0 : this.getMasterLevel().hashCode());
		result = 37 * result + (getWizardLevel() == null ? 0 : this.getWizardLevel().hashCode());
		result = 37 * result + (getApprenticeLevel() == null ? 0 : this.getApprenticeLevel().hashCode());
		result = 37 * result + (getRookieLevel() == null ? 0 : this.getRookieLevel().hashCode());
		result = 37 * result + (getCreatedAt() == null ? 0 : this.getCreatedAt().hashCode());
		return result;
	}

}