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
 * StageLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stage_log", schema = "public")

public class StageLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Task task;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String stageName;
	private String stageType;
	private String status;
	private Integer actionId;
	private String actionPassword;
	private String result;
	private String url0;
	private String url1;
	private String action;
	private String urlCode;
	private Set<SkillRating> skillRatings = new HashSet<SkillRating>(0);

	// Constructors

	/** default constructor */
	public StageLog() {
	}

	/** full constructor */
	public StageLog(IstarUser istarUser, Task task, Timestamp createdAt, Timestamp updatedAt, String stageName,
			String stageType, String status, Integer actionId, String actionPassword, String result, String url0,
			String url1, String action, String urlCode, Set<SkillRating> skillRatings) {
		this.istarUser = istarUser;
		this.task = task;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.stageName = stageName;
		this.stageType = stageType;
		this.status = status;
		this.actionId = actionId;
		this.actionPassword = actionPassword;
		this.result = result;
		this.url0 = url0;
		this.url1 = url1;
		this.action = action;
		this.urlCode = urlCode;
		this.skillRatings = skillRatings;
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
	@JoinColumn(name = "istar_user")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task")

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 29)

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "stage_name")

	public String getStageName() {
		return this.stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	@Column(name = "stage_type")

	public String getStageType() {
		return this.stageType;
	}

	public void setStageType(String stageType) {
		this.stageType = stageType;
	}

	@Column(name = "status")

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "action_id")

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	@Column(name = "action_password")

	public String getActionPassword() {
		return this.actionPassword;
	}

	public void setActionPassword(String actionPassword) {
		this.actionPassword = actionPassword;
	}

	@Column(name = "result")

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "url_0")

	public String getUrl0() {
		return this.url0;
	}

	public void setUrl0(String url0) {
		this.url0 = url0;
	}

	@Column(name = "url_1")

	public String getUrl1() {
		return this.url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	@Column(name = "action")

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "url_code")

	public String getUrlCode() {
		return this.urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stageLog")

	public Set<SkillRating> getSkillRatings() {
		return this.skillRatings;
	}

	public void setSkillRatings(Set<SkillRating> skillRatings) {
		this.skillRatings = skillRatings;
	}

}