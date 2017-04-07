package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "istarUser")
public class IstarUserPOJO {

	private Integer istarUserId;
	private String name;
	private String email;
	private String authenticationToken;
	private Boolean isVerified;
	private String role;

	public IstarUserPOJO(){
		
	}
	
	@XmlAttribute(name = "istarUserId", required=false)
	public Integer getIstarUserId() {
		return istarUserId;
	}
	public void setIstarUserId(Integer istarUserId) {
		this.istarUserId = istarUserId;
	}
	
	@XmlAttribute(name = "name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute(name = "email", required=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlAttribute(name = "authenticationToken", required=false)
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	
	@XmlAttribute(name = "isVerified", required=false)	
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@XmlAttribute(name = "role", required=false)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
