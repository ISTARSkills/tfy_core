package com.viksitpro.core.dao.entities;

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
 * Metadata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "metadata", schema = "public")

public class Metadata implements java.io.Serializable {

	// Fields

	private Integer id;
	private MetaColumn metaColumn;
	private Integer entityId;
	private String entityType;
	private String value;

	// Constructors

	/** default constructor */
	public Metadata() {
	}

	/** full constructor */
	public Metadata(MetaColumn metaColumn, Integer entityId, String entityType, String value) {
		this.metaColumn = metaColumn;
		this.entityId = entityId;
		this.entityType = entityType;
		this.value = value;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meta_column_id")

	public MetaColumn getMetaColumn() {
		return this.metaColumn;
	}

	public void setMetaColumn(MetaColumn metaColumn) {
		this.metaColumn = metaColumn;
	}

	@Column(name = "entity_id")

	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	@Column(name = "entity_type")

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	@Column(name = "value", length = 500)

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}