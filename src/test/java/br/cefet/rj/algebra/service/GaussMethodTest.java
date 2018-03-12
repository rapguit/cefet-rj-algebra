package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GaussMethodTest {

    private GaussMethod m;

    @Before
    public void setUp() throws Exception {
        m = new GaussMethod();
    }

    @Test
    public void calculate() throws Exception {
        double input[][] = new double[][] {
                {-1.0, 3.0, 5.0,  2.0, 10.0 },
                { 1.0, 9.0, 8.0,  4.0, 15.0 },
                { 0.0, 1.0, 0.0,  1.0,  2.0 },
                { 2.0, 1.0, 1.0, -1.0, -3.0 }
        };

        Result result = m.calculate(input);

        assertThat(result.getMatrixRegister().get("Result"), equalTo(expectedMatrix()));
//        assertThat(result.getSolution(), equalTo(expectedSolution()));
    }

    private double[] expectedSolution() {
        return new double[] { -1.0, 0.0, 1.0, 2.0 };
    }

    private double[][] expectedMatrix() {
        return new double[][] {
                { 2.0, 1.0, 1.0, -1.0, -3.0 },
                { 0.0, 8.5, 7.5,  4.5, 16.5 },
                { 0.0, 0.0, 2.4117647058823533, -0.3529411764705881, 1.7058823529411766 },
                { 0.0, 0.0, 0.0, 0.3414634146341464, 0.6829268292682926 }
        };
    }
}