/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class DeliverySession {

	Integer id;
	String sessionName;
	ArrayList<DeliveryLesson> lessons;
	
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
	
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	public DeliverySession() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
