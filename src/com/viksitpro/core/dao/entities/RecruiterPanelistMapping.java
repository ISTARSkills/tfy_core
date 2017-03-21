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
 * RecruiterPanelistMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "recruiter_panelist_mapping", schema = "public")

public class RecruiterPanelistMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUserByRecruiter;
	private IstarUser istarUserByPanelist;

	// Constructors

	/** default constructor */
	public RecruiterPanelistMapping() {
	}

	/** full constructor */
	public RecruiterPanelistMapping(IstarUser istarUserByRecruiter, IstarUser istarUserByPanelist) {
		this.istarUserByRecruiter = istarUserByRecruiter;
		this.istarUserByPanelist = istarUserByPanelist;
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
	@JoinColumn(name = "recruiter")

	public IstarUser getIstarUserByRecruiter() {
		return this.istarUserByRecruiter;
	}

	public void setIstarUserByRecruiter(IstarUser istarUserByRecruiter) {
		this.istarUserByRecruiter = istarUserByRecruiter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "panelist")

	public IstarUser getIstarUserByPanelist() {
		return this.istarUserByPanelist;
	}

	public void setIstarUserByPanelist(IstarUser istarUserByPanelist) {
		this.istarUserByPanelist = istarUserByPanelist;
	}

}