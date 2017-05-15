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
	private Double skillRating;
	private Double totalRating = 0.0;
	private Integer percentage = 0;
	
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
	public Double getSkillRating() {
		return skillRating;
	}
	public void setSkillRating(Double skillRating) {
		this.skillRating = skillRating;
	}
	
	@XmlAttribute(name = "totalRating", required = false)
	public Double getTotalRating() {
		return totalRating;
	}
	public void setTotalRating(Double totalRating) {
		this.totalRating = totalRating;
	}
	
	@XmlAttribute(name = "percentage", required = false)
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	
	public void calculatePercentage(){
		if(this.totalRating!=0){
		this.percentage = ((Double) ((this.skillRating/this.totalRating)*100)).intValue();
		}else{
			this.percentage = 0;
		}
	}	
}
