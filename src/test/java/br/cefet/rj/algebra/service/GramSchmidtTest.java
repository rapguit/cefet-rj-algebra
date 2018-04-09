package br.cefet.rj.algebra.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.cefet.rj.algebra.model.Result;

public class GramSchmidtTest {

	private GramSchmidt m;

	@Before
	public void setUp() throws Exception {
		m = new GramSchmidt();
	}

	@Test
	public void calculate_simple() {
		double input[][] = new double[][] {
				{  1.0,  0.0,  0.0 },
				{  1.0,  1.0,  0.0 },
				{  1.0,  1.0,  1.0 }
		};

		Result result = m.calculateResult(input);
		assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
	}

	private double[] expectedSimpleSolution() {
		return null;
	}

}