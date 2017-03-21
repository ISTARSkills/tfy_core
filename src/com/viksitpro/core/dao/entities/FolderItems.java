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
 * FolderItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "folder_items", schema = "public")

public class FolderItems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Folder folder;
	private Integer itemId;
	private String itemType;
	private Timestamp createdAt;

	// Constructors

	/** default constructor */
	public FolderItems() {
	}

	/** minimal constructor */
	public FolderItems(Folder folder, Integer itemId, String itemType) {
		this.folder = folder;
		this.itemId = itemId;
		this.itemType = itemType;
	}

	/** full constructor */
	public FolderItems(Folder folder, Integer itemId, String itemType, Timestamp createdAt) {
		this.folder = folder;
		this.itemId = itemId;
		this.itemType = itemType;
		this.createdAt = createdAt;
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
	@JoinColumn(name = "folder_id", nullable = false)

	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@Column(name = "item_id", nullable = false)

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_type", nullable = false)

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}