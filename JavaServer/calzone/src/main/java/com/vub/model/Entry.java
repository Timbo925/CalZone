package com.vub.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.vub.scheduler.DateStrengthComparator;
import com.vub.scheduler.EntryDifficultyComparator;
import com.vub.scheduler.RoomStrengthComparator;

/**
 * Data object that represents an entry in someone's calender.
 * 
 * @author pieter
 * 
 */
@Entity
@Table(name="ENTRY")
@PlanningEntity(difficultyComparatorClass = EntryDifficultyComparator.class)
public class Entry implements Comparable<Entry> {
	@Id
	@GeneratedValue
	@Column(name="ProgramID")
	int id;
	
	@Column(name="StartingDate")
	Date startingDate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "RoomID")
	Room room;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "CourseComponentID")
	CourseComponent courseComponent;
	
	@Column(name="indexInCourseComponent")
	int indexInCourseComponent;
	
	@Column(name="Frozen")
	boolean frozen;

	@PlanningVariable(valueRangeProviderRefs = { "startDateRange" }, strengthComparatorClass = DateStrengthComparator.class)
	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startDate) {
		this.startingDate = startDate;
	}

	@PlanningVariable(valueRangeProviderRefs = { "roomRange" }, strengthComparatorClass=RoomStrengthComparator.class)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public CourseComponent getCourseComponent() {
		return courseComponent;
	}

	public void setCourseComponent(CourseComponent courseComponent) {
		this.courseComponent = courseComponent;
	}

	/**
	 * A course components exists most of the times of multiple lectures. This
	 * number gives the index number of the lecture in all the given lectures.
	 * 
	 * @return the index in the coursecomponent.
	 * 
	 * @author Pieter Meiresone
	 */
	public int getIndexInCourseComponent() {
		return indexInCourseComponent;
	}

	/**
	 * @see {@link #getIndexInCourseComponent()}
	 * 
	 * @param indexInCourseComponent
	 *            the index in the coursecomponent
	 * 
	 * @author Pieter Meiresone
	 */
	public void setIndexInCourseComponent(int indexInCourseComponent) {
		this.indexInCourseComponent = indexInCourseComponent;
	}

	/**
	 * @return the frozen
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * @param frozen the frozen to set
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	/**
	 * Returns the enddate of the entry. This is a derived value based based on
	 * the startdate and the duration of the coursecomponent. This method is
	 * static so it can be used with Drools Rule engine.
	 * 
	 * @param e
	 *            The entry of which the enddate has to be calculated.
	 * 
	 * @return The enddate of the entry slot.
	 */
	public static Date calcEndDate(Entry e) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(e.getStartingDate());
		cal.add(Calendar.HOUR, e.getCourseComponent().getDuration());
		return cal.getTime();
	}

	/**
	 * Returns the enddate of the entry. This is a derived value based based on
	 * the startdate and the duration of the coursecomponent. This method is
	 * static so it can be used with Drools Rule engine.
	 * 
	 * @see {@link #calcEndDate(Entry)}
	 * 
	 * @param entryStartDate
	 *            The start date of the entry.
	 * @param entryCc
	 *            The course component of the entry.
	 * @return The enddate of the entry slot.
	 */
	public static Date calcEndDate(Date entryStartDate, CourseComponent entryCc) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entryStartDate);
		cal.add(Calendar.HOUR, entryCc.getDuration());
		return cal.getTime();
	}

	// @Override
	// public String toString() {
	// return "Entry [startDate=" + startDate + ", endDate=" + endDate
	// + ", courseComponent=" + courseComponent + ", room=" + room
	// + "]";
	// }

	@Override
	public String toString() {
		String result = "";

		result += "Lecture start: Week ";
		Calendar cal = Calendar.getInstance();
		cal.setTime(startingDate);
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += ", Date ";
		result += startingDate.toString();
		result += ", Duration: ";
		result += courseComponent.getDuration();
		result += "; CourseComp: ";
		result += courseComponent.hashCode();
		result += " (startDate: Week ";
		cal.setTime(courseComponent.getStartingDate());
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += " )";
		result += "; Room ID: ";
		result += room.hashCode();
		
		// Print Trajects
		result += " ; Traject ID: ";
		Set<Traject> trajects = this.courseComponent.getCourse().getTrajects();
		if (trajects != null) {
			for (Traject t : trajects) {
				result += t.getId() + ", ";
			}
		}
		// Print Teachers
		result += "; Teachers: ";
		Set<User> teachers = courseComponent.getTeachers();
		if (teachers != null) {
			for (User u : teachers) {
				result += u.getUsername() + ", ";
			}
		}

		return result;
	}

	/**
	 * Sorts entry's based on their startdate. If the startdate is the same,
	 * then the enddate is compared.
	 */
	@Override
	public int compareTo(Entry o) {
		return new CompareToBuilder()
				.append(this.startingDate, o.startingDate)
				.append(this.courseComponent.getDuration(),
						o.courseComponent.getDuration()).toComparison();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
