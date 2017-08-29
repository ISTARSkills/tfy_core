/**
 * 
 */
package com.talentify.admin.rest.pojo;

import java.util.ArrayList;

/**
 * @author mayank
 *
 */
public class EventsCard {

	Integer eventId;
	String date;
	String time;
	Float duration;
	String imageUrl;
	String course;
	
	String groupName;
	String roleName;
	String trainerName;
	String associateTrainerName;
	Float attendancePercentage;	
	Float studentRating;
	Float trainerRating;
	Float performance;
	String status;	
	Integer classroomId;
	String classroomName;
	ArrayList<EventError> errorFlags;
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Float getDuration() {
		return duration;
	}
	public void setDuration(Float duration) {
		this.duration = duration;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getAssociateTrainerName() {
		return associateTrainerName;
	}
	public void setAssociateTrainerName(String associateTrainerName) {
		this.associateTrainerName = associateTrainerName;
	}
	public Float getAttendancePercentage() {
		return attendancePercentage;
	}
	public void setAttendancePercentage(Float attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}
	public Float getStudentRating() {
		return studentRating;
	}
	public void setStudentRating(Float studentRating) {
		this.studentRating = studentRating;
	}
	public Float getTrainerRating() {
		return trainerRating;
	}
	public void setTrainerRating(Float trainerRating) {
		this.trainerRating = trainerRating;
	}
	public Float getPerformance() {
		return performance;
	}
	public void setPerformance(Float performance) {
		this.performance = performance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String  status) {
		this.status = status;
	}
	public ArrayList<EventError> getErrorFlags() {
		return errorFlags;
	}
	public void setErrorFlags(ArrayList<EventError> errorFlags) {
		this.errorFlags = errorFlags;
	}
	public EventsCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
