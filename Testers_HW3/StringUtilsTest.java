import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

class StringUtilsTest {
	
	@Nested
	class FindShhortestSequenceTest{
		@Test
		void testEndSeq() {
			assertEquals(StringUtils.findSortedSequence("to be or not to be"), "not to");
		}
		@Test
		void testZoo() {
			assertEquals(StringUtils.findSortedSequence("my mind is an empty zoo"), "an empty zoo");
		}
		@Test
		void testEmpty() {
			assertEquals(StringUtils.findSortedSequence(""), "");
		}
		@Test
		void testAllIncreasingSeq() {
			assertEquals(StringUtils.findSortedSequence("andy bought candy"), "andy bought candy");
		}
		@Test
		void testRepeatedWord() {
			assertEquals(StringUtils.findSortedSequence("life is not not not fair"), "is not not not");
		}
		@Test
		void testLexOrder() {
			assertEquals(StringUtils.findSortedSequence("abc acb"), "abc acb");
		}
		@Test
		void testLexOrderSwitch() {
			assertEquals(StringUtils.findSortedSequence("acb abc"), "abc");
		}
		@Test
		void testTwoOptions() {
			assertEquals(StringUtils.findSortedSequence("y z a b"), "a b");
		}
		@Test
		void testCorrectAtStart() {
			assertEquals(StringUtils.findSortedSequence("r y z a b"), "r y z");
		}
		@Test
		void testShorterWord() {
			assertEquals(StringUtils.findSortedSequence("abc ab"), "ab");
		}
		@Test
		void testSwitchShorter() {
			assertEquals(StringUtils.findSortedSequence("ab abc"), "ab abc");
		}
	}

	@Nested
	class IsEditDistanceOneTest{
		@Test
		void testSameWord() {
			assertTrue(StringUtils.isEditDistanceOne("x", "x"));
		}
		@Test
		void testTwoDistance() {
			assertFalse(StringUtils.isEditDistanceOne("dog", "god"));
		}
		@Test
		void testAddChar() {
			assertTrue(StringUtils.isEditDistanceOne("man", "main"));
		}
		@Test
		void testAddStart() {
			assertTrue(StringUtils.isEditDistanceOne("ab", "cab"));
		}
		@Test
		void testAddStartA() {
			assertTrue(StringUtils.isEditDistanceOne("cab", "ab"));
		}
		@Test
		void testLength() {
			assertFalse(StringUtils.isEditDistanceOne("israel", "is"));
		}
		@Test
		void testChangeChar() {
			assertTrue(StringUtils.isEditDistanceOne("dog", "gog"));
		}
		@Test
		void testAddEnd() {
			assertTrue(StringUtils.isEditDistanceOne("ab", "abb"));
		}
		@Test
		void testMoreThanTWO() {
			assertFalse(StringUtils.isEditDistanceOne("dog", "cat"));
		}
	}

}
