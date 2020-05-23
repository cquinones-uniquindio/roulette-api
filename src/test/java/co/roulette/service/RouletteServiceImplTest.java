package co.roulette.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import co.roulette.model.Roulette;
import co.roulette.repository.RouletteRepository;

/**
 * Roulette service class for testing
 * 
 * @author ceq93
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RouletteServiceImplTest {
	
	@Autowired
	RouletteServiceImpl rouletteService;
	@MockBean
	RouletteRepository rouletteRepository;

	@Test
	public void testfindAllRoulettes() {
		Mockito.when(rouletteRepository.findAll()).thenReturn(new ArrayList<Roulette>());
		assertNotNull(rouletteService.findAllRoulettes());
	}

	@Test
	public void testSaveRoulette() {
		Roulette roulette = new Roulette(null, false);
		Mockito.when(rouletteRepository.save(roulette)).thenReturn(roulette);
		assertNull(rouletteService.findRouletteById(14L));
	}

	@Test
	public void testFindRouletteById() {
		Roulette roulette = new Roulette();
		roulette.setRouletteId(14L);
		Optional<Roulette> optinalRoulette = Optional.of(roulette);	
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optinalRoulette);
		assertEquals(rouletteService.findRouletteById(14L), roulette);
	}

	@Test
	public void testEnableRouletteById() {
		Roulette roulette = new Roulette(14L, false);
		Optional<Roulette> optionalRoulette = Optional.of(roulette);
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("message", "La ruleta ha sido activada con éxito");
		response.put("roulette", roulette);
		
		ResponseEntity<HashMap<String, Object>> output = new ResponseEntity<HashMap<String,Object>>(response, HttpStatus.CREATED);
		Mockito.when(rouletteRepository.findById(14L)).thenReturn(optionalRoulette);
		Mockito.when(rouletteRepository.save(roulette)).thenReturn(roulette);
		assertEquals(rouletteService.enableRouletteById(14L), output);
	}

}
