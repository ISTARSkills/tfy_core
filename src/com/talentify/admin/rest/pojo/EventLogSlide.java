/**
 * 
 */
package com.talentify.admin.rest.pojo;

/**
 * @author mayank
 *
 */
public class EventLogSlide {

	String time;
	Integer slideId;
	String totalTime;
	String slideTitle;
	String lessonTitle;
	String sesssionTitle;
	
	public EventLogSlide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getSlideId() {
		return slideId;
	}
	public void setSlideId(Integer slideId) {
		this.slideId = slideId;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getSlideTitle() {
		return slideTitle;
	}
	public void setSlideTitle(String slideTitle) {
		this.slideTitle = slideTitle;
	}
	public String getLessonTitle() {
		return lessonTitle;
	}
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}
	public String getSesssionTitle() {
		return sesssionTitle;
	}
	public void setSesssionTitle(String sesssionTitle) {
		this.sesssionTitle = sesssionTitle;
	}
	
	
}
