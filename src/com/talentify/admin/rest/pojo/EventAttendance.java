/**
 * 
 */
package com.talentify.admin.rest.pojo;

import java.util.ArrayList;

/**
 * @author ajith
 *
 */
public class EventAttendance {

	ArrayList<AttendanceStudent>  presentStudents;
	ArrayList<AttendanceStudent>  absentStudents;
	public EventAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<AttendanceStudent> getPresentStudents() {
		return presentStudents;
	}
	public void setPresentStudents(ArrayList<AttendanceStudent> presentStudents) {
		this.presentStudents = presentStudents;
	}
	public ArrayList<AttendanceStudent> getAbsentStudents() {
		return absentStudents;
	}
	public void setAbsentStudents(ArrayList<AttendanceStudent> absentStudents) {
		this.absentStudents = absentStudents;
	}
	
	
}
