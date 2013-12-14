package com.jr.marsh.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jr.marsh.domain.Game;
import com.jr.marsh.domain.Score;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

public class MongoGameDAO implements IGameDAO{


	MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));


	public void insertScore(Long score, String name) {
		// TODO Auto-generated method stub
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

		
	}


	public ArrayList<Score> getScores() {
		// TODO Auto-generated method stub
		return null;
	}

}
