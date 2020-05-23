package co.roulette.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.roulette.model.Bet;
import co.roulette.model.Roulette;
import co.roulette.repository.RouletteRepository;

/**
 * Allow bet's CRUD operations
 * 
 * @author Carlos Quiñones
 *
 */
@Service
public class BetServiceImpl implements BetServiceInterface {
	@Autowired
	RouletteRepository rouletteRepository;
	@Autowired
	HttpServletRequest requestUrl;
	@Override
	public ResponseEntity<HashMap<String, Object>> newBet(Bet bet) {
		boolean rouletteStatus = checkRouletteById(bet.getRouletteId());
		HashMap<String, Object> response = new HashMap<String, Object>();
		bet.setUserId(requestUrl.getHeader("user"));
		if (rouletteStatus && bet.getBetAmount() != null && bet.getBetAmount() > 0 && bet.getBetAmount() <= 10000) {
			response.put("message", "La apuesta ha sido creada con éxito");
			response.put("bet", bet);
			saveBet(bet);

			return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
		} else {
			response.put("message", "La apuesta no se puede realizar");
			response.put("error", HttpStatus.BAD_REQUEST);

			return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<HashMap<String, Object>> saveBet(Bet bet) {
		Roulette roulette = rouletteRepository.findById(bet.getRouletteId()).get();
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (bet.getColorSelected() != null) {
			bet.setNumberSelected(null);
		} else {
			bet.setColorSelected(null);
		}
		roulette.addBet(bet);
		rouletteRepository.save(roulette);
		response.put("message", "Se ha realizado con éxito");
		response.put("bet", bet);

		return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.OK);
	}

	@Override
	public boolean checkRouletteById(Long rouletteId) {
		Optional<Roulette> roulette = rouletteRepository.findById(rouletteId);
		if (roulette.isPresent()) {
			if (roulette.get().isRouletteStatus()) {

				return true;
			}
		}

		return false;
	}

	@Override
	public ArrayList<Bet> closeBet(Long rouletteId) {
		Optional<Roulette> optionalRoulette = rouletteRepository.findById(rouletteId);
		if (optionalRoulette.isPresent()) {
			Roulette roulette = optionalRoulette.get();
			roulette.setRouletteStatus(false);
			rouletteRepository.save(roulette);
			
			return roulette.getBets();
		}
		return new ArrayList<>();
	}
	
}
