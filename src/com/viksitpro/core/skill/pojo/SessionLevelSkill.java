/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

import com.viksitpro.core.dao.entities.Cmsession;

/**
 * @author mayank
 *
 */
public class SessionLevelSkill {
	Integer id;
	String skillName;
	String creationType;
	
	ArrayList<LearningObjective> learningObjectives;
	/*ArrayList<Cmsession> sessions;*/
	public SessionLevelSkill() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public ArrayList<LearningObjective> getLearningObjectives() {
		return learningObjectives;
	}

	public void setLearningObjectives(ArrayList<LearningObjective> learningObjectives) {
		this.learningObjectives = learningObjectives;
	}
	
	
	
	
}
