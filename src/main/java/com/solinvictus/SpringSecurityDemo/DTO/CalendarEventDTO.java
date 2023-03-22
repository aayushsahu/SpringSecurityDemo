package com.solinvictus.SpringSecurityDemo.DTO;

import java.util.Date;

import com.solinvictus.SpringSecurityDemo.Entity.DateKeeper;

public class CalendarEventDTO {
	
	private DateKeeper dateKeeper;
	private Date eventDate;
	private String eventDescription;
	private String noteForEvent;
	
	public CalendarEventDTO() {
		
	}
	
	public CalendarEventDTO(Date eventDate, String eventDescription,
			String noteForEvent) {
		super();
		this.dateKeeper = new DateKeeper();
		this.eventDate = eventDate;
		this.eventDescription = eventDescription;
		this.noteForEvent = noteForEvent;
	}
	
	public DateKeeper getDateKeeper() {
		return dateKeeper;
	}
	public void setDateKeeper(DateKeeper dateKeeper) {
		this.dateKeeper = dateKeeper;
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
}
