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
	ArrayList<SessionLevelSkill> sessionLevelSkills;
	ArrayList<ModuleLevelSkill> moduleLevelSkills;
	ArrayList<DeliverySession> sessions;
	ArrayList<Integer> mappedSessions;
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
	public ArrayList<SessionLevelSkill> getSessionLevelSkills() {
		return sessionLevelSkills;
	}
	public void setSessionLevelSkills(ArrayList<SessionLevelSkill> sessionLevelSkills) {
		this.sessionLevelSkills = sessionLevelSkills;
	}
	public ArrayList<DeliverySession> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<DeliverySession> sessions) {
		this.sessions = sessions;
	}
	public ArrayList<Integer> getMappedSessions() {
		return mappedSessions;
	}
	public void setMappedSessions(ArrayList<Integer> mappedSessions) {
		this.mappedSessions = mappedSessions;
	}
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliveryModule() {
		super();
		
	}
	public ArrayList<ModuleLevelSkill> getModuleLevelSkills() {
		return moduleLevelSkills;
	}
	public void setModuleLevelSkills(ArrayList<ModuleLevelSkill> moduleLevelSkills) {
		this.moduleLevelSkills = moduleLevelSkills;
	}
	
	
	
}
