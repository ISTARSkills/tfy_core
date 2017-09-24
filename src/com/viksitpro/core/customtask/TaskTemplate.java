/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author istar
 *
 */
@XmlRootElement
public class TaskTemplate {
	String label;
	int id;
	String taskName;
	List<TaskStep> steps = new ArrayList<>();
	String description;
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@XmlElement
	public List<TaskStep> getSteps() {
		return steps;
	}

	public void setSteps(List<TaskStep> steps) {
		this.steps = steps;
	}

	public TaskTemplate() {
		super();
	}

	
	
	@XmlElement
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public TaskTemplate(int id, String taskName) {
		super();
		this.id = id;
		this.taskName = taskName;
	}

	public StringBuffer taskHTML() {
		return null;
		
	}
}
