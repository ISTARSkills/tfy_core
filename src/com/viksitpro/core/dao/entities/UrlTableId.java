package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UrlTableId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class UrlTableId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String url;
	private String type;

	// Constructors

	/** default constructor */
	public UrlTableId() {
	}

	/** minimal constructor */
	public UrlTableId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public UrlTableId(Integer id, String url, String type) {
		this.id = id;
		this.url = url;
		this.type = type;
	}

	// Property accessors

	@Column(name = "id", nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url")

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type")

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UrlTableId))
			return false;
		UrlTableId castOther = (UrlTableId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null && castOther.getUrl() != null
						&& this.getUrl().equals(castOther.getUrl())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null && castOther.getType() != null
						&& this.getType().equals(castOther.getType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result + (getType() == null ? 0 : this.getType().hashCode());
		return result;
	}

}