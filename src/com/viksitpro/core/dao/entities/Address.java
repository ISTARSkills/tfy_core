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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "address", schema = "public")

public class Address implements java.io.Serializable {

	// Fields

	private Integer id;
	private Pincode pincode;
	private String addressline1;
	private String addressline2;
	private Double addressGeoLongitude;
	private Double addressGeoLatitude;
	private Set<Organization> organizations = new HashSet<Organization>(0);
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>(0);

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** minimal constructor */
	public Address(Pincode pincode, String addressline1, String addressline2) {
		this.pincode = pincode;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
	}

	/** full constructor */
	public Address(Pincode pincode, String addressline1, String addressline2, Double addressGeoLongitude,
			Double addressGeoLatitude, Set<Organization> organizations, Set<UserProfile> userProfiles) {
		this.pincode = pincode;
		this.addressline1 = addressline1;
		this.addressline2 = addressline2;
		this.addressGeoLongitude = addressGeoLongitude;
		this.addressGeoLatitude = addressGeoLatitude;
		this.organizations = organizations;
		this.userProfiles = userProfiles;
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
	@JoinColumn(name = "pincode_id", nullable = false)

	public Pincode getPincode() {
		return this.pincode;
	}

	public void setPincode(Pincode pincode) {
		this.pincode = pincode;
	}

	@Column(name = "addressline1", nullable = false)

	public String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	@Column(name = "addressline2", nullable = false)

	public String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	@Column(name = "address_geo_longitude", precision = 17, scale = 17)

	public Double getAddressGeoLongitude() {
		return this.addressGeoLongitude;
	}

	public void setAddressGeoLongitude(Double addressGeoLongitude) {
		this.addressGeoLongitude = addressGeoLongitude;
	}

	@Column(name = "address_geo_latitude", precision = 17, scale = 17)

	public Double getAddressGeoLatitude() {
		return this.addressGeoLatitude;
	}

	public void setAddressGeoLatitude(Double addressGeoLatitude) {
		this.addressGeoLatitude = addressGeoLatitude;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")

	public Set<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")

	public Set<UserProfile> getUserProfiles() {
		return this.userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

}