/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class CourseLevelSkill {

	Integer id;
	String skillName;
	String creationType;	
	ArrayList<ModuleLevelSkill> moduleLevelSkill;

	
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

	public ArrayList<ModuleLevelSkill> getModuleLevelSkill() {
		return moduleLevelSkill;
	}

	public void setModuleLevelSkill(ArrayList<ModuleLevelSkill> moduleLevelSkill) {
		this.moduleLevelSkill = moduleLevelSkill;
	}

	public CourseLevelSkill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreationType() {
		return creationType;
	}

	public void setCreationType(String creationType) {
		this.creationType = creationType;
	}
	
	
}
