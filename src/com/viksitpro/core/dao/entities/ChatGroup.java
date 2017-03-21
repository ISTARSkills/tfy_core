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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ChatGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chat_group", schema = "public")

public class ChatGroup implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Timestamp createdAt;
	private Integer createdBy;
	private Set<IstarUser> istarUsers = new HashSet<IstarUser>(0);

	// Constructors

	/** default constructor */
	public ChatGroup() {
	}

	/** full constructor */
	public ChatGroup(String name, Timestamp createdAt, Integer createdBy, Set<IstarUser> istarUsers) {
		this.name = name;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
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

	@Column(name = "created_by")

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "chatGroups")

	public Set<IstarUser> getIstarUsers() {
		return this.istarUsers;
	}

	public void setIstarUsers(Set<IstarUser> istarUsers) {
		this.istarUsers = istarUsers;
	}

}