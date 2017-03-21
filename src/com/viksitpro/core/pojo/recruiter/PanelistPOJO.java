package com.viksitpro.core.pojo.recruiter;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "panelist")
public class PanelistPOJO {

	private StudentPOJO student;
	private List<TaskFeedbackPOJO> taskFeedbacks;
	private String hostUrl;
	private Integer istarUserId;
	private String urlCode;
	
	@XmlAttribute(name = "student", required=false)
	public StudentPOJO getStudent() {
		return student;
	}
	public void setStudent(StudentPOJO student) {
		this.student = student;
	}
	
	@XmlElementWrapper(name = "taskFeedbacks", required = false)
	@XmlAttribute(name = "taskFeedback", required=false)
	public List<TaskFeedbackPOJO> getTaskFeedbacks() {
		return taskFeedbacks;
	}
	public void setTaskFeedbacks(List<TaskFeedbackPOJO> taskFeedbacks) {
		this.taskFeedbacks = taskFeedbacks;
	}
	
	@XmlAttribute(name = "hostUrl", required=false)
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	
	@XmlAttribute(name = "istarUserId", required=false)
	public Integer getIstarUserId() {
		return istarUserId;
	}
	public void setIstarUserId(Integer istarUserId) {
		this.istarUserId = istarUserId;
	}
	
	@XmlAttribute(name = "urlCode", required=false)
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
}
