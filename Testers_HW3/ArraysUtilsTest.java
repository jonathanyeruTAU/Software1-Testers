import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

class ArraysUtilsTest {
	@Nested
	class TestShifted {
		private int[] testArr = {1, 2, 3, 4, 5};
		@Before
		void remakeArray() {
			this.testArr = new int[] {1, 2, 3, 4, 5};
		}
		@Test
		void testRightNeg() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, -1, 'R'), new int[] {2, 3, 4, 5, 1});
		}
		@Test
		void testRightPos() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, 1, 'R'), new int[] {5, 1, 2, 3, 4});

		}
		@Test
		void testAnotherLetter() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, 1, 'r'), this.testArr);
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, -2, 'g'), this.testArr);
		}
		@Test
		void testLeftPos() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, 3, 'L'), new int[] {4, 5, 1, 2, 3});
		}
		@Test
		void testLeftNeg() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, -3, 'L'), new int[] {3, 4, 5, 1, 2});
		}
		@Test
		void testEasyMod() {
			int[] another = {0, 8, 9, 5, 6};
			int[] expected = {8, 9, 5, 6, 0};
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(another, 6, 'L'), expected);
		}
		@Test
		void testEmpty() {
			int [] empty = new int [] {};
			assertTrue(ArrayUtils.shiftArrayCyclic(empty, -3, 'L') == empty);
		}
		@Test
		void testHardModLeft() {
			// if the tester keeps on running you didn't take mod into account!
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, 100001, 'L'), new int[] {2, 3, 4, 5, 1});
		}
		@Test
		void testHardModRight() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, 100001, 'R'), new int[] {5, 1, 2, 3, 4});
		}
		@Test
		void testHardModNegLeft() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, -100001, 'L'), new int[] {5, 1, 2, 3, 4});
		}
		@Test
		void testHardModNegRight() {
			assertArrayEquals(ArrayUtils.shiftArrayCyclic(this.testArr, -100001, 'R'), new int[] {2, 3, 4, 5, 1});
		}
	}
	
	
	/////////////////////////////////////////// tests for the shortest path now/////////////////////////////////
		@Nested
		class TestFindPath {
			@Test 
			void testNoEdges() {
				assertEquals(ArrayUtils.findShortestPath(new int[][] {{0,0,0}, {0,0,0}, {0,0,0}}, 0, 1), -1);
			}
			@Test
			void testSelfLove() {
				assertEquals(ArrayUtils.findShortestPath(new int[][] {{0,0,0}, {0,0,0}, {0,0,0}}, 1, 1), 0);
			}
			@Test
			void testNormalCase() {
				int[][] graph = {{0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}, {1, 0, 0, 0}};
				assertEquals(ArrayUtils.findShortestPath(graph, 0, 2), 2);
			}
			@Test
			void testManyPaths() {
				int[][] graph = {{0, 1, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 1, 0}};
				assertEquals(ArrayUtils.findShortestPath(graph, 0, 2), 2);
			}
			@Test
			void testInfiniteLoopNoPath() {
				int[][] graph = {{0, 1, 0, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}};
				assertEquals(ArrayUtils.findShortestPath(graph, 0, 3), -1);
			}
			@Test
			void testInfiniteLoopWithPath() {
				int[][] graph = {{0, 1, 0, 0}, {0, 0, 1, 1}, {1, 0, 0, 0}, {0, 0, 0, 0}};
				assertEquals(ArrayUtils.findShortestPath(graph, 2, 3), 3);
			}	
	}
}
