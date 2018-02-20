/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

import com.viksitpro.core.skill.pojo.LearningObjective;

/**
 * @author mayank
 *
 */
public class DeliveryLesson {

	Integer id;
	String lessonName;
	ArrayList<LearningObjective> mappedLO;
	Boolean isPerfect;
	
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
	public ArrayList<LearningObjective> getMappedLO() {
		return mappedLO;
	}
	public void setMappedLO(ArrayList<LearningObjective> mappedLO) {
		this.mappedLO = mappedLO;
	}
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliveryLesson() {
		super();
		
	}
	
	
	
}
