/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author istar
 *
 */
public class TaskStep {
	String label;
	List<TaskFormElement> form_elements;
	String updateQuery;
	String fetchQuery;
	
	public TaskStep() {
		super();
	}
	
	@XmlAttribute
	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	@XmlElement
	public List<TaskFormElement> getForm_elements() {
		return form_elements;
	}
	public void setForm_elements(List<TaskFormElement> form_elements) {
		this.form_elements = form_elements;
	}
	
	
	@XmlElement
	public String getUpdateQuery() {
		return updateQuery;
	}
	public void setUpdateQuery(String updateQuery) {
		this.updateQuery = updateQuery;
	}
	
	@XmlElement
	public String getFetchQuery() {
		return fetchQuery;
	}
	public void setFetchQuery(String fetchQuery) {
		this.fetchQuery = fetchQuery;
	}
	
	
}
