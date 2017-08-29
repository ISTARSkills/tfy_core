/**
 * 
 */
package com.talentify.admin.rest.pojo;

import java.util.ArrayList;

/**
 * @author ajith
 *
 */
public class EventTrainer {

	String type;
	String trainerName;
	Integer trainerId;
	ArrayList<String> skills;
	Float averageFeedback;
	Float totalHoursTaught;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public Integer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
	public ArrayList<String> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	public Float getAverageFeedback() {
		return averageFeedback;
	}
	public void setAverageFeedback(Float averageFeedback) {
		this.averageFeedback = averageFeedback;
	}
	public Float getTotalHoursTaught() {
		return totalHoursTaught;
	}
	public void setTotalHoursTaught(Float totalHoursTaught) {
		this.totalHoursTaught = totalHoursTaught;
	}
	public EventTrainer() {
		super();
	}
	
	
	
}
