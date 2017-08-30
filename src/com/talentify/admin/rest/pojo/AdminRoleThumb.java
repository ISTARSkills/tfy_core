/**
 * 
 */
package com.talentify.admin.rest.pojo;

/**
 * @author mayank
 *
 */
public class AdminRoleThumb {
	Integer id;
	String name;
	String imageUrl;
	Float avgRating;
	Integer totalStudents;
	Float attendancePercentage;
	Float performance;
	Float completionPercentage;
	
	
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public Float getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(Float completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	public AdminRoleThumb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
