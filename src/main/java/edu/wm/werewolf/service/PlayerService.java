package edu.wm.werewolf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import wm.edu.exceptions.NoPlayerFoundException;
import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.dao.IUserDAO;
import edu.wm.werewolf.domain.Player;

public class PlayerService {

	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDAO;
	
	public void restartGame(){
		//drop all players
		//for each user, create new player and insert them
	}
	
	public List<Player> getAllAlive(){
		return playerDAO.getAllAlive();
	}
	
	public void setAdmin(Player player){
		playerDAO.setAdmin(player);
	}
	
	public void insertPlayer(Player player){
		playerDAO.insertPlayer(player);
	}
	
	public void setDead(Player player){
		playerDAO.setDead(player);
	}
	
	public void placeVoteOn(Player player){
		System.out.println("placing vote in gameservice");
		System.out.println("vote going on: " + player.getId());
		playerDAO.placeVoteOn(player);
	}
	
	public void updatePos(Player player){
		playerDAO.updatePos(player);
	}
	
	public Player getPlayerById(String id){
		try {
			return playerDAO.getPlayerById(id);
		} catch (NoPlayerFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setVoted(Player player){
		playerDAO.setHasVoted(player);
	}
}
