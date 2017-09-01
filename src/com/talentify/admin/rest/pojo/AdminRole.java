/**
 * 
 */
package com.talentify.admin.rest.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class AdminRole {

	Integer id;
	String name;
	Float avgRating;
	Integer totalStudents;
	Float attendancePercentage;
	Float performance;
	Float wizard;
	Float master;
	Float rookie;
	Float apprentice;
	String imageUrl;
	
	ArrayList<AdminGroupThumb> groups;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Float avgRating) {
		this.avgRating = avgRating;
	}
	public Integer getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}
	public Float getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(Float attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	public Float getPerformance() {
		return performance;
	}
	public void setPerformance(Float performance) {
		this.performance = performance;
	}
	public Float getWizard() {
		return wizard;
	}
	public void setWizard(Float wizard) {
		this.wizard = wizard;
	}
	public Float getMaster() {
		return master;
	}
	public void setMaster(Float master) {
		this.master = master;
	}
	public Float getRookie() {
		return rookie;
	}
	public void setRookie(Float rookie) {
		this.rookie = rookie;
	}
	public Float getApprentice() {
		return apprentice;
	}
	public void setApprentice(Float apprentice) {
		this.apprentice = apprentice;
	}
	public ArrayList<AdminGroupThumb> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<AdminGroupThumb> groups) {
		this.groups = groups;
	}
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public AdminRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
