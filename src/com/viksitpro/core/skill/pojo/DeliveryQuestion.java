/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

import com.viksitpro.core.dao.entities.Question;

/**
 * @author mayank
 *
 */
public class DeliveryQuestion {

	Integer Id;
	Question question;
	ArrayList<ModuleLevelSkill> moduleLevelSkill;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public ArrayList<ModuleLevelSkill> getModuleLevelSkill() {
		return moduleLevelSkill;
	}
	public void setModuleLevelSkill(ArrayList<ModuleLevelSkill> moduleLevelSkill) {
		this.moduleLevelSkill = moduleLevelSkill;
	}
	
	
}
