/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.viksitpro.core.utilities.DBUTILS;

/**
 * @author istar
 *
 */
public class TaskFormElement {
	String elemntType;
	String label;
	String elemntName;
	String dataType;
	String dropdownData;

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

	
	public String getDropdownData() {
		return dropdownData;
	}

	public void setDropdownData(String dropdownData) {
		this.dropdownData = dropdownData;
	}

	public static  StringBuffer get(TaskFormElement element,String uniqueId) {
		StringBuffer out = new StringBuffer();
		DBUTILS util = new  DBUTILS();
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
		case "TEXT_BOX":
			out.append("<div class='form-group'><label>"
					+element.getLabel()+ "</label><br/>" + 
					"  <input type='text' name='"+elementName+"' class='' data-unique="+uniqueId+" id='"+uniqueId+"'>\r\n"+ 
					"</div>");
			return out;
		case "STATIC_DROP_DOWN":
			out.append("<div class='form-group'><label> "+element.getLabel()+"</label> <select name='"+elementName+"' data-unique="+uniqueId+" id='"+uniqueId+"'>");
			
			for(String option : element.getDropdownData().split("!#"))
			{
				out.append("<option value='"+option+"'>"+option+"</option>");
			}
			out.append("</select></div>");
			
			return out;
		case "DATE_PICKER":
			out.append("<div class='form-group' id='data_date_picker'><label>"+element.getLabel()+"</label> <div class='input-group date'> <span class='input-group-addon'><i class='fa fa-calendar'></i></span><input name='"+elementName+"' type='text' class='date_holder' value=''> </div> </div>");			
			return out;
		case "DB_DROP_DOWN":
			out.append("<div class='form-group'><label> "+element.getLabel()+"</label> <select name='"+elementName+"' data-unique="+uniqueId+" id='"+uniqueId+"'>");			
			String optionQuery = element.getDropdownData();
			List<HashMap<String, Object>> options = util.executeQuery(optionQuery);
			for(HashMap<String, Object> option : options)
			{
				out.append("<option value='"+option.get("key")+"'>"+option.get("value")+"</option>");
			}
			out.append("</select></div>");	
			return out;
		default:
			break;
		}
		
		
		
		return out;
	}
}
