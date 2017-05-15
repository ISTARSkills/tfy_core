package com.viksitpro.core.pojo.recruiter;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campaignProfile")
public class IstarNotificationPOJO {

	private Integer id;
	private String message;
	private Timestamp time;
	
	public IstarNotificationPOJO(){
		
	}

	@XmlAttribute(name = "id", required=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute(name = "message", required=false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlAttribute(name = "time", required=false)
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}	
}
