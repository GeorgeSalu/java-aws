package br.com.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

	@Test
	void test() {
		SimpleMath math = new SimpleMath();
		
		Double actual = math.sum(6.2D, 2D);
		double expected = 8.2D;
		assertEquals(expected, actual);
	}
	
}
