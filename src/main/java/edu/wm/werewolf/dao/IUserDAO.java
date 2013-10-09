package edu.wm.werewolf.dao;

import java.util.List;

import edu.wm.werewolf.domain.User;

public interface IUserDAO {
	
	void createUser(User user);
	
	User getUserByID(String id);
	
	List<User> getAllUsers();
	
	void updateEmail(User u);
	
	void updateImage(User u);
	

}
