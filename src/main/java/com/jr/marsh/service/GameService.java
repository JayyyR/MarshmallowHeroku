package com.jr.marsh.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.jr.marsh.dao.IGameDAO;
import com.jr.marsh.domain.Game;
import com.jr.marsh.domain.Score;

public class GameService {
	
	@Autowired private IGameDAO gameDAO;
	
	public void insertScore(Long score, String name) {
		gameDAO.insertScore(score, name);
	
	}
	

}
