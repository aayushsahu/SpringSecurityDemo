package com.solinvictus.SpringSecurityDemo.Entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CalendarEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long calendarEventId;
	
	@Embedded
	private DateKeeper dateKeeper = new DateKeeper();
	
	private Date eventDate;
	private String eventDescription;
	private String noteForEvent;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public CalendarEvent() {
	}
	
	public CalendarEvent(Date eventDate, String eventDescription, String noteForEvent, User user) {
		super();
		this.eventDate = eventDate;
		this.eventDescription = eventDescription;
		this.noteForEvent = noteForEvent;
		this.user = user;
	}

	public Long getCalendarEventId() {
		return calendarEventId;
	}

	public void setCalendarEventId(Long calendarEventId) {
		this.calendarEventId = calendarEventId;
	}
	
	public DateKeeper getDateKeeper() {
		return dateKeeper;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getNoteForEvent() {
		return noteForEvent;
	}

	public void setNoteForEvent(String noteForEvent) {
		this.noteForEvent = noteForEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
