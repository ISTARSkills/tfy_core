/**
 *
 */
package com.viksitpro.core.cms.interactive;		

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementMap;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 */
@XmlRootElement(name = "option_entity")
public class EntityOption implements Serializable {

	Integer id;
	String optionText;
	String backgroundImage;
	String grid;
	boolean isCorrect;
	String backgroundColor;
	Integer nextEntity;
	HashMap<Integer, InfoCard> cards = new HashMap<>();
	String evaluationScript;
	String mediaUrl;
	String mediaType;

	
	public HashMap<Integer, InfoCard> getCards() {
		return cards;
	}

	@XmlElementWrapper(name = "info_cards", required = false)
	public void setCards(HashMap<Integer, InfoCard> cards) {
		this.cards = cards;
	}

	public EntityOption() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	@XmlAttribute(name = "bg_color", required = false)
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getId() {
		return id;
	}

	@XmlAttribute(name = "id", required = false)
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptionText() {
		return optionText;
	}

	@XmlAttribute(name = "option_text", required = false)
	public void setOptionText(String optionText) {
		this.optionText = optionText;
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

	@XmlAttribute(name = "option_grid", required = false)
	public void setGrid(String grid) {
		this.grid = grid;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	@XmlAttribute(name = "iscorrect", required = false)
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Integer getNextEntity() {
		return nextEntity;
	}

	@XmlAttribute(name = "next_entity", required = false)
	public void setNextEntity(Integer nextEntity) {
		this.nextEntity = nextEntity;
	}

	public String getEvaluationScript() {
		return evaluationScript;
	}

	@XmlAttribute(name = "eval_script", required = false)
	public void setEvaluationScript(String evaluationScript) {
		this.evaluationScript = evaluationScript;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	@XmlAttribute(name = "media_url", required = false)
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getMediaType() {
		return mediaType;
	}

	@XmlAttribute(name = "media_type", required = false)
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}
