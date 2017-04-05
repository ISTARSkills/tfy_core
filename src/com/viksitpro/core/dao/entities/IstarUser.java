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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * IstarUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "istar_user", schema = "public")

public class IstarUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String password;
	private Timestamp createdAt;
	private Long mobile;
	private String authToken;
	private String loginType;
	private Set<Task> tasksForAssigneeMember = new HashSet<Task>(0);
	private Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings = new HashSet<RecruiterJobTaskCollegeMapping>(
			0);
	private Set<UserOrgMapping> userOrgMappings = new HashSet<UserOrgMapping>(0);
	private UserProfile userProfile;
	private Set<TrainerFeedback> trainerFeedbacks = new HashSet<TrainerFeedback>(0);
	private Set<Job> jobs = new HashSet<Job>(0);
	private Set<Team> teams = new HashSet<Team>(0);
	private Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses = new HashSet<TrainerSkillDistrubutionStats>(
			0);
	private Set<StageLog> stageLogs = new HashSet<StageLog>(0);
	private Set<Campaign> campaignsForRecruiter = new HashSet<Campaign>(0);
	private Set<Attendance> attendancesForUserId = new HashSet<Attendance>(0);
	private Set<SubUser> subUsersForIstarUser = new HashSet<SubUser>(0);
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);
	private Set<Campaign> campaignsForOwner = new HashSet<Campaign>(0);
	private Set<Notification> notificationsForSenderId = new HashSet<Notification>(0);
	private Set<StudentAssessment> studentAssessments = new HashSet<StudentAssessment>(0);
	private Set<TrainerBatch> trainerBatchs = new HashSet<TrainerBatch>(0);
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<TaskFeedback> taskFeedbacks = new HashSet<TaskFeedback>(0);
	private Set<CollegeRecruiterMapping> collegeRecruiterMappings = new HashSet<CollegeRecruiterMapping>(0);
	private Set<RecruiterPanelistMapping> recruiterPanelistMappingsForRecruiter = new HashSet<RecruiterPanelistMapping>(
			0);
	private Set<Task> tasksForOwner = new HashSet<Task>(0);
	private ProfessionalProfile professionalProfile;
	private Set<RecruiterPanelistMapping> recruiterPanelistMappingsForPanelist = new HashSet<RecruiterPanelistMapping>(
			0);
	private Set<TrainerPresentor> trainerPresentorsForPresentorId = new HashSet<TrainerPresentor>(0);
	private Set<Attendance> attendancesForTakenBy = new HashSet<Attendance>(0);
	private Set<Report> reports = new HashSet<Report>(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<BatchStudents> batchStudentses = new HashSet<BatchStudents>(0);
	private Set<SubUser> subUsersForSubIstarUser = new HashSet<SubUser>(0);
	private Set<Attendance> attendancesForTakenBy_1 = new HashSet<Attendance>(0);
	private Set<TaskType> taskTypes = new HashSet<TaskType>(0);
	private Set<JobPanelistMapping> jobPanelistMappings = new HashSet<JobPanelistMapping>(0);
	private Set<Task> tasksForActor = new HashSet<Task>(0);
	private Set<TrainerPresentor> trainerPresentorsForTrainerId = new HashSet<TrainerPresentor>(0);
	private Set<ExceptionLog> exceptionLogs = new HashSet<ExceptionLog>(0);
	private Set<Team> teams_1 = new HashSet<Team>(0);
	private Set<ChatGroup> chatGroups = new HashSet<ChatGroup>(0);
	private Set<StudentPlaylist> studentPlaylists = new HashSet<StudentPlaylist>(0);
	private Set<Notification> notificationsForReceiverId = new HashSet<Notification>(0);

	// Constructors

	/** default constructor */
	public IstarUser() {
	}

	/** minimal constructor */
	public IstarUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/** full constructor */
	public IstarUser(String email, String password, Timestamp createdAt, Long mobile, String authToken, String loginType,
			Set<Task> tasksForAssigneeMember, Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings,
			Set<UserOrgMapping> userOrgMappings, UserProfile userProfile, Set<TrainerFeedback> trainerFeedbacks,
			Set<Job> jobs, Set<Team> teams, Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses,
			Set<StageLog> stageLogs, Set<Campaign> campaignsForRecruiter, Set<Attendance> attendancesForUserId,
			Set<SubUser> subUsersForIstarUser, Set<TaskLog> taskLogs, Set<Campaign> campaignsForOwner,
			Set<Notification> notificationsForSenderId, Set<StudentAssessment> studentAssessments,
			Set<TrainerBatch> trainerBatchs, Set<Project> projects, Set<TaskFeedback> taskFeedbacks,
			Set<CollegeRecruiterMapping> collegeRecruiterMappings,
			Set<RecruiterPanelistMapping> recruiterPanelistMappingsForRecruiter, Set<Task> tasksForOwner,
			ProfessionalProfile professionalProfile, Set<RecruiterPanelistMapping> recruiterPanelistMappingsForPanelist,
			Set<TrainerPresentor> trainerPresentorsForPresentorId, Set<Attendance> attendancesForTakenBy,
			Set<Report> reports, Set<UserRole> userRoles, Set<BatchStudents> batchStudentses,
			Set<SubUser> subUsersForSubIstarUser, Set<Attendance> attendancesForTakenBy_1, Set<TaskType> taskTypes,
			Set<JobPanelistMapping> jobPanelistMappings, Set<Task> tasksForActor,
			Set<TrainerPresentor> trainerPresentorsForTrainerId, Set<ExceptionLog> exceptionLogs, Set<Team> teams_1,
			Set<ChatGroup> chatGroups, Set<StudentPlaylist> studentPlaylists,
			Set<Notification> notificationsForReceiverId) {
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.mobile = mobile;
		this.authToken = authToken;
		this.loginType = loginType;
		this.tasksForAssigneeMember = tasksForAssigneeMember;
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
		this.userOrgMappings = userOrgMappings;
		this.userProfile = userProfile;
		this.trainerFeedbacks = trainerFeedbacks;
		this.jobs = jobs;
		this.teams = teams;
		this.trainerSkillDistrubutionStatses = trainerSkillDistrubutionStatses;
		this.stageLogs = stageLogs;
		this.campaignsForRecruiter = campaignsForRecruiter;
		this.attendancesForUserId = attendancesForUserId;
		this.subUsersForIstarUser = subUsersForIstarUser;
		this.taskLogs = taskLogs;
		this.campaignsForOwner = campaignsForOwner;
		this.notificationsForSenderId = notificationsForSenderId;
		this.studentAssessments = studentAssessments;
		this.trainerBatchs = trainerBatchs;
		this.projects = projects;
		this.taskFeedbacks = taskFeedbacks;
		this.collegeRecruiterMappings = collegeRecruiterMappings;
		this.recruiterPanelistMappingsForRecruiter = recruiterPanelistMappingsForRecruiter;
		this.tasksForOwner = tasksForOwner;
		this.professionalProfile = professionalProfile;
		this.recruiterPanelistMappingsForPanelist = recruiterPanelistMappingsForPanelist;
		this.trainerPresentorsForPresentorId = trainerPresentorsForPresentorId;
		this.attendancesForTakenBy = attendancesForTakenBy;
		this.reports = reports;
		this.userRoles = userRoles;
		this.batchStudentses = batchStudentses;
		this.subUsersForSubIstarUser = subUsersForSubIstarUser;
		this.attendancesForTakenBy_1 = attendancesForTakenBy_1;
		this.taskTypes = taskTypes;
		this.jobPanelistMappings = jobPanelistMappings;
		this.tasksForActor = tasksForActor;
		this.trainerPresentorsForTrainerId = trainerPresentorsForTrainerId;
		this.exceptionLogs = exceptionLogs;
		this.teams_1 = teams_1;
		this.chatGroups = chatGroups;
		this.studentPlaylists = studentPlaylists;
		this.notificationsForReceiverId = notificationsForReceiverId;
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

	@Column(name = "email", nullable = false)

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "mobile")

	public Long getMobile() {
		return this.mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Column(name = "auth_token")

	public String getAuthToken() {
		return this.authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Column(name = "login_type")
		
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByAssigneeMember")

	public Set<Task> getTasksForAssigneeMember() {
		return this.tasksForAssigneeMember;
	}

	public void setTasksForAssigneeMember(Set<Task> tasksForAssigneeMember) {
		this.tasksForAssigneeMember = tasksForAssigneeMember;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<RecruiterJobTaskCollegeMapping> getRecruiterJobTaskCollegeMappings() {
		return this.recruiterJobTaskCollegeMappings;
	}

	public void setRecruiterJobTaskCollegeMappings(
			Set<RecruiterJobTaskCollegeMapping> recruiterJobTaskCollegeMappings) {
		this.recruiterJobTaskCollegeMappings = recruiterJobTaskCollegeMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "istarUser")

	public Set<UserOrgMapping> getUserOrgMappings() {
		return this.userOrgMappings;
	}

	public void setUserOrgMappings(Set<UserOrgMapping> userOrgMappings) {
		this.userOrgMappings = userOrgMappings;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "istarUser")

	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TrainerFeedback> getTrainerFeedbacks() {
		return this.trainerFeedbacks;
	}

	public void setTrainerFeedbacks(Set<TrainerFeedback> trainerFeedbacks) {
		this.trainerFeedbacks = trainerFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUsers")

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TrainerSkillDistrubutionStats> getTrainerSkillDistrubutionStatses() {
		return this.trainerSkillDistrubutionStatses;
	}

	public void setTrainerSkillDistrubutionStatses(Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses) {
		this.trainerSkillDistrubutionStatses = trainerSkillDistrubutionStatses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<StageLog> getStageLogs() {
		return this.stageLogs;
	}

	public void setStageLogs(Set<StageLog> stageLogs) {
		this.stageLogs = stageLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByRecruiter")

	public Set<Campaign> getCampaignsForRecruiter() {
		return this.campaignsForRecruiter;
	}

	public void setCampaignsForRecruiter(Set<Campaign> campaignsForRecruiter) {
		this.campaignsForRecruiter = campaignsForRecruiter;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByUserId")

	public Set<Attendance> getAttendancesForUserId() {
		return this.attendancesForUserId;
	}

	public void setAttendancesForUserId(Set<Attendance> attendancesForUserId) {
		this.attendancesForUserId = attendancesForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByIstarUser")

	public Set<SubUser> getSubUsersForIstarUser() {
		return this.subUsersForIstarUser;
	}

	public void setSubUsersForIstarUser(Set<SubUser> subUsersForIstarUser) {
		this.subUsersForIstarUser = subUsersForIstarUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByOwner")

	public Set<Campaign> getCampaignsForOwner() {
		return this.campaignsForOwner;
	}

	public void setCampaignsForOwner(Set<Campaign> campaignsForOwner) {
		this.campaignsForOwner = campaignsForOwner;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserBySenderId")

	public Set<Notification> getNotificationsForSenderId() {
		return this.notificationsForSenderId;
	}

	public void setNotificationsForSenderId(Set<Notification> notificationsForSenderId) {
		this.notificationsForSenderId = notificationsForSenderId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<StudentAssessment> getStudentAssessments() {
		return this.studentAssessments;
	}

	public void setStudentAssessments(Set<StudentAssessment> studentAssessments) {
		this.studentAssessments = studentAssessments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TrainerBatch> getTrainerBatchs() {
		return this.trainerBatchs;
	}

	public void setTrainerBatchs(Set<TrainerBatch> trainerBatchs) {
		this.trainerBatchs = trainerBatchs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TaskFeedback> getTaskFeedbacks() {
		return this.taskFeedbacks;
	}

	public void setTaskFeedbacks(Set<TaskFeedback> taskFeedbacks) {
		this.taskFeedbacks = taskFeedbacks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<CollegeRecruiterMapping> getCollegeRecruiterMappings() {
		return this.collegeRecruiterMappings;
	}

	public void setCollegeRecruiterMappings(Set<CollegeRecruiterMapping> collegeRecruiterMappings) {
		this.collegeRecruiterMappings = collegeRecruiterMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByRecruiter")

	public Set<RecruiterPanelistMapping> getRecruiterPanelistMappingsForRecruiter() {
		return this.recruiterPanelistMappingsForRecruiter;
	}

	public void setRecruiterPanelistMappingsForRecruiter(
			Set<RecruiterPanelistMapping> recruiterPanelistMappingsForRecruiter) {
		this.recruiterPanelistMappingsForRecruiter = recruiterPanelistMappingsForRecruiter;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByOwner")

	public Set<Task> getTasksForOwner() {
		return this.tasksForOwner;
	}

	public void setTasksForOwner(Set<Task> tasksForOwner) {
		this.tasksForOwner = tasksForOwner;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "istarUser")

	public ProfessionalProfile getProfessionalProfile() {
		return this.professionalProfile;
	}

	public void setProfessionalProfile(ProfessionalProfile professionalProfile) {
		this.professionalProfile = professionalProfile;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByPanelist")

	public Set<RecruiterPanelistMapping> getRecruiterPanelistMappingsForPanelist() {
		return this.recruiterPanelistMappingsForPanelist;
	}

	public void setRecruiterPanelistMappingsForPanelist(
			Set<RecruiterPanelistMapping> recruiterPanelistMappingsForPanelist) {
		this.recruiterPanelistMappingsForPanelist = recruiterPanelistMappingsForPanelist;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByPresentorId")

	public Set<TrainerPresentor> getTrainerPresentorsForPresentorId() {
		return this.trainerPresentorsForPresentorId;
	}

	public void setTrainerPresentorsForPresentorId(Set<TrainerPresentor> trainerPresentorsForPresentorId) {
		this.trainerPresentorsForPresentorId = trainerPresentorsForPresentorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByTakenBy")

	public Set<Attendance> getAttendancesForTakenBy() {
		return this.attendancesForTakenBy;
	}

	public void setAttendancesForTakenBy(Set<Attendance> attendancesForTakenBy) {
		this.attendancesForTakenBy = attendancesForTakenBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "istarUser")

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<BatchStudents> getBatchStudentses() {
		return this.batchStudentses;
	}

	public void setBatchStudentses(Set<BatchStudents> batchStudentses) {
		this.batchStudentses = batchStudentses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserBySubIstarUser")

	public Set<SubUser> getSubUsersForSubIstarUser() {
		return this.subUsersForSubIstarUser;
	}

	public void setSubUsersForSubIstarUser(Set<SubUser> subUsersForSubIstarUser) {
		this.subUsersForSubIstarUser = subUsersForSubIstarUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByTakenBy")

	public Set<Attendance> getAttendancesForTakenBy_1() {
		return this.attendancesForTakenBy_1;
	}

	public void setAttendancesForTakenBy_1(Set<Attendance> attendancesForTakenBy_1) {
		this.attendancesForTakenBy_1 = attendancesForTakenBy_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<TaskType> getTaskTypes() {
		return this.taskTypes;
	}

	public void setTaskTypes(Set<TaskType> taskTypes) {
		this.taskTypes = taskTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<JobPanelistMapping> getJobPanelistMappings() {
		return this.jobPanelistMappings;
	}

	public void setJobPanelistMappings(Set<JobPanelistMapping> jobPanelistMappings) {
		this.jobPanelistMappings = jobPanelistMappings;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByActor")

	public Set<Task> getTasksForActor() {
		return this.tasksForActor;
	}

	public void setTasksForActor(Set<Task> tasksForActor) {
		this.tasksForActor = tasksForActor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByTrainerId")

	public Set<TrainerPresentor> getTrainerPresentorsForTrainerId() {
		return this.trainerPresentorsForTrainerId;
	}

	public void setTrainerPresentorsForTrainerId(Set<TrainerPresentor> trainerPresentorsForTrainerId) {
		this.trainerPresentorsForTrainerId = trainerPresentorsForTrainerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<ExceptionLog> getExceptionLogs() {
		return this.exceptionLogs;
	}

	public void setExceptionLogs(Set<ExceptionLog> exceptionLogs) {
		this.exceptionLogs = exceptionLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<Team> getTeams_1() {
		return this.teams_1;
	}

	public void setTeams_1(Set<Team> teams_1) {
		this.teams_1 = teams_1;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "chat_group_member", schema = "public", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "chat_group_id", nullable = false, updatable = false) })

	public Set<ChatGroup> getChatGroups() {
		return this.chatGroups;
	}

	public void setChatGroups(Set<ChatGroup> chatGroups) {
		this.chatGroups = chatGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUser")

	public Set<StudentPlaylist> getStudentPlaylists() {
		return this.studentPlaylists;
	}

	public void setStudentPlaylists(Set<StudentPlaylist> studentPlaylists) {
		this.studentPlaylists = studentPlaylists;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "istarUserByReceiverId")

	public Set<Notification> getNotificationsForReceiverId() {
		return this.notificationsForReceiverId;
	}

	public void setNotificationsForReceiverId(Set<Notification> notificationsForReceiverId) {
		this.notificationsForReceiverId = notificationsForReceiverId;
	}

	@Transient
	public Set<SkillPrecentile> getSkillPercentiles() {
		// TODO Auto-generated method stub
		return null;
	}

}