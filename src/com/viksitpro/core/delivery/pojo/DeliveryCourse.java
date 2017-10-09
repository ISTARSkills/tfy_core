/**
 * 
 */
package com.viksitpro.core.delivery.pojo;

import java.util.ArrayList;

import com.viksitpro.core.dao.entities.Module;
import com.viksitpro.core.skill.pojo.ModuleLevelSkill;

/**
 * @author mayank
 *
 */
public class DeliveryCourse {

	Integer id;
	String courseName;
	ArrayList<ModuleLevelSkill> moduleLevelSkill;
	ArrayList<DeliveryModule> modules;
	ArrayList<Integer> mappedModules;
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
	public ArrayList<ModuleLevelSkill> getModuleLevelSkill() {
		return moduleLevelSkill;
	}
	public void setModuleLevelSkill(ArrayList<ModuleLevelSkill> moduleLevelSkill) {
		this.moduleLevelSkill = moduleLevelSkill;
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
	public ArrayList<Integer> getMappedModules() {
		return mappedModules;
	}
	public void setMappedModules(ArrayList<Integer> mappedModules) {
		this.mappedModules = mappedModules;
	}
	public Boolean getIsPerfect() {
		return isPerfect;
	}
	public void setIsPerfect(Boolean isPerfect) {
		this.isPerfect = isPerfect;
	}
	
	
}
