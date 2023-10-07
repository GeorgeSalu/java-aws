package br.com.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Test
	void testSum() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		
		Double actual = math.sum(firstNumber, secondNumber);
		double expected = 8.2D;

		assertEquals(expected, actual, () -> firstNumber + " + "+secondNumber+" did not producer "+expected+"!");
		assertNotEquals(9.2, actual);
		assertNotNull(actual);
	}
	
}
