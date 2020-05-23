package co.roulette.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.roulette.model.Bet;
import co.roulette.model.Roulette;
import co.roulette.service.BetServiceImpl;
import co.roulette.service.RouletteServiceImpl;

/**
 * Roulette controller class for testing
 * @author ceq93
 *
 */
@SpringBootTest
public class RouletteControllerTest {
	@MockBean
	private RouletteServiceImpl rouletteService;
	@MockBean
	private BetServiceImpl betService;
	@Autowired
	private RouletteController rouletteController;

	@Test
	public void testCreateRoulette() {
		Mockito.when(rouletteService.saveRoulette()).thenReturn(14L);
		assertEquals(rouletteController.createRoulette(), 14L);
	}

	@Test
	public void testEnableRoluette() {
		HashMap<String, Object> response = new HashMap<String, Object>();
		ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<HashMap<String, Object>>(response,
				HttpStatus.CREATED);
		Mockito.when(rouletteService.enableRouletteById(14L)).thenReturn(responseEntity);
		assertNotNull(rouletteController.enableRoluette(14L));
	}

	@Test
	public void testSelectRoulette() {
		HashMap<String, Object> response = new HashMap<String, Object>();
		ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<HashMap<String, Object>>(response,
				HttpStatus.BAD_REQUEST);
		Bet bet = new Bet();
		Mockito.when(betService.newBet(bet)).thenReturn(responseEntity);
		assertEquals(rouletteController.selectRoulette(bet), responseEntity);
	}

	@Test
	public void testCloseRulette() {
		Mockito.when(betService.closeBet(14L)).thenReturn(new ArrayList<Bet>());
		assertNotNull(rouletteController.closeRulette(14L));
	}

	@Test
	public void getRoulettes() {
		Mockito.when(rouletteService.getAllRoulettes()).thenReturn(new ArrayList<Roulette>());
		assertEquals(rouletteController.getRoulettes().size(), 0);
	}

}
