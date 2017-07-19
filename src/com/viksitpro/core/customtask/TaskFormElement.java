/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringEscapeUtils;

import com.viksitpro.core.utilities.DBUTILS;
import com.viksitpro.core.utilities.FormElementDropdownType;

/**
 * @author istar
 *
 */
public class TaskFormElement {
	String elemntType;
	String label;
	String elemntName;
	String dataType;
	DropDownData dropdownData;
	Integer id;
	Integer dependency;
	
	@XmlAttribute(name="dependency", required=false)
	public Integer getDependency() {
		return dependency;
	}

	public void setDependency(Integer dependency) {
		this.dependency = dependency;
	}

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

	
	
	@XmlElement(name="elemntType", required=false)
	public String getElemntType() {
		return elemntType;
	}

	public void setElemntType(String elemntType) {
		this.elemntType = elemntType;
	}

	@XmlElement(name="elemntName", required=false)
	public String getElemntName() {
		return elemntName;
	}

	public void setElemntName(String elemntName) {
		this.elemntName = elemntName;
	}
	
	@XmlElement(name="dataType", required=false)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@XmlElement(name="label", required=false)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
	@XmlAttribute(name="id", required=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement(name="dropdownData", required=false)
	public DropDownData getDropdownData() {
		return dropdownData;
	}

	public void setDropdownData(DropDownData dropdownData) {
		this.dropdownData = dropdownData;
	}

	public static  StringBuffer get(TaskFormElement element,Integer templateId, Integer stepId) {
		StringBuffer out = new StringBuffer();
		DBUTILS util = new  DBUTILS();
		String elementName=element.getElemntName().replaceAll(" ","_").replace("?", "").toLowerCase();
		String uniqueId = "form_element_"+templateId+"_"+stepId+"_"+element.getId();
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
					"  <input type='checkbox' name='"+elementName+"' class='js-switch' data-unique="+uniqueId+" id='"+uniqueId+"' tabindex='"+(element.getId())+"'>\r\n"+ 
					"</div>");
			return out;
		case "TEXT_AREA":
			out.append("<div class='form-group'><label>"
					+element.getLabel()+ "</label><br/>" + 
					"  <textarea style='width:100%;' rows='3' name='"+elementName+"'  data-unique="+uniqueId+" id='"+uniqueId+"' tabindex='"+(element.getId())+"'></textarea>"+ 
					"</div>");
			return out;
		case "TEXT_BOX":
			out.append("<div class='form-group'><label>"
					+element.getLabel()+ "</label><br/>" + 
					"  <input type='text' name='"+elementName+"' class='form-control' data-unique="+uniqueId+" id='"+uniqueId+"' tabindex='"+(element.getId())+"'>\r\n"+ 
					"</div>");
			return out;
		case "DATE_PICKER":
			out.append("<div class='form-group data_date_picker '><label>"+element.getLabel()+"</label> <div class='input-group date'> <span class='input-group-addon'><i class='fa fa-calendar'></i></span><input name='"+elementName+"' type='text' class='date_holder form-control' value='' tabindex='"+(element.getId())+"'> </div> </div>");			
			return out;
		case "DROP_DOWN":
			
			if(element.getDropdownData().getType().equalsIgnoreCase(FormElementDropdownType.static_list))
			{
				out.append("<div class='form-group'><label> "+element.getLabel()+"</label>"
						+ " <select name='"+elementName+"' data-unique="+uniqueId+" id='"+uniqueId+"' data-placeholder='Select "+element.getLabel()+"' "
								+ "data-tabindex='"+(element.getId())+"' tabindex='"+(element.getId())+"'> ");
				for(String option : element.getDropdownData().getItemSource().split("!#"))
				{
					out.append("<option value='"+option+"'>"+option+"</option>");
				}				
				out.append("</select></div>");
			}
			else if(element.getDropdownData().getType().equalsIgnoreCase(FormElementDropdownType.ajaxified_list))
			{
				if(element.getDependency()!=null)
				{
					out.append("<div class='form-group'><label> "+element.getLabel()+"</label> <select class='ajaxified_list' name='"+elementName+"' "
							+ " data-unique="+uniqueId+" id='"+uniqueId+"' data-dependency='"+"form_element_"+templateId+"_"+stepId+"_"+element.getDependency()+"' "
									+ " data-sql='"+element.getDropdownData().getItemSource().replaceAll("'", "\"")+"' data-placeholder='Select "+element.getLabel()+"' "
											+ "data-tabindex='"+(element.getId())+"' tabindex='"+(element.getId())+"'> ");	
					
					out.append("</select></div>");
				}
				else
				{
					out.append("<div class='form-group'><label> "+element.getLabel()+"</label> <select class='ajaxified_list' name='"+elementName+"' data-unique="+uniqueId+" "
							+ " id='"+uniqueId+"' data-sql='"+element.getDropdownData().getItemSource().replaceAll("'", "\"")+"' data-placeholder='Select "+element.getLabel()+"' "
									+ "data-tabindex='"+(element.getId())+"' tabindex='"+(element.getId())+"'> ");				
					out.append("</select></div>");
				}					
			}				
			return out;
		
			
		default:
			break;
		}
		
		
		
		return out;
	}
}
