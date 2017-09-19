package com.viksitpro.core.cms.oldcontent;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "outcome")
public class Outcome {
	int points;
	int coins;
	int jumpToSlideID;
	String expectedResult;
	
	@XmlAttribute(name="points",required=false)
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	@XmlAttribute(name="coins", required=false)
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	@XmlAttribute(name="jump_slide_id", required=false)
	public int getJumpToSlideID() {
		return jumpToSlideID;
	}
	public void setJumpToSlideID(int jumpToSlideID) {
		this.jumpToSlideID = jumpToSlideID;
	}
	
	@XmlAttribute(name="expected_result", required=false)
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	public Outcome() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

