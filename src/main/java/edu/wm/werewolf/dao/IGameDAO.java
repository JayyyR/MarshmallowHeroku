package edu.wm.werewolf.dao;

import com.mongodb.DBCursor;

import edu.wm.werewolf.domain.Game;

public interface IGameDAO {
	
	void createGame(Game game);
	boolean checkGame();
	
	

}
