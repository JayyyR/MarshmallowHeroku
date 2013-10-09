package edu.wm.werewolf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.User;

public class UserService implements IUserService {

	
	@Autowired private IUserDAO userDAO;
	
	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
		userDAO.createUser(user);
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDAO.getUserByID(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDAO.getAllUsers();
	}

	@Override
	public void updateEmail(User u) {
		// TODO Auto-generated method stub
		userDAO.updateEmail(u);
		
	}

	@Override
	public void updateImage(User u) {
		// TODO Auto-generated method stub
		userDAO.updateImage(u);
	}

}
