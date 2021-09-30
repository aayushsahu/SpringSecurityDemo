package com.solinvictus.SpringSecurityDemo.Entity;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Embeddable
public class DateKeeper {
	
	private Date creationDate;
	private Date lastModificationDate;
	
	
	public DateKeeper() {
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public Date getLastModificationDate() {
		return lastModificationDate;
	}
	
	@PrePersist
	public void prePersist() {
		this.creationDate = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.lastModificationDate = new Date();
	}
}
