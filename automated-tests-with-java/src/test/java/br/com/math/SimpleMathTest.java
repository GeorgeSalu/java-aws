package br.com.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Math Operations in SimpleMath Class")
public class SimpleMathTest {

	// test[System Under test]_[Condition or State Change]_[Expected result]
	@Test
	@DisplayName("Test 6.2 + 2 = 8.2")
	void testSum_when_SixDotToenIsAddedByTwo_ShouldReturnEightDotTwo() {
		// AAA Arrange, Act, Assert
		// Given / Arrange
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;
		double expected = 8.2D;

		// When / Act
		Double actual = math.sum(firstNumber, secondNumber);

		// Then / Assert
		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	@Test
	@DisplayName("Test 6.2 - 2 = 4.2")
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
	@DisplayName("Test 6.2 - 2 = 12.4")
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
	@DisplayName("Test 6.2 - 2 = 3.1")
	void testDivision() {
		SimpleMath math = new SimpleMath();
		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.division(firstNumber, secondNumber);
		double expected = 3.1D;

		assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not producer " + expected + "!");
	}

	// test[System Under test]_[Condition or State Change]_[Expected result]
	@Disabled("TODO: We need still work on it")
	@Test
	void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowAritmeticException() {
		fail();
	}

	@Test
	@DisplayName("Test 6.2 mean 2 = 4.1")
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
	@DisplayName("Test 81 squareRoot = 9")
	void testSqaureRoot() {
		SimpleMath math = new SimpleMath();
		double number = 81D;

		Double actual = math.squareRoot(number);
		double expected = 9D;

		assertEquals(expected, actual, () -> "Sqaure root of" + number + " did not producer " + expected + "!");
	}

}
