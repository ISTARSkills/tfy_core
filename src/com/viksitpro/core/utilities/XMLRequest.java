/**
 * 
 */
package com.viksitpro.core.utilities;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ComplexObject
 *
 */
@XmlRootElement(name="xml_request")
public class XMLRequest {

	Integer taskId;
	String prevStage;
	String nextStage;
	HashMap<String, String[]> params;
	
	@XmlAttribute (name="taskId", required=false)
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	@XmlElementWrapper(name="params" , required=false)
	public HashMap<String, String[]> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String[]> params) {
		this.params = params;
	}
	
	@XmlAttribute (name="prev_stage", required=false)
	public String getPrevStage() {
		return prevStage;
	}
	
	public void setPrevStage(String prevStage) {
		this.prevStage = prevStage;
	}
	
	@XmlAttribute (name="next_stage", required=false)
	public String getNextStage() {
		return nextStage;
	}
	public void setNextStage(String nextStage) {
		this.nextStage = nextStage;
	}
		
	
	
}
