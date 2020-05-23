package co.roulette.util;

import co.roulette.model.Bet;

/**
 * Allows to verify the roulette's validity
 * 
 * @author Carlos Quiñones
 * @version 1.0
 */
public class RouletteValidator {
	private RouletteValidator() {
		super();
	}

	public static boolean validateBet(Bet request) {
		if (request.getColorSelected() != null && (request.getColorSelected().equalsIgnoreCase("negro")
				|| request.getColorSelected().equalsIgnoreCase("rojo"))) {

			return true;
		}
		if (request.getNumberSelected() != null && request.getNumberSelected() >= 0
				&& request.getNumberSelected() <= 36) {

			return true;
		}

		return false;
	}
}
