package edu.wm.werewolf.domain;

import java.util.Date;

public class Kill {
	
	private String killerID;
	private String victimId;
	private Date timestamp;
	private float lat;
	private float lng;
	public String getKillerID() {
		return killerID;
	}
	public Kill(String killerID, String victimId, Date timestamp, float lat,
			float lng) {
		super();
		this.killerID = killerID;
		this.victimId = victimId;
		this.timestamp = timestamp;
		this.lat = lat;
		this.lng = lng;
	}
	public void setKillerID(String killerID) {
		this.killerID = killerID;
	}
	public String getVictimId() {
		return victimId;
	}
	public void setVictimId(String victimId) {
		this.victimId = victimId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}

}
