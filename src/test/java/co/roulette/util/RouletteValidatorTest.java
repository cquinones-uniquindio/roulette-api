package co.roulette.util;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import co.roulette.model.Bet;
import co.roulette.util.RouletteValidator;

/**
 * Roulette validator class for testing
 * 
 * @author Carlos Quiñones
 *
 */
class RouletteValidatorTest {

	@Test
	void testValidateBet() {
		Bet bet = new Bet();
		assertThat(RouletteValidator.validateBet(bet)).isEqualTo(false);
		bet.setColorSelected("negro");
		assertThat(RouletteValidator.validateBet(bet)).isEqualTo(true);
		bet.setNumberSelected(14L);
		assertThat(RouletteValidator.validateBet(bet)).isEqualTo(true);
	}
}
