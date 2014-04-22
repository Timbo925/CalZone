package com.vub.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.vub.scheduler.EntryDifficultyComparator;

/**
 * Data object that represents an entry in someone's calender.
 * 
 * @author pieter
 * 
 */
@PlanningEntity(difficultyComparatorClass = EntryDifficultyComparator.class)
public class Entry implements Comparable<Entry> {
	Date startDate;
	Room room;

	CourseComponent courseComponent;
	int indexInCourseComponent;
	boolean frozen;

	@PlanningVariable(valueRangeProviderRefs = { "startDateRange" })
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@PlanningVariable(valueRangeProviderRefs = { "roomRange" })
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
		cal.setTime(e.getStartDate());
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
		cal.setTime(startDate);
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += ", Date ";
		result += startDate.toString();
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
				.append(this.startDate, o.startDate)
				.append(this.courseComponent.getDuration(),
						o.courseComponent.getDuration()).toComparison();
	}
}
