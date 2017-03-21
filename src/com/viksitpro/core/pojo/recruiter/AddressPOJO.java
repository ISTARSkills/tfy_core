package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class AddressPOJO {

	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private Integer pincode;
	private String city;
	private String state;
	
	@XmlAttribute(name = "id", required=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "addressLine1", required=false)
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	@XmlAttribute(name = "addressLine2", required=false)
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	@XmlAttribute(name = "pincode", required=false)
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	@XmlAttribute(name = "city", required=false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlAttribute(name = "state", required=false)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
