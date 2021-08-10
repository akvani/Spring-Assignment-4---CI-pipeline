package com.stackroute.newz.model;

import java.time.LocalDateTime;

public class Reminder {
	String reminderId;
	LocalDateTime schedule;

	/*
	 * This class should have two fields(reminderId,schedule).
	 * This class should also contain the getters and setters for the 
	 * fields along with the parameterized	constructor and toString method.
	 * The value of newssourceCreationDate should not be accepted from the user but should be
	 * always initialized with the system date.
	 */
	public Reminder(String reminderId,LocalDateTime schedule)
	{
		
	}
	public Reminder() {
		
	}
	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId=reminderId;

	}

	public LocalDateTime getSchedule() {
		return schedule;
	}

	public void setSchedule() {
		this.schedule=schedule;
	}


}
