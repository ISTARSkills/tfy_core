/**
 * 
 */
package com.viksitpro.core.dao.entities;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author ComplexObject
 *
 */
public class PmStageAction {

	String actionDetail;	
	String actionUrl;
	String type;
	
	public PmStageAction() {
		super();		
	}
		
	@XmlAttribute (name="action_detail", required=false)
	public String getActionDetail() {
		return actionDetail;
	}
	public void setActionDetail(String actionName) {
		this.actionDetail = actionName;
	}
	
	@XmlAttribute (name="action_url", required=false)
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	@XmlAttribute (name="type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}		
}
