/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

import com.viksitpro.core.skill.pojo.ModuleLevelSkill;
import com.viksitpro.core.skill.pojo.SessionLevelSkill;

/**
 * @author mayank
 *
 */
public class DeliveryModule {

	Integer id;
	String moduleName;	
	ArrayList<DeliverySession> sessions;
	
	
	Boolean isPerfect;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public ArrayList<DeliverySession> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<DeliverySession> sessions) {
		this.sessions = sessions;
	}
	
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliveryModule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
