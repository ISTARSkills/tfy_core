/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

import com.viksitpro.core.skill.pojo.LearningObjective;
import com.viksitpro.core.skill.pojo.SessionLevelSkill;

/**
 * @author mayank
 *
 */
public class DeliverySession {

	Integer id;
	String sessionName;
	ArrayList<DeliveryLesson> lessons;
	ArrayList<LearningObjective> los;
	ArrayList<Integer>mappedLessons;
	ArrayList<SessionLevelSkill>sessionSkills;
	Boolean isPerfect;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public ArrayList<DeliveryLesson> getLessons() {
		return lessons;
	}
	public void setLessons(ArrayList<DeliveryLesson> lessons) {
		this.lessons = lessons;
	}
	public ArrayList<LearningObjective> getLos() {
		return los;
	}
	public void setLos(ArrayList<LearningObjective> los) {
		this.los = los;
	}
	public ArrayList<Integer> getMappedLessons() {
		return mappedLessons;
	}
	public void setMappedLessons(ArrayList<Integer> mappedLessons) {
		this.mappedLessons = mappedLessons;
	}
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliverySession() {
		super();
		
	}
	public ArrayList<SessionLevelSkill> getSessionSkills() {
		return sessionSkills;
	}
	public void setSessionSkills(ArrayList<SessionLevelSkill> sessionSkills) {
		this.sessionSkills = sessionSkills;
	}
	
	
}
