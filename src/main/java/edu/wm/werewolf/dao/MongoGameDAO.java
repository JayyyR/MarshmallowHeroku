package edu.wm.werewolf.dao;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

import edu.wm.werewolf.domain.Game;

public class MongoGameDAO implements IGameDAO{
	
	//@Autowired private MongoClient mongo;
	@Autowired private MongoURI mongoURI;
	
	@Override
	public void createGame(Game game) {
		// TODO Auto-generated method stub
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		
		//DB db = mongo.getDB("Werewolf");
		DBCollection games = db.getCollection("Game");
		BasicDBObject document = new BasicDBObject();
		document.put("dayNightFreq", game.getDayNightFreq());
		document.put("createdDate", game.getCreatedDate());
		games.insert(document);
		
	}

}
