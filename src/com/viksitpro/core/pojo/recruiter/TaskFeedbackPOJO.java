package com.viksitpro.core.pojo.recruiter;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "taskFeedback")
public class TaskFeedbackPOJO {
	
	private Integer id;
	private Integer taskId;
	private String feedback;
	private Integer istarUserId;
	private String name;
	private String status;
	private String stage;
	private Integer rating;
	private Timestamp updatedAt;
	
	@XmlAttribute(name = "id", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "taskId", required=false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	@XmlAttribute(name = "feedback", required=false)
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	@XmlAttribute(name = "istarUserId", required=false)
	public Integer getIstarUserId() {
		return istarUserId;
	}
	public void setIstarUserId(Integer istarUserId) {
		this.istarUserId = istarUserId;
	}
	
	@XmlAttribute(name = "name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name = "status", required=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlAttribute(name = "stage", required=false)
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@XmlAttribute(name = "updatedAt", required=false)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@XmlAttribute(name = "rating", required=false)
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
}

