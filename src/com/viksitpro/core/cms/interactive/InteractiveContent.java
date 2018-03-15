/**
 * 
 */
package com.viksitpro.core.cms.interactive;		

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * @author ComplexObject
 *
 */
@Root(name="interactive_content")
public class InteractiveContent implements Serializable {

	@Attribute(name = "id", required = false)
    Integer id;
	@Attribute(name = "bgImage", required = false)
    String bgImage;
	@Attribute(name = "evalType", required = false)
    Boolean evalType;
	@Attribute(name = "scrollable", required = false)
    Boolean scrollable;
	@Attribute(name = "gridX", required = false)
    Integer gridX;
	@Attribute(name = "gridY", required = false)
    Integer gridY;
	
	@ElementList(name = "entity", inline = true, required = false)
	List<Entity> questions ;

	@ElementList(name = "variables", inline = true, required = false)
  List<Variable> variables;


	@Attribute(name = "audioUrl", required = false)
    String audioUrl;

	@Attribute(name = "zipFileURL", required = false)
	String zipFileURL;
	
	public InteractiveContent() {
		super();
		
	}

	public List<Entity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Entity> questions) {
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public Boolean getEvalType() {
		return evalType;
	}

	public void setEvalType(Boolean evalType) {
		this.evalType = evalType;
	}

	public Boolean getScrollable() {
		return scrollable;
	}

	public void setScrollable(Boolean scrollable) {
		this.scrollable = scrollable;
	}

	public Integer getGridX() {
		return gridX;
	}

	public void setGridX(Integer gridX) {
		this.gridX = gridX;
	}

	public Integer getGridY() {
		return gridY;
	}

	public void setGridY(Integer gridY) {
		this.gridY = gridY;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getZipFileURL() {
		return zipFileURL;
	}

	public void setZipFileURL(String zipFileURL) {
		this.zipFileURL = zipFileURL;
	}
	
	
}
