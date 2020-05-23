package co.roulette.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import co.roulette.model.Bet;
import co.roulette.model.Roulette;
import co.roulette.repository.RouletteRepository;

/**
 * Bet service class for testing
 * 
 * @author Carlos Quiñones
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BetServiceImplTest {
	
	@MockBean
	RouletteRepository rouletteRepository;
	
	@Autowired
	BetServiceImpl betService;

	@Test
	void testNewBet() {
		Bet bet = new Bet();
		bet.setRouletteId(14L);
		bet.setBetAmount(50L);
		bet.setColorSelected("negro");
		Roulette roulette = new Roulette(14L, true);
		ArrayList<Bet> bets = new ArrayList<Bet>();
		roulette.setBets(bets);
		Optional<Roulette> optionalRoulette = Optional.of(roulette);
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("message", "La apuesta ha sido creada con éxito");
		response.put("bet", bet);
		ResponseEntity<HashMap<String, Object>> output = new ResponseEntity<HashMap<String,Object>>(response, HttpStatus.CREATED);
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optionalRoulette);
		Mockito.when(rouletteRepository.save(roulette)).thenReturn(roulette);
		assertEquals(betService.newBet(bet), output);
		}

	@Test
	void testSaveBet() {
		Bet bet = new Bet();
		bet.setRouletteId(14L);
		bet.setBetAmount(50L);
		bet.setColorSelected("negro");
		Roulette roulette = new Roulette(14L, true);
		ArrayList<Bet> bets = new ArrayList<Bet>();
		roulette.setBets(bets);
		Optional<Roulette> optionalRoulette = Optional.of(roulette);
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Se ha realizado con éxito");
		response.put("bet", bet);
		ResponseEntity<HashMap<String, Object>> output = new ResponseEntity<HashMap<String,Object>>(response, HttpStatus.OK);
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optionalRoulette);
		Mockito.when(rouletteRepository.save(roulette)).thenReturn(roulette);
		assertEquals(betService.saveBet(bet), output);		
	}

	@Test
	public void testCheckRouletteById() {
		Roulette roulette = new Roulette();
		roulette.setRouletteStatus(true);
		Optional<Roulette> optinalRoulette = Optional.of(roulette);		
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optinalRoulette);
		assertEquals(betService.checkRouletteById(14L), true);
	}

	@Test
	void testCloseBet() {
		Roulette roulette = new Roulette();
		roulette.setRouletteId(14L);
		Bet bet = new Bet();
		roulette.addBet(bet);
		Optional<Roulette> optinalRoulette = Optional.of(roulette);		
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optinalRoulette);
		assertEquals(betService.closeBet(14L), roulette.getBets());
	}
}
