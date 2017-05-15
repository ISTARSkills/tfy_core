package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "studentTile")
public class StudentTilePOJO {

	private Integer istarUser;
	private Integer task;
	private String name;
	private String lastCollegeAttended;
	private String lastDegreePursued;
	private String lastAttendedSpecialization;
	private Boolean noShowFlag;
	private String profileImage;
	
	public StudentTilePOJO(){
		
	}

	@XmlAttribute(name = "istarUser", required=false)
	public Integer getIstarUser() {
		return istarUser;
	}

	public void setIstarUser(Integer istarUser) {
		this.istarUser = istarUser;
	}

	@XmlAttribute(name = "task", required=false)
	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

	@XmlAttribute(name = "name", required=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "lastCollegeAttended", required=false)
	public String getLastCollegeAttended() {
		return lastCollegeAttended;
	}

	public void setLastCollegeAttended(String lastCollegeAttended) {
		this.lastCollegeAttended = lastCollegeAttended;
	}

	@XmlAttribute(name = "lastDegreePursued", required=false)
	public String getLastDegreePursued() {
		return lastDegreePursued;
	}

	public void setLastDegreePursued(String lastDegreePursued) {
		this.lastDegreePursued = lastDegreePursued;
	}

	@XmlAttribute(name = "lastAttendedSpecialization", required=false)
	public String getLastAttendedSpecialization() {
		return lastAttendedSpecialization;
	}

	public void setLastAttendedSpecialization(String lastAttendedSpecialization) {
		this.lastAttendedSpecialization = lastAttendedSpecialization;
	}

	@XmlAttribute(name = "noShowFlag", required=false)
	public Boolean getNoShowFlag() {
		return noShowFlag;
	}

	public void setNoShowFlag(Boolean noShowFlag) {
		this.noShowFlag = noShowFlag;
	}

	@XmlAttribute(name = "profileImage", required=false)
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
