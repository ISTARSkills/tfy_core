/**
 * 
 */
package com.viksitpro.core.cms.interactive;		

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */
@XmlRootElement(name = "interactive_content")
public class InteractiveContent implements Serializable {

	Integer id;
	String bgImage;
	Boolean evalType;
	Boolean scrollable;
	Integer gridX;
	Integer gridY;
	List<Entity> questions;
	List<Variable> variables;
	String audioUrl;

	public InteractiveContent() {
		super();
	}

	public List<Entity> getQuestions() {
		return questions;
	}

	@XmlElement(name = "entity", required = false)
	public void setQuestions(List<Entity> questions) {
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute(name = "id", required = false)
	public void setId(Integer id) {
		this.id = id;
	}

	public String getBgImage() {
		return bgImage;
	}

	@XmlAttribute(name = "bgImage", required = false)
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public Boolean getEvalType() {
		return evalType;
	}

	@XmlAttribute(name = "evalType", required = false)
	public void setEvalType(Boolean evalType) {
		this.evalType = evalType;
	}

	public Boolean getScrollable() {
		return scrollable;
	}

	@XmlAttribute(name = "scrollable", required = false)
	public void setScrollable(Boolean scrollable) {
		this.scrollable = scrollable;
	}

	public Integer getGridX() {
		return gridX;
	}

	@XmlAttribute(name = "gridX", required = false)
	public void setGridX(Integer gridX) {
		this.gridX = gridX;
	}

	public Integer getGridY() {
		return gridY;
	}

	@XmlAttribute(name = "gridY", required = false)
	public void setGridY(Integer gridY) {
		this.gridY = gridY;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	@XmlElement(name = "variables", required = false)
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	@XmlAttribute(name = "audioUrl", required = false)
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
}
