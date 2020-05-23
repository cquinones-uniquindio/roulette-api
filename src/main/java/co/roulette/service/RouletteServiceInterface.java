package co.roulette.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import co.roulette.model.Roulette;

/**
 * Interface with all operations over roulletes
 * 
 * @author Carlos Quiñones
 *
 */
public interface RouletteServiceInterface {

	public List<Roulette> findAllRoulettes();
	public Long saveRoulette();
	public Roulette findRouletteById(Long roulette_id);	
	public ResponseEntity<?> enableRouletteById(Long roulette_id);
	public ArrayList<Roulette> getAllRoulettes();

}
