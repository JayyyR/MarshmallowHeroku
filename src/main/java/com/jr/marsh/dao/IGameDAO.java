package com.jr.marsh.dao;

import java.util.ArrayList;

import com.jr.marsh.domain.Game;
import com.jr.marsh.domain.Score;
import com.mongodb.DBCursor;

public interface IGameDAO {
	
	void insertScore(Long score, String name);
	ArrayList<Score> getScores();
}
