/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

import com.viksitpro.core.dao.entities.SkillObjective;
import com.viksitpro.core.skill.pojo.LearningObjective;
import com.viksitpro.core.skill.pojo.ModuleLevelSkill;

/**
 * @author mayank
 *
 */
public class DeliveryLesson {

	Integer id;
	String lessonName;
	ArrayList<ModuleLevelSkill> mappedModuleLevelSkill;
	Boolean isPerfect;
	String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	
	
	public ArrayList<ModuleLevelSkill> getMappedModuleLevelSkill() {
		return mappedModuleLevelSkill;
	}
	public void setMappedModuleLevelSkill(ArrayList<ModuleLevelSkill> mappedModuleLevelSkill) {
		this.mappedModuleLevelSkill = mappedModuleLevelSkill;
	}
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliveryLesson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
