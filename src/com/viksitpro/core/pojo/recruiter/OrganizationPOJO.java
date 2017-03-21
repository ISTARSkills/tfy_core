package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organization")
public class OrganizationPOJO {

	private Integer id;
	private String name;
	private String orgType;
	private String industry;
	private String profile;
	private String image;
	private String website;
	private Integer founded;
	private String employeeCount;
	private String contactName;
	private String contactEmail;
	private Long contactPhone;
	private String createdAt;
	private String updatedAt;
	private AddressPOJO address;
	
	
	@XmlAttribute(name = "organizationId", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "address", required=false)
	public AddressPOJO getAddress() {
		return address;
	}
	public void setAddress(AddressPOJO address) {
		this.address = address;
	}
	
	@XmlAttribute(name = "name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "type", required=false)
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	@XmlAttribute(name = "industry", required=false)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@XmlAttribute(name = "profile", required=false)
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	@XmlAttribute(name = "image", required=false)
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@XmlAttribute(name = "website", required=false)
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@XmlAttribute(name = "founded", required=false)
	public Integer getFounded() {
		return founded;
	}
	public void setFounded(Integer founded) {
		this.founded = founded;
	}
	
	@XmlAttribute(name = "employeeCount", required=false)
	public String getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}
	
	@XmlAttribute(name = "contactName", required=false)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@XmlAttribute(name = "contactEmail", required=false)
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	@XmlAttribute(name = "contactPhone", required=false)
	public Long getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(Long contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@XmlAttribute(name = "createdAt", required=false)
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	@XmlAttribute(name = "updatedAt", required=false)
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
