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
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task", schema = "public")

public class Task implements java.io.Serializable {

	// Fields

	private Integer id;
	private Task task;
	private TaskType taskType;
	private IstarUser istarUserByOwner;
	private IstarUser istarUserByAssigneeMember;
	private IstarUser istarUserByActor;
	private Project project;
	private Team team;
	private String name;
	private String description;
	private Integer priority;
	private String state;
	private Timestamp startDate;
	private Timestamp endDate;
	private Float durationInHours;
	private Boolean isRepeatative;
	private Timestamp followupDate;
	private Boolean isActive;
	private String tags;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer itemId;
	private String itemType;
	private Boolean isTimedTask;
	private Integer followUpDurationInDays;
	private Set<StageLog> stageLogs = new HashSet<StageLog>(0);
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<TaskFeedback> taskFeedbacks = new HashSet<TaskFeedback>(0);
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);
	private Set<Campaign> campaigns = new HashSet<Campaign>(0);
	private Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings = new HashSet<RecruiterJobTaskCollegeMapping>(
			0);

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** full constructor */
	public Task(Task task, TaskType taskType, IstarUser istarUserByOwner, IstarUser istarUserByAssigneeMember,
			IstarUser istarUserByActor, Project project, Team team, String name, String description, Integer priority,
			String state, Timestamp startDate, Timestamp endDate, Float durationInHours, Boolean isRepeatative,
			Timestamp followupDate, Boolean isActive, String tags, Timestamp createdAt, Timestamp updatedAt,
			Integer itemId, String itemType, Boolean isTimedTask, Integer followUpDurationInDays,
			Set<StageLog> stageLogs, Set<Task> tasks, Set<TaskFeedback> taskFeedbacks, Set<TaskLog> taskLogs,
			Set<Campaign> campaigns, Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings) {
		this.task = task;
		this.taskType = taskType;
		this.istarUserByOwner = istarUserByOwner;
		this.istarUserByAssigneeMember = istarUserByAssigneeMember;
		this.istarUserByActor = istarUserByActor;
		this.project = project;
		this.team = team;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationInHours = durationInHours;
		this.isRepeatative = isRepeatative;
		this.followupDate = followupDate;
		this.isActive = isActive;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.itemId = itemId;
		this.itemType = itemType;
		this.isTimedTask = isTimedTask;
		this.followUpDurationInDays = followUpDurationInDays;
		this.stageLogs = stageLogs;
		this.tasks = tasks;
		this.taskFeedbacks = taskFeedbacks;
		this.taskLogs = taskLogs;
		this.campaigns = campaigns;
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
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
	@JoinColumn(name = "parent_task")

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_type")

	public TaskType getTaskType() {
		return this.taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")

	public IstarUser getIstarUserByOwner() {
		return this.istarUserByOwner;
	}

	public void setIstarUserByOwner(IstarUser istarUserByOwner) {
		this.istarUserByOwner = istarUserByOwner;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_member")

	public IstarUser getIstarUserByAssigneeMember() {
		return this.istarUserByAssigneeMember;
	}

	public void setIstarUserByAssigneeMember(IstarUser istarUserByAssigneeMember) {
		this.istarUserByAssigneeMember = istarUserByAssigneeMember;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor")

	public IstarUser getIstarUserByActor() {
		return this.istarUserByActor;
	}

	public void setIstarUserByActor(IstarUser istarUserByActor) {
		this.istarUserByActor = istarUserByActor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_team")

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "priority")

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "state")

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "start_date", length = 29)

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", length = 29)

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@Column(name = "duration_in_hours", precision = 8, scale = 8)

	public Float getDurationInHours() {
		return this.durationInHours;
	}

	public void setDurationInHours(Float durationInHours) {
		this.durationInHours = durationInHours;
	}

	@Column(name = "is_repeatative")

	public Boolean getIsRepeatative() {
		return this.isRepeatative;
	}

	public void setIsRepeatative(Boolean isRepeatative) {
		this.isRepeatative = isRepeatative;
	}

	@Column(name = "followup_date", length = 29)

	public Timestamp getFollowupDate() {
		return this.followupDate;
	}

	public void setFollowupDate(Timestamp followupDate) {
		this.followupDate = followupDate;
	}

	@Column(name = "is_active")

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "tags")

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	@Column(name = "item_id")

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_type")

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "is_timed_task")

	public Boolean getIsTimedTask() {
		return this.isTimedTask;
	}

	public void setIsTimedTask(Boolean isTimedTask) {
		this.isTimedTask = isTimedTask;
	}

	@Column(name = "follow_up_duration_in_days")

	public Integer getFollowUpDurationInDays() {
		return this.followUpDurationInDays;
	}

	public void setFollowUpDurationInDays(Integer followUpDurationInDays) {
		this.followUpDurationInDays = followUpDurationInDays;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<StageLog> getStageLogs() {
		return this.stageLogs;
	}

	public void setStageLogs(Set<StageLog> stageLogs) {
		this.stageLogs = stageLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<TaskFeedback> getTaskFeedbacks() {
		return this.taskFeedbacks;
	}

	public void setTaskFeedbacks(Set<TaskFeedback> taskFeedbacks) {
		this.taskFeedbacks = taskFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(Set<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "task")

	public Set<RecruiterJobTaskCollegeMapping> getRecruiterJobTaskCollegeMappings() {
		return this.recruiterJobTaskCollegeMappings;
	}

	public void setRecruiterJobTaskCollegeMappings(
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings) {
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
	}

}