/**
 * 
 */
package com.viksitpro.core.services;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */
@XmlRootElement(name="result")
public class PmResponseObject {

	String statusCode;
	String message;
	
	public PmResponseObject() {
		super();
		
	}
	
	@XmlAttribute(name="statusCode")
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	@XmlAttribute(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
}
