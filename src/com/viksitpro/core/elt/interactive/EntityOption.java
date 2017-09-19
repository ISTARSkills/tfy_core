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
@XmlRootElement(name = "entity_option")
public class EntityOption {
	int Id;
	Integer correct_order_id;
	Integer next_slide;
	String image_BG;
	String bg_audio;
	String option_text;
	String type;
	String correct_option;
		
	public EntityOption() {
		super();
	}
	
	public int getId() {
		return Id;
	}
	
	@XmlAttribute(name = "id")
	public void setId(int id) {
		Id = id;
	}
		
	public String getImage_BG() {
		return image_BG;
	}
	@XmlAttribute(name = "image_BG",required=false)
	public void setImage_BG(String image_BG) {
		this.image_BG = image_BG;
	}

	public Integer getCorrect_order_id() {
		return correct_order_id;
	}
	@XmlAttribute(name = "correct_order_id",required=false)
	public void setCorrect_order_id(Integer correct_order_id) {
		this.correct_order_id = correct_order_id;
	}

	public String getBg_audio() {
		return bg_audio;
	}
	@XmlAttribute(name = "bg_audio",required=false)
	public void setBg_audio(String bg_audio) {
		this.bg_audio = bg_audio;
	}

	public String getOption_text() {
		return option_text;
	}
	@XmlAttribute(name = "option_text",required=false)
	public void setOption_text(String option_text) {
		this.option_text = option_text;
	}

	public String getType() {
		return type;
	}
	@XmlAttribute(name = "type",required=false)
	public void setType(String type) {
		this.type = type;
	}

	public Integer getNext_slide() {
		return next_slide;
	}
	@XmlAttribute(name = "next_slide",required=false)
	public void setNext_slide(Integer next_slide) {
		this.next_slide = next_slide;
	}

	public String getCorrect_option() {
		return correct_option;
	}

	@XmlAttribute(name = "correct_option",required=false)
	public void setCorrect_option(String correct_option) {
		this.correct_option = correct_option;
	}
	
	
	
	
	
	
	
	

}