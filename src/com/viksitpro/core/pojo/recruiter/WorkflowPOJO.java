package com.viksitpro.core.pojo.recruiter;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


public class WorkflowPOJO {

	Integer id;
	String name;
	String description;
	List<StagePOJO> stages;
	HashMap<String, String[]> attributes;
	
	@XmlAttribute (name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute (name="name", required=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute (name="description", required=false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElementWrapper(name="stages" , required=false)
	@XmlElement (name="stage", required=false)
	public List<StagePOJO> getStages() {
		//stages.sort((left, right) -> left.getId() - right.getId());
		return stages;
	}
	public void setStages(List<StagePOJO> stages) {
		this.stages = stages;
	}
	
	@XmlElementWrapper(name="attributes" , required=false)
	@XmlElement (name="attribute", required=false)
	public HashMap<String, String[]> getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap<String, String[]> attributes) {
		this.attributes = attributes;
	}
	
}
