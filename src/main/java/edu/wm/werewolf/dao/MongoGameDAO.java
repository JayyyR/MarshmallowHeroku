package edu.wm.werewolf.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import edu.wm.werewolf.domain.Game;

public class MongoGameDAO implements IGameDAO{
	
	@Autowired private MongoClient mongo;

	@Override
	public void createGame(Game game) {
		// TODO Auto-generated method stub
		
		DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		BasicDBObject document = new BasicDBObject();
		document.put("dayNightFreq", game.getDayNightFreq());
		document.put("createdDate", game.getCreatedDate());
		games.insert(document);
		
	}

}
