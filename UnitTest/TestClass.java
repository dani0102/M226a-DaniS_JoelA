package ch.ahameafule.joel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestClass {
	
	@Test
	public final void multiplicationWithZeroReturnsZero() {
		
		MainClass classToTest = new MainClass();
		
		assertEquals(5, classToTest.multiply(10, 0), "10 x 0 equals 0");
		assertEquals(0, classToTest.multiply(0, 10), "0 x 10 equals 0");
		assertEquals(0, classToTest.multiply(0, 0), "0 x 0 equals 0");
	}
}
