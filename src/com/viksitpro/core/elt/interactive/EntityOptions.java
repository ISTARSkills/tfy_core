package com.viksitpro.core.elt.interactive;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entity_options")
public class EntityOptions {

	ArrayList<EntityOption> entityOptions=new ArrayList<>();

	public ArrayList<EntityOption> getEntityOptions() {
		return entityOptions;
	}
	@XmlElement(name = "entity_option")
	public void setEntityOptions(ArrayList<EntityOption> entityOptions) {
		this.entityOptions = entityOptions;
	}	
}
