/**
 * 
 */
package com.viksitpro.core.cms.interactive;		

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */

@XmlRootElement(name = "info_card")
public class InfoCard implements Serializable {

	Integer id;
	String template;
	CardContent content;

	public Integer getId() {
		return id;
	}

	@XmlAttribute(name = "id", required = false)
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	@XmlAttribute(name = "template", required = false)
	public void setTemplate(String template) {
		this.template = template;
	}

	public CardContent getContent() {
		return content;
	}

	@XmlElement(name = "content")
	public void setContent(CardContent content) {
		this.content = content;
	}

	public InfoCard() {
		super();
		// TODO Auto-generated constructor stub
	}

}
