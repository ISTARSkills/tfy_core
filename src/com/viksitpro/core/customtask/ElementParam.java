/**
 * 
 */
package com.viksitpro.core.customtask;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author mayank
 *
 */
public class ElementParam {

	private String name;
	private String value;
	@XmlAttribute(name="name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute(name="value", required=false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ElementParam() {
		super();
		
	}
	
	
}
