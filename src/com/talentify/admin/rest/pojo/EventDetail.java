package com.talentify.admin.rest.pojo;

import java.util.ArrayList;


public class EventDetail {

	Integer eventId;
	String time;
	Float duration;
	String imageUrl;
	String status;
	String sessionName;
	String courseName;
	String groupName;
	String roleName;
	EventTrainer trainer;
	Integer classRoomId;
	String classRoomName;
	EventTrainer associateTrainer;
	EventAttendance eventAttendance;
	EventFeedback trainerFeedback;
	ArrayList<EventFeedback> studentFeedback;
	ArrayList<String> learningObjCovered;
	
	
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public EventTrainer getTrainer() {
		return trainer;
	}
	public void setTrainer(EventTrainer trainer) {
		this.trainer = trainer;
	}
	public EventTrainer getAssociateTrainer() {
		return associateTrainer;
	}
	public void setAssociateTrainer(EventTrainer associateTrainer) {
		this.associateTrainer = associateTrainer;
	}
	public EventAttendance getEventAttendance() {
		return eventAttendance;
	}
	public void setEventAttendance(EventAttendance eventAttendance) {
		this.eventAttendance = eventAttendance;
	}
	public EventFeedback getTrainerFeedback() {
		return trainerFeedback;
	}
	public void setTrainerFeedback(EventFeedback trainerFeedback) {
		this.trainerFeedback = trainerFeedback;
	}
	public ArrayList<EventFeedback> getStudentFeedback() {
		return studentFeedback;
	}
	public void setStudentFeedback(ArrayList<EventFeedback> studentFeedback) {
		this.studentFeedback = studentFeedback;
	}
	public ArrayList<String> getLearningObjCovered() {
		return learningObjCovered;
	}
	public void setLearningObjCovered(ArrayList<String> learningObjCovered) {
		this.learningObjCovered = learningObjCovered;
	}
	public EventDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}
	public String getClassRoomName() {
		return classRoomName;
	}
	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
	
	
	
	
	
	
	
	
	
	
}
