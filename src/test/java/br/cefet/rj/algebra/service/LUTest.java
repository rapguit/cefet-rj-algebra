package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

public class LUTest {

    private LU m;

    @Before
    public void setUp() throws Exception {
        m = new LU();
    }

    @Test
    public void calculate() {
        double input[][] = new double[][] {
                {  1.0,  1.0,  1.0,  1.0, 1.0,   2.0 },
                {  1.0, -1.0, -1.0, -1.0, 1.0,   4.0 },
                {  1.0,  2.0, -1.0,  0.5, 2.0,   6.0 },
                { -1.0,  1.0,  1.0,  3.0,-1.0,  -6.0 },
                {  0.5,  1.0,  1.0, -0.5,-7.0, -13.0 }
        };

        Result result = m.calculate(input);
//        assertThat(result.getSolution(), equalTo(expectedSolution()));
    }

    @Test
    public void calculate_simple() {
        double input[][] = new double[][] {
                {  5.0,  2.0,  1.0,  0.0 },
                {  3.0,  1.0,  4.0, -7.0 },
                {  1.0,  1.0,  3.0, -5.0 }
        };

        Result result = m.calculate(input);
//        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    private double[] expectedSolution() {
        return new double[] { 1.0, 0.5, -0.5, -1.0, 2.0 };
    }

    private double[] expectedSimpleSolution() {
        return new double[] { 0.0, 1.0, -2.0 };
    }
}