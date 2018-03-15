/**
 * 
 */
package com.viksitpro.core.customtask;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author ISTAR-SKILL
 *
 */
public class DropDownData {

	String type;
    String 	dataFetchType;
    String itemSource;
    @XmlAttribute(name="type", required=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlAttribute(name="dataFetchType", required=false)
	public String getDataFetchType() {
		return dataFetchType;
	}
	public void setDataFetchType(String dataFetchType) {
		this.dataFetchType = dataFetchType;
	}
	
	@XmlElement(name="itemSource", required=false)
	public String getItemSource() {
		return itemSource;
	}
	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}
	public DropDownData() {
		super();
		
	}
    
    
    
}
