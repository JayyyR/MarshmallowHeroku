package edu.wm.werewolf.domain;

import java.util.Date;

public class Game {
	
	private int dayNightFreq;
	
	private Date createdDate;
	private boolean isDay = true;
	public Game(int dayNightFreq, Date createdDate) {
		super();
		this.dayNightFreq = dayNightFreq;
		this.createdDate = createdDate;
	}
	
	public boolean isDay() {
		return isDay;
	}
	public void setDay(boolean isDay) {
		this.isDay = isDay;
	}
	public int getDayNightFreq() {
		return dayNightFreq;
	}
	public void setDayNightFreq(int dayNightFreq) {
		this.dayNightFreq = dayNightFreq;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
