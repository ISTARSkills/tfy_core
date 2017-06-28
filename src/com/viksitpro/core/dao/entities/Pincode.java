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
 * Pincode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pincode", schema = "public")

public class Pincode implements java.io.Serializable {

	// Fields

	private Integer id;
	private String city;
	private String country;
	private Integer pin;
	private String state;
	private Double lattiude;
	private Double longitude;
	private String stateCode;
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<Address> addresses_1 = new HashSet<Address>(0);
	private String name;
	private String pc_name;

	// Constructors

	/** default constructor */
	public Pincode() {
	}

	/** minimal constructor */
	public Pincode(String city, String country, String state) {
		this.city = city;
		this.country = country;
		this.state = state;
	}

	/** full constructor */
	public Pincode(String city, String country, Integer pin, String state, Double lattiude, Double longitude,
			String stateCode, Set<Address> addresses, Set<Address> addresses_1) {
		this.city = city;
		this.country = country;
		this.pin = pin;
		this.state = state;
		this.lattiude = lattiude;
		this.longitude = longitude;
		this.stateCode = stateCode;
		this.addresses = addresses;
		this.addresses_1 = addresses_1;
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

	@Column(name = "city", nullable = false)

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = false)

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "pin")

	public Integer getPin() {
		return this.pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Column(name = "state", nullable = false)

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "lattiude", precision = 17, scale = 17)

	public Double getLattiude() {
		return this.lattiude;
	}

	public void setLattiude(Double lattiude) {
		this.lattiude = lattiude;
	}

	@Column(name = "longitude", precision = 17, scale = 17)

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "state_code")

	public String getStateCode() {
		return this.stateCode;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pc_name")
	public String getPc_name() {
		return pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pincode")

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pincode")

	public Set<Address> getAddresses_1() {
		return this.addresses_1;
	}

	public void setAddresses_1(Set<Address> addresses_1) {
		this.addresses_1 = addresses_1;
	}
	
	
	

}