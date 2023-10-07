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

		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	@Test
	void testSubtraction() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.subtraction(firstNumber, secondNumber);
		double expected = 4.2D;

		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	@Test
	void testMultiplication() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.multiplication(firstNumber, secondNumber);
		double expected = 12.4D;

		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	@Test
	void testDivision() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.division(firstNumber, secondNumber);
		double expected = 3.1D;

		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	@Test
	void testMean() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.mean(firstNumber, secondNumber);
		double expected = 4.1D;

		assertEquals(expected, actual,
				() -> "(" + firstNumber + " + " + secondNumber + ")/2" + " did not producer " + expected + "!");
	}

	@Test
	void testSqaureRoot() {
		SimpleMath math = new SimpleMath();
		double number = 81D;

		Double actual = math.squareRoot(number);
		double expected = 9D;

		assertEquals(expected, actual, () -> "Sqaure root of" + number + " did not producer " + expected + "!");
	}

}
