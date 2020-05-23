package co.roulette.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.roulette.model.Bet;
import co.roulette.model.Roulette;
import co.roulette.service.BetServiceImpl;
import co.roulette.service.RouletteServiceImpl;
import co.roulette.util.RouletteValidator;

/**
 * Allow to make http requests
 * 
 * @author Carlos Quiñones
 * @version 1.0
 */

@RestController
public class RouletteController {

	@Autowired
	private RouletteServiceImpl rouletteService;
	@Autowired
	private BetServiceImpl betService;
	
	@GetMapping("/new_roulette")
	public Long createRoulette() {

		return rouletteService.saveRoulette();
	}

	@PutMapping("/open_roulette/")
	public ResponseEntity<HashMap<String, Object>> enableRoluette(@RequestParam("id") Long id) {
		return rouletteService.enableRouletteById(id);
	}

	@PostMapping("/new_bet")
	public ResponseEntity<HashMap<String, Object>> selectRoulette(@RequestBody Bet bet) {
		boolean isValidRoulette = RouletteValidator.validateBet(bet);
		ResponseEntity<HashMap<String, Object>> output = null;
		if (isValidRoulette) {
			output = betService.newBet(bet);
			
			return output;
		}
		HashMap<String, Object> response = new HashMap<String, Object>();
		output = new ResponseEntity<HashMap<String,Object>>(response, HttpStatus.BAD_REQUEST);
		
		return output;
	}
	
	@GetMapping("/close_roulette")
	public ArrayList<Bet> closeRulette(@RequestParam("roulette") Long roulettId) {
		
		return betService.closeBet(roulettId);
	}
	@GetMapping("/all_roulettes")
	public ArrayList<Roulette> getRoulettes(){
		
		return rouletteService.getAllRoulettes();
	}
}
