/**
 * 
 */
package com.viksitpro.core.customtask;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author mayank
 *
 */
public class ValidationParam {

	String name;
	String value;
	
	@XmlAttribute(name="name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name="value", required=false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ValidationParam() {
		super();
		
	}
	
	
	
}
