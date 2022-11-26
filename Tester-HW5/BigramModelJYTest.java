package il.ac.tau.cs.sw1.ex5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;

class BigramModelJYTest {
	public BigramModel bigramModel = new BigramModel();
	public static final String REAL_TEST_FILENAME = "resources/hw5/tester.txt";
	public static final String ALL_YOU_NEED_FILENAME = "resources/hw5/all_you_need.txt";
	public static final String ALL_YOU_NEED_MODEL_DIR = "resources/hw5/all_you_need_model";
	
	public BigramModelJYTest() throws IOException {
		bigramModel.initModel(ALL_YOU_NEED_FILENAME);
	}
	
	@Nested class MyTests{
		@Test
		void regexCheckChar() {
			assertTrue("1check".matches("(.*)[a-zA-Z](.*)"));
			assertTrue("1111111a".matches("(.*)[a-zA-Z](.*)"));
			assertTrue("man's".matches("(.*)[a-zA-Z](.*)"));
			assertFalse("1111".matches("(.*)[a-zA-Z](.*)"));
		}
		@Test
		void regexDigit() {
			assertTrue("1111".matches("(.*)\\d(.*)"));
			assertTrue("1111&".matches("(.*)[^0-9](.*)"));
			assertTrue("+1111".matches("(.*)[^0-9](.*)"));
		}
		@Test 
		void testToLower() {
			assertEquals("Check1".toLowerCase(), "check1");
		}
		@Test 
		void testInArr() {
			int[][] count = new int[3][1];
			assertTrue(count[0][0] == 0);
		}
	}
	@Nested class Q1Q2 {
		@Test
		void TestQ1() throws IOException {
			assertArrayEquals(bigramModel.buildVocabularyIndex(REAL_TEST_FILENAME), 
					new String[]{"aaa", "aa1", BigramModel.SOME_NUM, "1984a", 
							"https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley".toLowerCase(),
							"man", "man's"});
		}
		@Test
		void TestQ2() throws IOException {
			int [] zeros = new int[7];
			String[] voc = bigramModel.buildVocabularyIndex(REAL_TEST_FILENAME);
			assertArrayEquals(bigramModel.buildCountsArray(REAL_TEST_FILENAME, voc), new int[][] {
				new int[] {0, 1, 0, 0, 0, 0, 0},
				new int[] {0, 0, 2, 0, 0, 0, 0},
				zeros,
				zeros,
				zeros,
				new int[] {0, 0, 0, 0, 0, 0, 1},
				zeros
			});
		}
	}
	// run this when you want to save files 
	@Nested class SaveLoadTest{
		@Before
		void init() throws IOException {
			bigramModel.initModel(ALL_YOU_NEED_FILENAME);
		}
		@Test 
		void testSaveModel() throws IOException {
			// for me, the eclipse doesn't show the new files so i need to see them from the file explorer
			bigramModel.saveModel("resources/hw5/all_you_need_test");
		}
		@Test
		void loadModel() throws IOException {
			// they covered the counts
			bigramModel.loadModel(ALL_YOU_NEED_MODEL_DIR);
			assertArrayEquals(bigramModel.mVocabulary, new String[]{"love", "all", "you", "need", "is"});
		}
	}
	@Nested class WordsInClass{
		@Test
		void testIsWordIn() {
			assertEquals(bigramModel.getWordIndex("love"), 0);
			assertEquals(bigramModel.getWordIndex("is"), 4);
			assertEquals(bigramModel.getWordIndex("a"), -1);
		}
	}
	
	@Nested class MostFrequantTest{
		@Test
		void testMostFrequent() throws IOException {
			bigramModel.initModel(REAL_TEST_FILENAME);
			assertEquals(bigramModel.getMostFrequentProceeding(BigramModel.SOME_NUM), null);
			assertEquals(bigramModel.getMostFrequentProceeding("aaa"), "aa1");
		}
	}
	
}
