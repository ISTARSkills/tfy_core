/**
 * 
 */
package com.viksitpro.core.customtask;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author ISTAR-SKILL
 *
 */
public class DropDownItem {

	String sql;
	String name;
	Integer id ;
	String key;
	String value;
	
	@XmlElement(name="sql", required = false)
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@XmlAttribute(name="name", required = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name="id", required = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name="key", required = false)
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@XmlAttribute(name="value", required = false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public DropDownItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
