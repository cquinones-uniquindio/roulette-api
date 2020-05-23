package co.roulette.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.roulette.model.Roulette;
import co.roulette.repository.RouletteRepository;

/**
 * Allow roulette's CRUD operations
 * 
 * @author Carlos Quiñones
 *
 */
@Service
public class RouletteServiceImpl implements RouletteServiceInterface {
	@Autowired
	private RouletteRepository rouletteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Roulette> findAllRoulettes() {
		List<Roulette> result = new ArrayList<>();
		rouletteRepository.findAll().forEach(result::add);

		return result;
	}

	@Override
	public Long saveRoulette() {
		Roulette request = new Roulette(null, false);
		Roulette response = rouletteRepository.save(request);
		return response.getRouletteId();
	}

	@Override
	@Transactional(readOnly = true)
	public Roulette findRouletteById(Long id) {
		return rouletteRepository.findById(id).orElse(null);
	}

	@Override
	public ResponseEntity<HashMap<String, Object>> enableRouletteById(Long roulette_id) {
		Optional<Roulette> roulette = rouletteRepository.findById(roulette_id);
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (roulette.isPresent()) {
			Roulette request = roulette.get();
			{
				if (!request.isRouletteStatus())
					request.setBets(null);
			}
			request.setRouletteStatus(true);
			rouletteRepository.save(request);
			response.put("message", "La ruleta ha sido activada con éxito");
			response.put("roulette", request);

			return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.CREATED);
		} else {
			response.put("message", "La apuesta no es correcta");
			response.put("error", HttpStatus.BAD_REQUEST);

			return new ResponseEntity<HashMap<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ArrayList<Roulette> getAllRoulettes() {
		ArrayList<Roulette> result = new ArrayList<>();
		rouletteRepository.findAll().forEach(result::add);

		return result;
	}
}
