/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class ModuleLevelSkill {
	Integer id;
	String skillName;
	String creationType;
	
	ArrayList<SessionLevelSkill> sessionLevelSkill;
	//ArrayList<Module> modules;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCreationType() {
		return creationType;
	}

	public void setCreationType(String creationType) {
		this.creationType = creationType;
	}

	public ArrayList<SessionLevelSkill> getSessionLevelSkill() {
		return sessionLevelSkill;
	}

	public void setSessionLevelSkill(ArrayList<SessionLevelSkill> sessionLevelSkill) {
		this.sessionLevelSkill = sessionLevelSkill;
	}

	public ModuleLevelSkill() {
		super();
		
	}

	/*public ArrayList<Module> getModules() {
		return modules;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}*/
	
	
}
