package com.vub.model;

import java.util.Date;

/**
 * Data object that represents an entry in someone's calender.
 * 
 * @author pieter
 *
 */
public class Entry {
	Date startDate;
	Date endDate;
	Course course;
	Room room;
	
	public Entry() {
		
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
