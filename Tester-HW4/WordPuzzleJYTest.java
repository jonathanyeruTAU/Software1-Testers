package il.ac.tau.cs.sw1.ex4;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.transform.Templates;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.validator.PublicClassValidator;

class WordPuzzleJYTest {
	@Nested class myFuncs{
		public String hello = "hello";
		public char[] puzzle = {'h', '_', '_', '_', 'o'};
		@Test void testLeftPad() {
			assertEquals(WordPuzzle.leftPad("1001", '0', 3), "0001001");
			assertEquals(WordPuzzle.leftPad("1", '0', 0), "1");
		}
		@Test void testChoose() {
			assertEquals(WordPuzzle.choose(10, 5), 252);
			assertEquals(WordPuzzle.choose(5, 3), 10);
		}
		@Test void testToTemplate() {
			assertArrayEquals(WordPuzzle.toTemplate("1001", 5), new boolean[]
					{false, true, false, false, true});
		}
		@Test void testCountOnes() {
			assertEquals(WordPuzzle.countOnes(new boolean[]
					{false, true, false, false, true}), 2);
		}
		@Test void testGetLeftChars() {
			assertArrayEquals(WordPuzzle.getCorrecrCharsLeft(hello, puzzle), new char[] {'e', 'l'});
		}
	}

	// didn't do one for createTemplate, i think they covered that one well
	@Nested class TestcheCkLegalTemplate{
		public String hello = "hello";
		// {'h', 'e', 'l','l','o'}
		@Test void testALLFalse() {
			assertFalse(WordPuzzle.checkLegalTemplate(this.hello, new boolean[] 
					{false, false, false, false, false}));
		}
		// {_,_,_,_,_}
		@Test void testAllTrue(){
			assertFalse(WordPuzzle.checkLegalTemplate(this.hello, new boolean[] 
					{true, true, true, true, true}));
		}
		// {'h', 'e', '_', '_'. 'o'}
		@Test void testGoodPuazzleLetters() {
			assertTrue(WordPuzzle.checkLegalTemplate(this.hello, new boolean[]
					{false, false, true, true, false}));
		}
		// {'e', '_', 'a', 'b', 'e'}
		@Test void testBadLetters() {
			assertFalse(WordPuzzle.checkLegalTemplate("eeabe", new boolean[]
					{false, true, false, false, false}));
		}
		@Test void testGoodBadKeep() {
			// k_ep
			assertFalse(WordPuzzle.checkLegalTemplate("keep", new boolean[]
					{false, true, false, false}));
			// k__p
			assertTrue(WordPuzzle.checkLegalTemplate("keep", new boolean[]
					{false, true, true, false}));
		}
		@Test void testDiffLengths() {
			assertFalse(WordPuzzle.checkLegalTemplate(this.hello, new boolean[]
					{false, false, true}));
		}	
	}
	@Nested class TestGetAllLegalTemplates {
		public String hello = "hello";
		public boolean[][] empty = {};
		
		@Test void testK0() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 0), empty);
		}
		@Test void testK1() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 1), new boolean[][]
					{new boolean[] {false, false, false,false, true}, //00001
				new boolean[] {false, true, false, false, false}, //01000
				new boolean[] {true, false, false, false, false}}); // 10000
		}
		@Test void testK2() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 2), new boolean[][]
					{new boolean[] {false, false, true, true, false},     // 00110
					new boolean[] {false, true, false, false, true},      // 01001
					new boolean[] {true, false, false, false, true},      // 10001
					new boolean[] {true, true, false, false, false}});    // 11000    
		}
		@Test void testK3() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 3), new boolean[][]
					{new boolean[] {false, false, true, true, true}, // 00111
					new boolean[] {false, true, true, true, false}, // 01110
					new boolean[] {true, false, true, true, false}, // 10110
					new boolean[] {true, true, false, false, true}}); // 11001
		}
		@Test void testK4() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 4), new boolean[][]
					{new boolean[] {false, true, true, true, true}, //01111
					new boolean[] {true, false, true, true, true},  //10111
					new boolean[] {true, true, true, true, false}}); // 11110
		}
		@Test void testK5() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates(hello, 5), empty);
		}
		@Test void testAll() {
			assertArrayEquals(WordPuzzle.getAllLegalTemplates("abc", 1), new boolean[][]
					{new boolean[] {false, false, true},
					new boolean[] {false, true, false},
					new boolean[] {true, false, false}
					});
		}
	}
	@Nested class TestApplyGuess{
		public String hello = "hello";
		public char[] puzzle = {'h', '_', '_', '_', 'o'};
		
		@Test void testAlreadyThere() {
			assertEquals(WordPuzzle.applyGuess('h', hello, puzzle), 0);
		}
		@Test void testOneLetter() {
			assertEquals(WordPuzzle.applyGuess('e', hello, puzzle), 1);
		}
		@Test void testTwo() {
			assertEquals(WordPuzzle.applyGuess('l', hello, puzzle), 2);
		}
	}
	

}
