package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
		assertThat(result.getMatrixRegister().get("M_U"), equalTo(expectedSimpleMatrix()));
		assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
	}

	@Test
	public void calculate_simple2() {
		double input[][] = new double[][] {
				{  1.0,  0.0,  1.0 },
				{ -2.0,  1.0,  0.0 },
				{  0.0,  1.0, -1.0 }
		};

		Result result = m.calculateResult(input);
		assertThat(result.getMatrixRegister().get("M_U"), equalTo(expectedSimpleMatrix2()));
		assertThat(result.getMatrixRegister().get("M_W"), equalTo(expectedSimpleMatrixW2()));
		assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
	}

	private Double[][] expectedSimpleMatrix() {
		return new Double[][]{
				{1.0, -0.6666666666666666,  0.0},
				{1.0,  0.33333333333333337, -0.5},
				{1.0,  0.33333333333333337,  0.5}
		};
	}

	private Double[][] expectedSimpleMatrix2() {
		return new Double[][]{
				{ 1.0,  0.4,  1.0},
				{-2.0,  0.19999999999999996,  0.5},
				{ 0.0,  1.0, -0.5}
		};
	}

	private Double[][] expectedSimpleMatrixW2() {
		return new Double[][]{
				{ 0.4472135954999579, 0.36514837167011077, 0.8164965809277261},
				{-0.8944271909999159,  0.18257418583505533,  0.4082482904638631},
				{ 0.0,  0.9128709291752769, -0.4082482904638631}
		};
	}

	private double[] expectedSimpleSolution() {
		return null;
	}

}