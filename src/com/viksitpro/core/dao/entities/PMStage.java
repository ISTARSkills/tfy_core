/**
 * 
 */
package com.viksitpro.core.dao.entities;


import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * @author ComplexObject
 */
public class PMStage{

	Integer id;
	String name;
	String nextPossibleStages;
	String description;
	Integer orderId;
	String allowedRoles;
	String type;
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
	
	@XmlAttribute (name="next_stages", required=false)
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
	
	@XmlAttribute (name="order", required=false)
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}		
		
	@XmlAttribute (name="allowed_roles", required=false)
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
		
	@XmlElementWrapper(name="stage_actions" , required=false)
	@XmlElement(name="stage_action")
	public List<PmStageAction> getStageActions() {
		return stageActions;
	}
	public void setStageActions(List<PmStageAction> stageActions) {
		this.stageActions = stageActions;
	}
	
}
