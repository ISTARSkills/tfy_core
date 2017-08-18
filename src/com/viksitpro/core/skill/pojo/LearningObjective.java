/**
 * 
 */
package com.viksitpro.core.skill.pojo;

import java.util.ArrayList;

import com.viksitpro.core.dao.entities.Lesson;
import com.viksitpro.core.dao.entities.Question;

/**
 * @author mayank
 *
 */
public class LearningObjective {

	Integer id;
	String learningObjectiveName;
	String creationType;
	ArrayList<Lesson> lessons;
	ArrayList<Question> questions;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLearningObjectiveName() {
		return learningObjectiveName;
	}
	public void setLearningObjectiveName(String learningObjectiveName) {
		this.learningObjectiveName = learningObjectiveName;
	}
	public String getCreationType() {
		return creationType;
	}
	public void setCreationType(String creationType) {
		this.creationType = creationType;
	}
	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(ArrayList<Lesson> lessons) {
		this.lessons = lessons;
	}
	public LearningObjective() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
}
