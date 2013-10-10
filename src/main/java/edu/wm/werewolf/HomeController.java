package edu.wm.werewolf;

import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.impl.JsonParserBase;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wm.werewolf.dao.IPlayerDAO;
import edu.wm.werewolf.domain.Game;
import edu.wm.werewolf.domain.Player;
import edu.wm.werewolf.domain.User;
import edu.wm.werewolf.service.GameService;
import edu.wm.werewolf.service.PlayerService;
import edu.wm.werewolf.service.UserService;
//test
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired private PlayerService playerService;
	@Autowired private UserService userService;
	@Autowired private GameService gameService;


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		System.out.println("MONGO HQ URL IS: " + System.getenv("MONGOHQ_URL"));
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	//home

	@RequestMapping(value="/players/alive", method=RequestMethod.GET)
	public @ResponseBody List<Player> getAllAlive(){

		List<Player> players = playerService.getAllAlive();
		return players;

	}
	
	@RequestMapping("/players/{id}")
	@ResponseBody
	public Player getById(@PathVariable String id) {
		System.out.println("in id");
		return playerService.getPlayerById(id);
	}


	@RequestMapping(value="/players/nearby", method=RequestMethod.GET)
	public @ResponseBody List<Player> getNearbyPlayers(String id){
		
		System.out.println("in nearby yo");
		Player requestingPlayer = playerService.getPlayerById(id);
		List<Player> players = playerService.getAllAlive();
		List<Player> closePlayers = new ArrayList<Player>();
		for (Player player:players){

			//check if within 10(?) of lat and long)
			if (player.getLat() <= requestingPlayer.getLat() + 10
					&& player.getLat() >= requestingPlayer.getLat() - 10){
				if (player.getLng() <= requestingPlayer.getLng() + 10
						&& player.getLng() >= requestingPlayer.getLng() - 10){
					
					System.out.println("adding players");

					closePlayers.add(player);
				}
			}

		}
		return closePlayers;

	}

	@RequestMapping(value="/players/insert", method=RequestMethod.POST)
	public @ResponseBody String insert(String id, boolean isDead, double lat, 
			double lng, String userId, boolean isWerewolf){

		Player player = new Player(id, isDead, lat, lng, userId, isWerewolf);
		playerService.insertPlayer(player);
		return "inserted";

	}

    
	 // handles person form submit
    @RequestMapping(value="/players/vote", method=RequestMethod.POST)
    @ResponseBody
    public void placeVote(String id) {
    	System.out.println("in place vote homecontrol");
        playerService.placeVoteOn(playerService.getPlayerById(id));
    }

    
    // handles person form submit
    @RequestMapping(value="/players/kill", method=RequestMethod.POST)
    @ResponseBody
    public void kill(String id) {
    	System.out.println("in kill homecontroller");
    	playerService.setDead(playerService.getPlayerById(id));
       
    }
    
 // handles person form submit
    @RequestMapping(value="/players/updatePos", method=RequestMethod.POST)
    @ResponseBody
    public void updatePos(String id, double lat, double lng) {
    	System.out.println("in update homecontroller");
    	
    	Player updatePlay = playerService.getPlayerById(id);
    	updatePlay.setLat(lat);
    	updatePlay.setLng(lng);
    	playerService.updatePos(updatePlay);
       
    }
    
    ///////////////User Stuff////////////////
    
    
    @RequestMapping(value="/users/create", method=RequestMethod.POST)
	public @ResponseBody String create(String firstName, String lastName, String id,
			String hashedPassword, String imageURL, String email) {

		User user = new User(firstName, lastName, id, hashedPassword, imageURL, email);
		userService.createUser(user);
		return "created";

	}
    
    @RequestMapping(value="/users/getPass", method=RequestMethod.GET)
	public @ResponseBody String getPass(String id){

		User user = userService.getUserById(id);
		String hashedPass = user.getHashedPassword();
		return hashedPass;

	}
    
    @RequestMapping(value="/users/getAll", method=RequestMethod.GET)
	public @ResponseBody List<User> getAll(){
    	List<User> users = userService.getAllUsers();
		return users;

	}
    
    @RequestMapping(value="/users/updateEmail", method=RequestMethod.POST)
	public @ResponseBody void updateEmail(String id, String email) {
    	
    	User user = userService.getUserById(id);
    	user.setEmail(email);
    	
    	userService.updateEmail(user);

	}
    
    @RequestMapping(value="/users/updateImage", method=RequestMethod.POST)
   	public @ResponseBody void updateImage(String id, String imageURL) {
       	
       	User user = userService.getUserById(id);
       	user.setImageURL(imageURL);
       	
       	userService.updateImage(user);

   	}
    
    ////////////////Game stuff/////////////
    

    @RequestMapping(value="/games/create", method=RequestMethod.POST)
   	public @ResponseBody String create(int dayNightFreq, Date createdDate) {
    	
   		Game game = new Game(dayNightFreq, createdDate);
   		gameService.createGame(game);
   		return "created game";

   	}

}
