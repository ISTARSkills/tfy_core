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
 * ClassroomDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "classroom_details", schema = "public")

public class ClassroomDetails implements java.io.Serializable {

	// Fields

	private Integer id;
	private Organization organization;
	private String classroomIdentifier;
	private Integer maxStudents;
	private String ipAddress;

	// Constructors

	/** default constructor */
	public ClassroomDetails() {
	}

	/** full constructor */
	public ClassroomDetails(Organization organization, String classroomIdentifier, Integer maxStudents,
			String ipAddress) {
		this.organization = organization;
		this.classroomIdentifier = classroomIdentifier;
		this.maxStudents = maxStudents;
		this.ipAddress = ipAddress;
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
	@JoinColumn(name = "organization_id")

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name = "classroom_identifier")

	public String getClassroomIdentifier() {
		return this.classroomIdentifier;
	}

	public void setClassroomIdentifier(String classroomIdentifier) {
		this.classroomIdentifier = classroomIdentifier;
	}

	@Column(name = "max_students")

	public Integer getMaxStudents() {
		return this.maxStudents;
	}

	public void setMaxStudents(Integer maxStudents) {
		this.maxStudents = maxStudents;
	}

	@Column(name = "ip_address")

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}