package co.roulette.service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import co.roulette.model.Bet;

/**
 * Interface with all operations over bets
 * @author Carlos Quiñones
 *
 */
public interface BetServiceInterface {
	public ResponseEntity<?> newBet(Bet bet);
	public ResponseEntity<?> saveBet(Bet bet);
	public boolean checkRouletteById(Long roulette_id);
	public ArrayList<Bet> closeBet(Long roulette_id);
}
