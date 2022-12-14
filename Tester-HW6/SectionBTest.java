package il.ac.tau.cs.sw1.hw6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class SectionBTest {
	
	@Test
	void testContins() {
		assertTrue(SectionB.contains(new int[] {1, 100, 2, 36}, 2));
		assertTrue(SectionB.contains(new int[] {1, 100, 2, 36}, 1));
	}
	
	@Test
	void testReversed() {
		assertEquals("abc", SectionB.reverse("cba"));
	}
	
	@Test
	void testGuess() {
		int[] testArray = new int[] {1, 1, 1, 1, 1, 2, 3, 4};
		int[] ret = SectionB.guess(testArray);
		assertNotEquals(ret, new int[] {1, 1, 1, 1, 1, 2, 3, 4});
		for (int i = 1; i < 5; i++) {
			assertTrue(SectionB.contains(testArray, i));
		}
	}

}
