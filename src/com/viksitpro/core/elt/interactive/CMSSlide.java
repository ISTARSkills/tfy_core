/**
 * 
 */
package com.viksitpro.core.elt.interactive;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.viksitpro.core.dao.entities.IstarUserDAO;
import com.viksitpro.core.dao.entities.Question;
import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author Ajith
 *
 *///
@XmlRootElement(name = "slide")
public class CMSSlide {
	int Id;
	int order_id;
	int coins;
	int points;
	String template;
	String image_BG;
	String audio_BG;
	String audio_type;
	String action;
	String marking_scheme;
	String sub_type;
	
	EntityOptions entityOptions;
	public CMSSlide() {
		super();
	}

	public int getId() {
		return Id;
	}

	@XmlElement(name = "id")
	public void setId(int id) {
		Id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	@XmlElement(name = "order_id")
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCoins() {
		return coins;
	}

	@XmlElement(name = "coins", required = false)
	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getPoints() {
		return points;
	}

	@XmlElement(name = "points", required = false)
	public void setPoints(int points) {
		this.points = points;
	}

	public String getImage_BG() {
		return image_BG;
	}

	@XmlAttribute(name = "image_BG", required = false)
	public void setImage_BG(String image_BG) {
		this.image_BG = image_BG;
	}

	public String getAction() {
		return action;
	}

	@XmlElement(name = "action", required = false)
	public void setAction(String action) {
		this.action = action;
	}

	public String getMarking_scheme() {
		return marking_scheme;
	}

	@XmlElement(name = "marking_scheme", required = false)
	public void setMarking_scheme(String marking_scheme) {
		this.marking_scheme = marking_scheme;
	}

	public EntityOptions getEntityOptions() {
		return entityOptions;
	}
	@XmlElement(name = "entityOptions", required = false)
	public void setEntityOptions(EntityOptions entityOptions) {
		this.entityOptions = entityOptions;
	}

	public String getTemplate() {
		return template;
	}
	@XmlAttribute(name = "template", required = false)
	public void setTemplate(String template) {
		this.template = template;
	}

	public String getAudio_BG() {
		return audio_BG;
	}

	@XmlAttribute(name = "audio_BG", required = false)
	public void setAudio_BG(String audio_BG) {
		this.audio_BG = audio_BG;
	}

	public String getSub_type() {
		return sub_type;
	}
	
	@XmlAttribute(name = "sub_type", required = false)
	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public String getAudio_type() {
		return audio_type;
	}
	@XmlAttribute(name = "audio_type", required = false)
	public void setAudio_type(String audio_type) {
		this.audio_type = audio_type;
	}
	
	
	
	

}