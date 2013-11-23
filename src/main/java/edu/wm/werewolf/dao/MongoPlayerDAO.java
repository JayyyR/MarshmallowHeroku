package edu.wm.werewolf.dao;

import java.net.UnknownHostException;
import java.util.ArrayList; //error!
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

import wm.edu.exceptions.NoPlayerFoundException;
import edu.wm.werewolf.HomeController;
import edu.wm.werewolf.domain.Player;

public class MongoPlayerDAO implements IPlayerDAO {
	//@Autowired private MongoClient mongo;
	//@Autowired private MongoURI mongoURI;
	private static final Logger logger = LoggerFactory.getLogger(MongoPlayerDAO.class);
	MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));

	@Override
	public List<Player> getAllAlive() {
		//DB db = mongo.getDB("Werewolf");
		
		String pass = new String(mongoURI.getPassword());
		logger.info("Mongo user is " + mongoURI.getUsername() + " mongo pass is: " + pass);
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
 
		
		List<Player> players = new ArrayList<Player>();

		DBCollection playersCol = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("dead", false);

		//create and return players
		DBCursor cursor = playersCol.find(searchQuery);

		
		//fix casting
		while (cursor.hasNext()) {
			DBObject playerFound = cursor.next();
			players.add(new Player((String) playerFound.get("id").toString(), ((Boolean) playerFound.get("dead")).booleanValue(),
					(Double.valueOf(playerFound.get("lat").toString())), (Double.valueOf(playerFound.get("lng").toString())), (String) playerFound.get("userid").toString(),
					 ((Boolean) playerFound.get("werewolf")).booleanValue()));
		}

		return players;
	}

	@Override
	public void setDead(Player p) {
		//DB db = mongo.getDB("Werewolf");
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
        
		DBCollection players = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", p.getId());

		BasicDBObject deadDocument = new BasicDBObject();
		deadDocument.put("dead", true);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", deadDocument);

		players.update(searchQuery, updateObj);
	}

	@Override
	public void insertPlayer(Player player) {
		//DB db = mongo.getDB("Werewolf");
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
        
		DBCollection players = db.getCollection("Player");
		BasicDBObject document = new BasicDBObject();
		document.put("id", player.getId());
		document.put("dead", player.isDead());
		document.put("lat", player.getLat());
		document.put("lng", player.getLng());
		document.put("userid", player.getUserId());
		document.put("werewolf", player.isWerewolf());
		document.put("votedon", player.isVotedOn());
		players.insert(document);

	}

	@Override
	public Player getPlayerById(String id) throws NoPlayerFoundException {

		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
        
		//DB db = mongo.getDB("Werewolf");
		DBCollection players = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);

		
		System.out.println("id in getplayer nearby mongoplayerdao is: " + id);
		//create and return player
		DBCursor cursor = players.find(searchQuery);
		DBObject playerFound = cursor.next();
		Player player = new Player((String) playerFound.get("id").toString(), ((Boolean) playerFound.get("dead")).booleanValue(),
				(Double.valueOf(playerFound.get("lat").toString())), (Double.valueOf(playerFound.get("lng").toString())), (String) playerFound.get("userid").toString(),
				 ((Boolean) playerFound.get("werewolf")).booleanValue());
		return player;
	}

	@Override
	public void placeVoteOn(Player p) {

		//DB db = mongo.getDB("Werewolf");
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
		DBCollection players = db.getCollection("Player");

		System.out.println("in mongo, placing vote on player: " + p.getId());
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", p.getId());

		BasicDBObject voteDocument = new BasicDBObject();
		voteDocument.put("votedon", true);

		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", voteDocument);

		players.update(searchQuery, updateObj);

	}
	
	@Override
	public void setAdmin(Player player){
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

		DBCollection users = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", player.getId());
		
		BasicDBObject admin = new BasicDBObject();
		
		admin.put("admin", true);
		
		BasicDBObject updateAdmin = new BasicDBObject();
		updateAdmin.put("$set", admin);

		users.update(searchQuery, updateAdmin);
		
	}
	

	@Override
	public void updatePos(Player player) {
		// TODO Auto-generated method stub

		//DB db = mongo.getDB("Werewolf");
		
		DB db = null;
		try {
			db = mongoURI.connectDB();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

		DBCollection users = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", player.getId());

		BasicDBObject latDoc = new BasicDBObject();
		latDoc.put("lat", player.getLat());
		
		BasicDBObject lngDoc = new BasicDBObject();
		lngDoc.put("lng", player.getLng());

		BasicDBObject updateLat = new BasicDBObject();
		updateLat.put("$set", latDoc);
		
		BasicDBObject updateLng = new BasicDBObject();
		updateLng.put("$set", lngDoc);

		users.update(searchQuery, updateLat);
		users.update(searchQuery, updateLng);

	}

	@Override
	public boolean checkAdmin(Player player) {
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
		DBCollection players = db.getCollection("Player");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", player.getId());

		
		DBCursor cursor = players.find(searchQuery);
		DBObject playerFound = cursor.next();
		
		return ((Boolean) playerFound.get("admin")).booleanValue();
		
	}

}
