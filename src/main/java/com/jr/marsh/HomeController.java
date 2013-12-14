package com.jr.marsh;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jr.marsh.domain.Score;
import com.jr.marsh.service.GameService;
//test
/**
 * Handles requests for the application home page.
 */
@Controller 
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired private GameService gameService;


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		logger.info("MONGO HQ URL IS: " + System.getenv("MONGOHQ_URL"));

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	//home

	@RequestMapping(value="/scores", method=RequestMethod.GET)
	public @ResponseBody List<Score> getScores(){

		List<Score> scores = gameService.getScores();

		return scores;


	}
	

	// handles person form submit
	@RequestMapping(value="/insertscore", method=RequestMethod.POST)
	@ResponseBody
	public void insertScore(Long score, String name) {
		gameService.insertScore(score, name);

	}
}

	