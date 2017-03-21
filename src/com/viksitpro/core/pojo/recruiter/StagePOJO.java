package com.viksitpro.core.pojo.recruiter;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.viksitpro.core.dao.entities.PmStageAction;

@XmlRootElement(name = "stage")
public class StagePOJO {

	Integer id;
	String name;
	String nextPossibleStages;
	String description;
	Integer orderId;
	String allowedRoles;
	String type;
	Integer studentCount;
	List<PmStageAction> stageActions;
	
	@XmlAttribute (name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
	@XmlAttribute (name="name" ,required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute (name="nextPossibleStages", required=false)
	public String getNextPossibleStages() {
		return nextPossibleStages;
	}
	public void setNextPossibleStages(String nextPossibleStages) {
		this.nextPossibleStages = nextPossibleStages;
	}
	
	@XmlAttribute (name="description",required=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlAttribute (name="orderId", required=false)
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}		
		
	@XmlAttribute (name="allowedRoles", required=false)
	public String getAllowedRoles() {
		return allowedRoles;
	}
	public void setAllowedRoles(String allowedRoles) {
		this.allowedRoles = allowedRoles;
	}
	
	@XmlAttribute (name="type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute (name="type", required=false)
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	
	@XmlElementWrapper(name="stageActions" , required=false)
	@XmlElement(name="stageAction")
	public List<PmStageAction> getStageActions() {
		return stageActions;
	}
	public void setStageActions(List<PmStageAction> stageActions) {
		this.stageActions = stageActions;
	}
}
