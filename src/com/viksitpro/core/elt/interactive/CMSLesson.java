/**
 * 
 */
package com.viksitpro.core.elt.interactive;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vaibhaverma
 *
 */
@XmlRootElement(name = "lesson")
public class CMSLesson {
	String lessonTitle;
	String lessonDescription = "";
	String type;
	ArrayList<CMSSlide> slides;

	
	public String getLessonTitle() {
		return lessonTitle;
	}

	@XmlAttribute(name = "h1")
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute(name = "lesson_type")
	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<CMSSlide> getSlides() {
		return slides;
	}

	@XmlElement(name = "slide")
	public void setSlides(ArrayList<CMSSlide> slides) {
		this.slides = slides;
	}

	public String getLessonDescription() {
		return lessonDescription;
	}

	@XmlAttribute(name = "description")
	public void setLessonDescription(String lessonDescription) {
		this.lessonDescription = lessonDescription;
	}

}