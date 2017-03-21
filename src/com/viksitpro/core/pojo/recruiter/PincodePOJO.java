package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pincode")
public class PincodePOJO {

	private Integer id;
	private String city;
	private String country;
	private Integer pin;
	private String state;
	private Double lattiude;
	private Double longitude;
	private String stateCode;
	
	@XmlAttribute(name = "id", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "city", required=false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlAttribute(name = "country", required=false)
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlAttribute(name = "pin", required=false)
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
	@XmlAttribute(name = "state", required=false)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@XmlAttribute(name = "lattiude", required=false)
	public Double getLattiude() {
		return lattiude;
	}
	public void setLattiude(Double lattiude) {
		this.lattiude = lattiude;
	}
	
	@XmlAttribute(name = "longitude", required=false)
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@XmlAttribute(name = "stateCode", required=false)
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	
	
	
	
}
