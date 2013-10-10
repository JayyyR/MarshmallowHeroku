package edu.wm.werewolf.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.domain.User;

public class MongoUserDAO implements IUserDAO {
	
	@Autowired private DB db;
	//@Autowired private MongoClient mongo;
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		//DB db = mongo.getDB("Werewolf");
		
		DBCollection users = db.getCollection("User");
		BasicDBObject document = new BasicDBObject();
		document.put("id", user.getId());
		document.put("hashedPass", user.getHashedPassword());
		document.put("email", user.getEmail());
		document.put("firstName", user.getFirstName());
		document.put("lastName", user.getLastName());
		document.put("imageURL",user.getImageURL());
		users.insert(document);
		
	}

	@Override
	public User getUserByID(String id) {
		// TODO Auto-generated method stub
		
		//DB db = mongo.getDB("Werewolf");
		
		DBCollection players = db.getCollection("User");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);

		//create and return player
		DBCursor cursor = players.find(searchQuery);
		DBObject userFound = cursor.next();
		User user = new User((String) userFound.get("firstName"), (String) userFound.get("lastName"),
				(String) userFound.get("id"), (String) userFound.get("hashedPass"), (String) userFound.get("imageURL"),
				(String) userFound.get("email"));
		
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		//DB db = mongo.getDB("Werewolf");

		DBCollection usersCol = db.getCollection("User");

		//create and return users
		DBCursor cursor = usersCol.find();

		while (cursor.hasNext()) {
			DBObject userFound = cursor.next();
			users.add(new User((String) userFound.get("firstName"), (String) userFound.get("lastName"),
					(String) userFound.get("id"), (String) userFound.get("hashedPass"), (String) userFound.get("imageURL"),
					(String) userFound.get("email")));
		}

		return users;
	}

	@Override
	public void updateEmail(User u) {
		// TODO Auto-generated method stub
		
		//DB db = mongo.getDB("Werewolf");
		
		DBCollection users = db.getCollection("User");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", u.getId());

		BasicDBObject emDocument = new BasicDBObject();
		emDocument.put("email", u.getEmail());
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", emDocument);
		
		users.update(searchQuery, updateObj);
		
	}

	@Override
	public void updateImage(User u) {
		// TODO Auto-generated method stub
		
		//DB db = mongo.getDB("Werewolf");
		
		DBCollection users = db.getCollection("User");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", u.getId());

		BasicDBObject doc = new BasicDBObject();
		doc.put("imageURL", u.getImageURL());
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", doc);
		
		users.update(searchQuery, updateObj);
		
	}

}
