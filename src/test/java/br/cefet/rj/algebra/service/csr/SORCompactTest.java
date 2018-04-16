package br.cefet.rj.algebra.service.csr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.cefet.rj.algebra.model.CSRData;
import br.cefet.rj.algebra.model.Result;

@Ignore
public class SORCompactTest {

	private SORCompact m;
	private CSRData dataSimple;

	@Before
	public void setUp() throws Exception {
		m = new SORCompact(Integer.MAX_VALUE, 0.0, 1.2);
		dataSimple = simpleInput();
	}

	@Test
	public void calculate_simple() {
		Result result = m.calculateResult(dataSimple);
		assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
	}

	private double[] expectedSimpleSolution() {
		return new double[] { 1.0, 2.0, 2.0 };
	}

	public CSRData simpleInput() throws Exception {

//				{  2.0, -1.0,  0.0, 0.0 },
//				{ -1.0,  2.0, -1.0, 1.0 },
//				{  0.0, -1.0,  2.0, 2.0 }

		CSRData input = new CSRData(3, 4);
		input.register(0, 0, 2.0);
		input.register(0, 1, -1.0);
		input.register(1, 0, -1.0);
		input.register(1, 1, 2.0);
		input.register(1, 2, -1.0);
		input.register(1, 3, 1.0);
		input.register(2, 1, -1.0);
		input.register(2, 2, 2.0);
		input.register(2, 3, 2.0);

		return input;
	}
}