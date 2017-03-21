package com.viksitpro.core.dao.entities;

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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SkillObjective entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "skill_objective", schema = "public")

public class SkillObjective implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String name;
	private Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses = new HashSet<TrainerSkillDistrubutionStats>(
			0);
	private Set<Course> courses = new HashSet<Course>(0);
	private Set<Question> questions = new HashSet<Question>(0);
	private Set<SkillRating> skillRatings = new HashSet<SkillRating>(0);
	private Set<Lesson> lessons = new HashSet<Lesson>(0);
	private Set<BadgeSkill> badgeSkills = new HashSet<BadgeSkill>(0);
	private Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks = new HashSet<JobRoleSkillBenchmark>(0);
	private Set<Module> modules = new HashSet<Module>(0);
	private Set<SkillObjective> skillObjectivesForLearningObjectiveId = new HashSet<SkillObjective>(0);
	private Set<Cmsession> cmsessions = new HashSet<Cmsession>(0);
	private Set<SkillObjective> skillObjectivesForSkillId = new HashSet<SkillObjective>(0);

	// Constructors

	/** default constructor */
	public SkillObjective() {
	}

	/** full constructor */
	public SkillObjective(String type, String name, Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses,
			Set<Course> courses, Set<Question> questions, Set<SkillRating> skillRatings, Set<Lesson> lessons,
			Set<BadgeSkill> badgeSkills, Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks, Set<Module> modules,
			Set<SkillObjective> skillObjectivesForLearningObjectiveId, Set<Cmsession> cmsessions,
			Set<SkillObjective> skillObjectivesForSkillId) {
		this.type = type;
		this.name = name;
		this.trainerSkillDistrubutionStatses = trainerSkillDistrubutionStatses;
		this.courses = courses;
		this.questions = questions;
		this.skillRatings = skillRatings;
		this.lessons = lessons;
		this.badgeSkills = badgeSkills;
		this.jobRoleSkillBenchmarks = jobRoleSkillBenchmarks;
		this.modules = modules;
		this.skillObjectivesForLearningObjectiveId = skillObjectivesForLearningObjectiveId;
		this.cmsessions = cmsessions;
		this.skillObjectivesForSkillId = skillObjectivesForSkillId;
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

	@Column(name = "type")

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skillObjective")

	public Set<TrainerSkillDistrubutionStats> getTrainerSkillDistrubutionStatses() {
		return this.trainerSkillDistrubutionStatses;
	}

	public void setTrainerSkillDistrubutionStatses(Set<TrainerSkillDistrubutionStats> trainerSkillDistrubutionStatses) {
		this.trainerSkillDistrubutionStatses = trainerSkillDistrubutionStatses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "course_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "skill_objective_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", nullable = false, updatable = false) })

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "question_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "learning_objectiveid", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "questionid", nullable = false, updatable = false) })

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skillObjective")

	public Set<SkillRating> getSkillRatings() {
		return this.skillRatings;
	}

	public void setSkillRatings(Set<SkillRating> skillRatings) {
		this.skillRatings = skillRatings;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "lesson_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "learning_objectiveid", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "lessonid", nullable = false, updatable = false) })

	public Set<Lesson> getLessons() {
		return this.lessons;
	}

	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skillObjective")

	public Set<BadgeSkill> getBadgeSkills() {
		return this.badgeSkills;
	}

	public void setBadgeSkills(Set<BadgeSkill> badgeSkills) {
		this.badgeSkills = badgeSkills;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skillObjective")

	public Set<JobRoleSkillBenchmark> getJobRoleSkillBenchmarks() {
		return this.jobRoleSkillBenchmarks;
	}

	public void setJobRoleSkillBenchmarks(Set<JobRoleSkillBenchmark> jobRoleSkillBenchmarks) {
		this.jobRoleSkillBenchmarks = jobRoleSkillBenchmarks;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "module_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "skill_objective_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "module_id", nullable = false, updatable = false) })

	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skillObjectivesForSkillId")

	public Set<SkillObjective> getSkillObjectivesForLearningObjectiveId() {
		return this.skillObjectivesForLearningObjectiveId;
	}

	public void setSkillObjectivesForLearningObjectiveId(Set<SkillObjective> skillObjectivesForLearningObjectiveId) {
		this.skillObjectivesForLearningObjectiveId = skillObjectivesForLearningObjectiveId;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "cmsession_skill_objective", schema = "public", joinColumns = {
			@JoinColumn(name = "skill_objective_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "cmsession_id", nullable = false, updatable = false) })

	public Set<Cmsession> getCmsessions() {
		return this.cmsessions;
	}

	public void setCmsessions(Set<Cmsession> cmsessions) {
		this.cmsessions = cmsessions;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "skill_learning_obj_mapping", schema = "public", joinColumns = {
			@JoinColumn(name = "learning_objective_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "skill_id", nullable = false, updatable = false) })

	public Set<SkillObjective> getSkillObjectivesForSkillId() {
		return this.skillObjectivesForSkillId;
	}

	public void setSkillObjectivesForSkillId(Set<SkillObjective> skillObjectivesForSkillId) {
		this.skillObjectivesForSkillId = skillObjectivesForSkillId;
	}

}