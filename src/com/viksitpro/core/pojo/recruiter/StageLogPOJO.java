package com.viksitpro.core.pojo.recruiter;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "stageLog")
public class StageLogPOJO {

	private Integer id;
	private Integer taskId;
	private Integer istarUserId;
	private String stageName;
	private String stageType;
	private String status;
	private String result;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@XmlAttribute (name="id" ,required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute (name="taskId" ,required=false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	@XmlAttribute (name="istarUserId" ,required=false)
	public Integer getIstarUserId() {
		return istarUserId;
	}
	public void setIstarUserId(Integer istarUserId) {
		this.istarUserId = istarUserId;
	}
	
	@XmlAttribute (name="stageName" ,required=false)
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
	@XmlAttribute (name="stageType" ,required=false)
	public String getStageType() {
		return stageType;
	}
	public void setStageType(String stageType) {
		this.stageType = stageType;
	}
	
	@XmlAttribute (name="status" ,required=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlAttribute (name="result" ,required=false)
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@XmlAttribute (name="createdAt" ,required=false)
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@XmlAttribute (name="updatedAt" ,required=false)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
