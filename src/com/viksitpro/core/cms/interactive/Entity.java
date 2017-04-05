/**
 *
 */
package com.viksitpro.core.cms.interactive;		

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;


import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 */
@XmlRootElement(name = "entity")
public class Entity implements Serializable {

	Integer id;
	String backgroundImage;
	String grid;
	String correctMessage;
	String incorrectMessage;
	String actionType;
	String text;
	String bgColor;
	HashMap<Integer, EntityOption> options;
	String transitionImage;

	
	public String getBgColor() {
		return bgColor;
	}

	@XmlAttribute(name = "bg_color", required = false)
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	@XmlAttribute(name = "q_text", required = false)
	public void setText(String text) {
		this.text = text;
	}

	public String getActionType() {
		return actionType;
	}

	@XmlAttribute(name = "action", required = false)
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute(name = "id", required = false)
	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	@XmlAttribute(name = "image_url", required = false)
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getGrid() {
		return grid;
	}

	@XmlAttribute(name = "question_grid", required = false)
	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getCorrectMessage() {
		return correctMessage;
	}

	@XmlAttribute(name = "correct_opt_message", required = false)
	public void setCorrectMessage(String correctMessage) {
		this.correctMessage = correctMessage;
	}

	public String getIncorrectMessage() {
		return incorrectMessage;
	}

	@XmlAttribute(name = "incorrect_opt_message", required = false)
	public void setIncorrectMessage(String incorrectMessage) {
		this.incorrectMessage = incorrectMessage;
	}

	public HashMap<Integer, EntityOption> getOptions() {
		return options;
	}

	@XmlElementWrapper(name = "option_entity", required = false)
	public void setOptions(HashMap<Integer, EntityOption> options) {
		this.options = options;
	}

	public String getTransitionImage() {
		return transitionImage;
	}

	@XmlAttribute(name = "transition_image", required = false)
	public void setTransitionImage(String transitionImage) {
		this.transitionImage = transitionImage;
	}
}
