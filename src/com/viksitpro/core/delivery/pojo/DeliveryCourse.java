/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class DeliveryCourse {

	Integer id;
	String courseName;
	
	ArrayList<DeliveryModule> modules;

	Boolean isPerfect;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public ArrayList<DeliveryModule> getModules() {
		return modules;
	}
	public void setModules(ArrayList<DeliveryModule> modules) {
		this.modules = modules;
	}
	public DeliveryCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	
	
}
