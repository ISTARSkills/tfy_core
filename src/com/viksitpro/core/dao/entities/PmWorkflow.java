/**
 * 
 */
package com.viksitpro.core.dao.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */
@XmlRootElement(name = "workflow")
public class PmWorkflow {

	Integer id;
	String name;
	String description;
	String category;
	List<PMStage> stages = new ArrayList<PMStage>();
	HashMap<String, String[]> attributes = new HashMap<String, String[]>();

	@XmlAttribute(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute(name = "name", required = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "description", required = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlAttribute(name = "category", required = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@XmlElementWrapper(name = "stages", required = false)
	@XmlElement(name = "stage", required = false)
	public List<PMStage> getStages() {
		//stages.sort((left, right) -> left.getId() - right.getId());
		return stages;
	}

	public void setStages(List<PMStage> stages) {
		this.stages = stages;
	}

	@XmlElementWrapper(name = "attributes", required = false)
	@XmlElement(name = "attribute", required = false)
	public HashMap<String, String[]> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String[]> attributes) {
		this.attributes = attributes;
	}

}
