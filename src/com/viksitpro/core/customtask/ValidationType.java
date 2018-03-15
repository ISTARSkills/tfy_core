/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author mayank
 *
 */
public class ValidationType {

	String type;
	String warning;
	ArrayList<ValidationParam> params;
	
	@XmlAttribute(name="type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name="params", required=false)
	public ArrayList<ValidationParam> getParams() {
		return params;
	}
	public void setParams(ArrayList<ValidationParam> params) {
		this.params = params;
	}
	
	public ValidationType() {
		super();
		
	}
	
	@XmlAttribute(name="warning", required=false)
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	
	
	
	
}
