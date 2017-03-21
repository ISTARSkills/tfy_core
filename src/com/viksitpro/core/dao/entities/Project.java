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
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", schema = "public")

public class Project implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp completedOn;
	private Timestamp deadline;
	private Boolean active;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Team> teams = new HashSet<Team>(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(IstarUser istarUser, String name, Timestamp createdAt, Timestamp updatedAt, Timestamp completedOn,
			Timestamp deadline, Boolean active, Set<Task> tasks, Set<Team> teams) {
		this.istarUser = istarUser;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.completedOn = completedOn;
		this.deadline = deadline;
		this.active = active;
		this.tasks = tasks;
		this.teams = teams;
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
	@JoinColumn(name = "creator")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "completed_on", length = 29)

	public Timestamp getCompletedOn() {
		return this.completedOn;
	}

	public void setCompletedOn(Timestamp completedOn) {
		this.completedOn = completedOn;
	}

	@Column(name = "deadline", length = 29)

	public Timestamp getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	@Column(name = "active")

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}