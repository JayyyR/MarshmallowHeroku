package edu.wm.werewolf.dao;

import java.util.List;

import wm.edu.exceptions.NoPlayerFoundException;
import edu.wm.werewolf.domain.Player;

public interface IPlayerDAO {
	
	public List<Player> getAllAlive();
	
	void insertPlayer(Player player);
	
	void updatePos(Player player);
	
	public void setDead(Player p);
	
	public void placeVoteOn(Player p);
	
	Player getPlayerById(String id) throws NoPlayerFoundException;
	
	public void setAdmin(Player player);

	public boolean checkAdmin(Player player);
}
