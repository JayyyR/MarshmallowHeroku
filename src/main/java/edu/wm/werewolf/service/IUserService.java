package edu.wm.werewolf.service;

import java.util.List;

import edu.wm.werewolf.domain.User;

public interface IUserService {

	void createUser(User user);

	User getUserById(String id);

	List<User> getAllUsers();

	void updateEmail(User u);

	void updateImage(User u);

}
