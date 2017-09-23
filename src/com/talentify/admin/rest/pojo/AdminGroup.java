/**
 * 
 */
package com.talentify.admin.rest.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class AdminGroup {

	Integer id;
	String groupName ;
	String type;
	Float averageRating;
	ArrayList<AdminRoleThumb> roles ;
	Integer totalStudents;
	Float attendancePercentage;
	Float performance;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}
	public ArrayList<AdminRoleThumb> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<AdminRoleThumb> roles) {
		this.roles = roles;
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
	public AdminGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
}
