/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.UUID;

/**
 * @author istar
 *
 */
public class TaskFormElement {
	String elemntType;
	String label;
	String elemntName;

	String dataType;

	public TaskFormElement() {
		super();
	}

	public TaskFormElement(String elemntType, String elemntName, String dataType) {
		super();
		this.elemntType = elemntType;
		this.elemntName = elemntName;
		this.dataType = dataType;
	}

	public TaskFormElement(String elemntType, String label, String elemntName, String dataType) {
		super();
		this.elemntType = elemntType;
		this.label = label;
		this.elemntName = elemntName;
		this.dataType = dataType;
	}

	public String getElemntType() {
		return elemntType;
	}

	public void setElemntType(String elemntType) {
		this.elemntType = elemntType;
	}

	public String getElemntName() {
		return elemntName;
	}

	public void setElemntName(String elemntName) {
		this.elemntName = elemntName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static  StringBuffer get(TaskFormElement element,String uniqueId) {
		StringBuffer out = new StringBuffer();
		
		String elementName=element.getElemntName().replaceAll(" ","_").replace("?", "").toLowerCase();
		switch (element.getElemntType()) {
		case "STAR_RATING":
			out.append("<div class='form-group'>\r\n" + 
					"<label>"+element.getLabel()+ " </label> \r\n" + 
					"<div class='combostar' data-name='"+elementName+"'>" + 
					"</div></div>");
			return out;
			
		case "SWITCH":
			out.append("<div class='form-group'><label>"
					+element.getLabel()+ "</label><br/>" + 
					"  <input type='checkbox' name='"+elementName+"' class='js-switch' data-unique="+uniqueId+" id='"+uniqueId+"'>\r\n"+ 
					"</div>");
			return out;
		case "TEXT":
			out.append("<div class='form-group'><label>"
					+element.getLabel()+ "</label><br/>" + 
					"  <textarea style='width:100%;' rows='3' name='"+elementName+"'  data-unique="+uniqueId+" id='"+uniqueId+"'></textarea>"+ 
					"</div>");
			return out;
		default:
			break;
		}
		
		
		
		return out;
	}
}
