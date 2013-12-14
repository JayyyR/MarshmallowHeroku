package com.jr.marsh.domain;

public class Score {
	
	private String name;
	private Long score;
	
	public Score (String name, Long score){
		this.score = score;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}

}
