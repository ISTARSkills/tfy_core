package com.viksitpro.core.pojo.recruiter;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "team")
public class TeamPOJO {

	private Integer teamId;
	private Integer istarUserId;
	private String projectName;
	private String name;
	private String description;
	private List<IstarUserPOJO> teamMembers;
	
	
	@XmlAttribute(name = "teamId", required=false)
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	@XmlAttribute(name = "istarUserId", required=false)
	public Integer getIstarUserId() {
		return istarUserId;
	}
	public void setIstarUserId(Integer istarUserId) {
		this.istarUserId = istarUserId;
	}
	
	@XmlAttribute(name = "projectName", required=false)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@XmlAttribute(name = "name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "description", required=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElementWrapper(name="teamMembers" , required=false)
	@XmlAttribute(name = "teamMember", required=false)
	public List<IstarUserPOJO> getTeamMembers() {
		return teamMembers;
	}
	public void setTeamMembers(List<IstarUserPOJO> teamMembers) {
		this.teamMembers = teamMembers;
	}	
}
