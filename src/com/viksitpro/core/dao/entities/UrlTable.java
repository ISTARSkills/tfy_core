package com.viksitpro.core.dao.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UrlTable entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "url_table", schema = "public")

public class UrlTable implements java.io.Serializable {

	// Fields

	private UrlTableId id;

	// Constructors

	/** default constructor */
	public UrlTable() {
	}

	/** full constructor */
	public UrlTable(UrlTableId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false)),
			@AttributeOverride(name = "url", column = @Column(name = "url")),
			@AttributeOverride(name = "type", column = @Column(name = "type")) })

	public UrlTableId getId() {
		return this.id;
	}

	public void setId(UrlTableId id) {
		this.id = id;
	}

}