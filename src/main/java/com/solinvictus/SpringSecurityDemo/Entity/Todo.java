package com.solinvictus.SpringSecurityDemo.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Todo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long todoId;	
	private Date dateofCreation;
	private String task;
	private boolean isDone;
	
	@ManyToOne
	@JsonIgnore
	private User user;

	public Todo() {
		
	}
	
	public Todo(String task, User user) {
		super();
		this.dateofCreation = new Date();
		this.task = task;
		this.isDone = false;
		this.user = user;
	}

	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	public Date getDateofCreation() {
		return dateofCreation;
	}

	public void setDateofCreation(Date dateofCreation) {
		this.dateofCreation = dateofCreation;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean status) {
		this.isDone = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [todoId=" + todoId + ", dateofCreation=" + dateofCreation + ", task=" + task + ", isDone=" + isDone
				+ ", user=" + user.getUsername() + "]";
	}
	
	
	
}
