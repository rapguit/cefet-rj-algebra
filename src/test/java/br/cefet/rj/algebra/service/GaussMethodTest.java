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
    public void calculate_simple() throws Exception {
        double input[][] = new double[][] {
                {  1.0,  2.0,  1.0,  3.0 },
                {  2.0, -3.0, -1.0,  4.0 },
                {  3.0, -1.0, -2.0,  1.0 }
        };

        Result result = m.calculateResult(input);

        assertThat(result.getMatrixRegister().get("Result"), equalTo(expectedSimpleMatrix()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    @Test
    public void calculate() throws Exception {
        double input[][] = new double[][] {
                {  1.0,  1.0,  1.0,  1.0, 1.0,   2.0 },
                {  1.0, -1.0, -1.0, -1.0, 1.0,   4.0 },
                {  1.0,  2.0, -1.0,  0.5, 2.0,   6.0 },
                { -1.0,  1.0,  1.0,  3.0,-1.0,  -6.0 },
                {  0.5,  1.0,  1.0, -0.5,-7.0, -13.0 }
        };

        Result result = m.calculateResult(input);

        assertThat(result.getMatrixRegister().get("Result"), equalTo(expectedMatrix()));
        assertThat(result.getSolution(), equalTo(expectedSolution()));
    }

    private double[] expectedSolution() {
        return new double[] { 1.0, 0.5, -0.5, -1.0, 2.0 };
    }

    private double[][] expectedMatrix() {
        return new double[][] {
                { 1.0,  1.0,  1.0,  1.0, 1.0,   2.0 },
                { 0.0, -2.0, -2.0, -2.0, 0.0,   2.0 },
                { 0.0,  0.0, -3.0, -1.5, 1.0,   5.0 },
                { 0.0,  0.0,  0.0,  2.0, 0.0,  -2.0 },
                { 0.0,  0.0,  0.0,  0.0,-7.5, -15.0 }
        };
    }

    private double[] expectedSimpleSolution() {
        return new double[] { 2.0, -1.0, 3.0 };
    }

    private double[][] expectedSimpleMatrix() {
        return new double[][] {
                { 1.0, 2.0, 1.0, 3.0 },
                { 0.0,-7.0,-3.0,-2.0 },
                { 0.0, 0.0,-2.0,-6.0 },
        };
    }
}