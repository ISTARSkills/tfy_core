/**
 * 
 */
package com.viksitpro.core.customtask;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ISTAR-SKILL
 *
 */
@XmlRootElement (name="dropdown_list")
public class DropDownList {

	ArrayList<DropDownItem> dropdowns;

	@XmlElement(name="dropdown_item", required=false)
	public ArrayList<DropDownItem> getDropdowns() {
		return dropdowns;
	}

	public void setDropdowns(ArrayList<DropDownItem> dropdowns) {
		this.dropdowns = dropdowns;
	}

	public DropDownList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
