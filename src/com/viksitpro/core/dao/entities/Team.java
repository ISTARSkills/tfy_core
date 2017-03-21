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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Team entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "team", schema = "public")

public class Team implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Project project;
	private String name;
	private String description;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<IstarUser> istarUsers = new HashSet<IstarUser>(0);

	// Constructors

	/** default constructor */
	public Team() {
	}

	/** full constructor */
	public Team(IstarUser istarUser, Project project, String name, String description, Set<Task> tasks,
			Set<IstarUser> istarUsers) {
		this.istarUser = istarUser;
		this.project = project;
		this.name = name;
		this.description = description;
		this.tasks = tasks;
		this.istarUsers = istarUsers;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "team_member", schema = "public", joinColumns = {
			@JoinColumn(name = "team_id", updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", updatable = false) })

	public Set<IstarUser> getIstarUsers() {
		return this.istarUsers;
	}

	public void setIstarUsers(Set<IstarUser> istarUsers) {
		this.istarUsers = istarUsers;
	}

}