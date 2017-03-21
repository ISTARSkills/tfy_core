package com.viksitpro.core.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MetaColumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "meta_column", schema = "public")

public class MetaColumn implements java.io.Serializable {

	// Fields

	private Integer id;
	private String entityName;
	private String propertyName;
	private String dataType;
	private Set<Metadata> metadatas = new HashSet<Metadata>(0);

	// Constructors

	/** default constructor */
	public MetaColumn() {
	}

	/** full constructor */
	public MetaColumn(String entityName, String propertyName, String dataType, Set<Metadata> metadatas) {
		this.entityName = entityName;
		this.propertyName = propertyName;
		this.dataType = dataType;
		this.metadatas = metadatas;
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

	@Column(name = "entity_name")

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Column(name = "property_name")

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "data_type")

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "metaColumn")

	public Set<Metadata> getMetadatas() {
		return this.metadatas;
	}

	public void setMetadatas(Set<Metadata> metadatas) {
		this.metadatas = metadatas;
	}

}