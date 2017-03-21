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
 * Folder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "folder", schema = "public")

public class Folder implements java.io.Serializable {

	// Fields

	private Integer id;
	private Folder folder;
	private String name;
	private Timestamp createdAt;
	private Set<FolderItems> folderItemses = new HashSet<FolderItems>(0);
	private Set<Folder> folders = new HashSet<Folder>(0);

	// Constructors

	/** default constructor */
	public Folder() {
	}

	/** minimal constructor */
	public Folder(Folder folder, String name) {
		this.folder = folder;
		this.name = name;
	}

	/** full constructor */
	public Folder(Folder folder, String name, Timestamp createdAt, Set<FolderItems> folderItemses,
			Set<Folder> folders) {
		this.folder = folder;
		this.name = name;
		this.createdAt = createdAt;
		this.folderItemses = folderItemses;
		this.folders = folders;
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
	@JoinColumn(name = "parent_id", nullable = false)

	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Column(name = "name", nullable = false)

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "folder")

	public Set<FolderItems> getFolderItemses() {
		return this.folderItemses;
	}

	public void setFolderItemses(Set<FolderItems> folderItemses) {
		this.folderItemses = folderItemses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "folder")

	public Set<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(Set<Folder> folders) {
		this.folders = folders;
	}

}