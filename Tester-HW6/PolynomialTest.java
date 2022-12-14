package il.ac.tau.cs.sw1.hw6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PolynomialTest {

	public Polynomial emptyPolynomial = new Polynomial();
	public Polynomial bigPolynomial = new Polynomial(new double[] {1, 2, 3, 0, 1});
	public Polynomial smallPolynomial = new Polynomial(new double[] {1, 2}); 
	
	@Before
	void setup() {
		this.emptyPolynomial = new Polynomial();
		this.bigPolynomial = new Polynomial(new double[] {1, 2, 3, 0, 1});
		this.smallPolynomial = new Polynomial(new double[] {1, 2}); 
	}
	
	@Test
	void testAdd() {
		// we didn't define an equals method so we will do with that.
		assertEquals(bigPolynomial.adds(smallPolynomial).computePolynomial(1), 
				new Polynomial(new double[] {2, 4, 3, 0, 1}).computePolynomial(1));
		
		assertEquals(smallPolynomial.adds(bigPolynomial).computePolynomial(1),
				new Polynomial(new double[] {2, 4, 3, 0, 1}).computePolynomial(1));
		
		assertEquals(bigPolynomial.adds(emptyPolynomial).computePolynomial(1), 
				bigPolynomial.computePolynomial(1));
	}
	
	@Test
	void testMultiply() {
		assertEquals(bigPolynomial.multiply(2).computePolynomial(1), 
				new Polynomial(new double[] {2, 4, 6, 0, 2}).computePolynomial(1));
		assertEquals(emptyPolynomial.multiply(2).computePolynomial(1), 
				emptyPolynomial.computePolynomial(1));
	}
	
	@Test
	void testGetDegree() {
		assertEquals(bigPolynomial.adds(smallPolynomial).getDegree(), 4);
		assertEquals(emptyPolynomial.getDegree(), 0);
	}
	
	@Test
	void testGetCoefficient() {
		assertEquals(bigPolynomial.getCoefficient(0), 1);
		assertEquals(bigPolynomial.getCoefficient(1), 2);
		assertEquals(bigPolynomial.getCoefficient(2), 3);
		assertEquals(bigPolynomial.getCoefficient(3), 0);
		assertEquals(bigPolynomial.getCoefficient(4), 1);
		
		// the real test
		assertEquals(emptyPolynomial.getCoefficient(5), 0);
	}
	
	@Test
	void testSetCoefficientAndGetDegree() {
		smallPolynomial.setCoefficient(4, 1);
		smallPolynomial.setCoefficient(4, 0);
		assertEquals(1, smallPolynomial.getDegree());
	}
	
	@Test
	void testDerivative() {
		assertEquals(bigPolynomial.getFirstDerivation().computePolynomial(1), 
				new Polynomial(new double[] {2, 3, 0, 1}).computePolynomial(1));
		
		assertEquals(emptyPolynomial.getFirstDerivation().computePolynomial(1),
				0);
		
		assertEquals(smallPolynomial.getFirstDerivation().computePolynomial(1), 2);
	}
	
	@Test
	void testIsExtrime() {
		Polynomial x4 = new Polynomial(new double[] {0, 0, 0, 0, 1});
		assertTrue(x4.isExtrema(0));
		assertFalse(x4.isExtrema(1));
	}

}
