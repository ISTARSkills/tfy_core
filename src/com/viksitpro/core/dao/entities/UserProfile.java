package com.viksitpro.core.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * UserProfile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_profile", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))

public class UserProfile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Address address;
	private IstarUser istarUser;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String profileImage;
	private Long aadharNo;
	private String fatherName;
	private String motherName;
	private String userCategory;
	
	
	private String religion;
	private String casteCategory;
	private String placeOfBirth;
	
	
	
	@Column(name = "religion", nullable = false)
	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "caste_category", nullable = false)

	public String getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}

	@Column(name = "place_of_birth", nullable = false)

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	// Constructors

	/** default constructor */
	public UserProfile() {
	}

	/** full constructor */
	public UserProfile(Address address, IstarUser istarUser, String firstName, String lastName, Date dob, String gender,
			String profileImage, Long aadharNo) {
		this.address = address;
		this.istarUser = istarUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.profileImage = profileImage;
		this.aadharNo = aadharNo;
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
	@JoinColumn(name = "address_id")

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "first_name")

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 13)

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "gender")

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "profile_image")

	public String getProfileImage() {
			return this.profileImage;
	}

	@Transient
	public String getImage(){
		if (this.profileImage != null) {
			return this.profileImage;
		} else {
			try {
				return "users/" + firstName.trim().substring(0, 1).toUpperCase() + ".png";
			} catch (Exception e) {
				System.err.println("UserID ->"+ user_id);
			}
		}
		return profileImage;
	}
	
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Column(name = "aadhar_no")

	public Long getAadharNo() {
		return this.aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	@Column(name = "father_name")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "mother_name")
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	@Column(name = "user_category", nullable=true)
	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	
	
	
	
}