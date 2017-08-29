/**
 * 
 */
package com.talentify.admin.rest.pojo;

/**
 * @author ajith
 *
 */
public class EventFeedback {

	Integer userId;
	Float rating;
	String comment;
	String userName;
	public EventFeedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
		
	
}
