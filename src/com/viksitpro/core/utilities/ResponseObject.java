/**
 * 
 */
package com.viksitpro.core.utilities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */
@XmlRootElement(name="response")
public class ResponseObject {

	String statusCode;
	String message;
	Object result;
	
	public ResponseObject() {
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

	@XmlElement(name="result")
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
