package com.solinvictus.SpringSecurityDemo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoDTO {
	
	private String task;
	
	@JsonProperty
	private boolean isDone;

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	
	
}
