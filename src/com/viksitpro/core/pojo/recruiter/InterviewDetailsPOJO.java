package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "interviewDetails")
public class InterviewDetailsPOJO {

	private TeamPOJO panelistTeam;
	private String status;
	private String dateTime;
		
	@XmlAttribute(name = "panelistTeam", required=false)
	public TeamPOJO getPanelistTeam() {
		return panelistTeam;
	}
	public void setPanelistTeam(TeamPOJO panelistTeam) {
		this.panelistTeam = panelistTeam;
	}
	
	@XmlAttribute(name = "status", required=false)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlAttribute(name = "dateTime", required=false)
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
