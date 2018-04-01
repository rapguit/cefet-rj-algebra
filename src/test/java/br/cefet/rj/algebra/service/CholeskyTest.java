package br.cefet.rj.algebra.service;

import org.junit.Before;
import org.junit.Test;

import br.cefet.rj.algebra.model.Result;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CholeskyTest {

	private Cholesky m;

	@Before
	public void setUp() throws Exception {
		m = new Cholesky();
	}

	@Test
	public void calculate_simple() {
		double input[][] = new double[][] {
				{  4.0,  2.0, -4.0,  0.0 },
				{  2.0, 10.0,  4.0,  6.0 },
				{ -4.0,  4.0,  9.0,  5.0 }
		};

		Result result = m.calculate(input);

		Map<String, Double[][]> matrixRegister = result.getMatrixRegister();
		assertThat(matrixRegister.get("G"), equalTo(expectedSimpleMatrix()));
		assertThat(matrixRegister.get("GT"), equalTo(expectedTransposedSimpleMatrix()));
		assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
	}

	@Test
	public void calculate() {
		double input[][] = new double[][] {
				{ 9.0, 3.0, 2.0,  2.0, 10.0 },
				{ 3.0, 1.0, 1.0,  2.0, 15.0 },
				{ 2.0, 1.0, 3.0,  6.0,  9.0 },
				{ 2.0, 2.0, 6.0,  9.0,  3.0 }
		};

		Result result = m.calculate(input);

//		assertThat(result.getMatrixRegister().get("G"), equalTo(expectedMatrix()));
//		assertThat(result.getSolution(), equalTo(expectedSolution()));
	}

	private double[] expectedSimpleSolution() {
		return new double[] { 1.0, 0.0, 1.0 };
	}

	private double[][] expectedSimpleMatrix() {
		return new double[][]{
				{  2.0, 0.0, 0.0 },
				{  1.0, 3.0, 0.0 },
				{ -2.0, 2.0, 1.0 }
		};
	}

	private double[][] expectedTransposedSimpleMatrix() {
		return new double[][]{
				{ 2.0, 1.0, -2.0 },
				{ 0.0, 3.0,  2.0 },
				{ 0.0, 0.0,  1.0 }
		};
	}

	private double[] expectedSolution() {
		return new double[] { -1.0, 0.0, 1.0, 2.0 };
	}

	private double[][] expectedMatrix() {
		return new double[][]{
				{  2.0, 0.0, 0.0, 0.0 },
				{  3.0, 8.5, 0.0, 0.0 },
				{ -2.0, 5.0, 2.4, 0.0 },
				{  1.0, 2.0, 0.0, 0.3 }
		};
	}
}