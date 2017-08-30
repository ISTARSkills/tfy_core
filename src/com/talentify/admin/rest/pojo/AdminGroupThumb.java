/**
 * 
 */
package com.talentify.admin.rest.pojo;

/**
 * @author mayank
 *
 */
public class AdminGroupThumb {
	
	Integer id;
	String name;
	String type;
	String roleGroupName;
	Integer totalStudents;
	Float attendancePercentage;
	Float performance;
	Float completionPercentage;
	Float avgRating;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoleGroupName() {
		return roleGroupName;
	}
	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
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
	public Float getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(Float completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	public AdminGroupThumb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Float getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Float avgRating) {
		this.avgRating = avgRating;
	}
	
	

}
