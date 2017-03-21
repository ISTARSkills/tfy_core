package com.viksitpro.core.pojo.recruiter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "skill")
public class SkillPOJO {

	private Integer id;
	private Integer parentSkillId;
	private String skillTitle;
	private String skillLevel;
	private Integer orderId;
	private String levelType;
	private Integer skillRating;
	
	@XmlAttribute(name = "id", required = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlAttribute(name = "parentSkillId", required = false)
	public Integer getParentSkillId() {
		return parentSkillId;
	}
	public void setParentSkillId(Integer parentSkillId) {
		this.parentSkillId = parentSkillId;
	}
	
	@XmlAttribute(name = "skillTitle", required = false)
	public String getSkillTitle() {
		return skillTitle;
	}
	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}
	
	@XmlAttribute(name = "skillLevel", required = false)
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	@XmlAttribute(name = "orderId", required = false)
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@XmlAttribute(name = "levelType", required = false)
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	
	@XmlAttribute(name = "skillRating", required = false)
	public Integer getSkillRating() {
		return skillRating;
	}
	public void setSkillRating(Integer skillRating) {
		this.skillRating = skillRating;
	}
	
	
}
