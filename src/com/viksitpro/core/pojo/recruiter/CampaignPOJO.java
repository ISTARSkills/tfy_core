package com.viksitpro.core.pojo.recruiter;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campaign")
public class CampaignPOJO {
	
	private Integer id;
	private IstarUserPOJO executiveRecruiter;
	private IstarUserPOJO masterRecruiter;
	private JobPOJO job;
	private Integer taskId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String type;
	private CampaignProfilePOJO campaignProfilePOJO;
	
	@XmlAttribute(name = "name", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "executiveRecruiter", required=false)
	public IstarUserPOJO getExecutiveRecruiter() {
		return executiveRecruiter;
	}
	public void setExecutiveRecruiter(IstarUserPOJO executiveRecruiter) {
		this.executiveRecruiter = executiveRecruiter;
	}
	
	@XmlAttribute(name = "masterRecruiter", required=false)
	public IstarUserPOJO getMasterRecruiter() {
		return masterRecruiter;
	}
	public void setMasterRecruiter(IstarUserPOJO masterRecruiter) {
		this.masterRecruiter = masterRecruiter;
	}
	
	@XmlAttribute(name = "job", required=false)
	public JobPOJO getJob() {
		return job;
	}
	public void setJob(JobPOJO job) {
		this.job = job;
	}
	
	@XmlAttribute(name = "taskId", required=false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	@XmlAttribute(name = "createdAt", required=false)
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@XmlAttribute(name = "updatedAt", required=false)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@XmlAttribute(name = "type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute(name = "campaignProfilePOJO", required=false)
	public CampaignProfilePOJO getCampaignProfilePOJO() {
		return campaignProfilePOJO;
	}
	public void setCampaignProfilePOJO(CampaignProfilePOJO campaignProfilePOJO) {
		this.campaignProfilePOJO = campaignProfilePOJO;
	}
	
	
}
