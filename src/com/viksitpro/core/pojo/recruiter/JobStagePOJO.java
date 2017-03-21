package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jobStages")
public class JobStagePOJO {

	private int id;
	private String name;
	private String description;
	private String type;
	private String allowedRoles;
	private String stageActions;
	
	@XmlAttribute(name = "id", required=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	@XmlAttribute(name = "type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute(name = "allowedRoles", required=false)
	public String getAllowedRoles() {
		return allowedRoles;
	}
	public void setAllowedRoles(String allowedRoles) {
		this.allowedRoles = allowedRoles;
	}
	
	@XmlAttribute(name = "stageActions", required=false)
	public String getStageActions() {
		return stageActions;
	}
	public void setStageActions(String stageActions) {
		this.stageActions = stageActions;
	}
	
}
