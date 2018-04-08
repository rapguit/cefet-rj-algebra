package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import static br.cefet.rj.algebra.util.ArraysUtils.unbox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SeidelTest {

    private Seidel m;

    @Before
    public void setUp() throws Exception {
        m = new Seidel(Integer.MAX_VALUE, 0.0);
    }

    @Test
    public void calculate_simple() {
        double input[][] = new double[][] {
                { 10.0,  2.0,  1.0,  7.0 },
                {  1.0,  5.0,  1.0, -8.0 },
                {  2.0,  3.0, 10.0,  6.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("X_1"), equalTo(expectedSimpleSolutionX1()));
        assertThat(result.getVectorRegister().get("X_2"), equalTo(expectedSimpleSolutionX2()));
        assertThat(result.getVectorRegister().get("X_3"), equalTo(expectedSimpleSolutionX3()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    @Test
    public void calculate_simple_max_1_interaction() {
        m = new Seidel(4, 0.0);
        double input[][] = new double[][] {
                { 10.0,  2.0,  1.0,  7.0 },
                {  1.0,  5.0,  1.0, -8.0 },
                {  2.0,  3.0, 10.0,  6.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("X_1"), equalTo(expectedSimpleSolutionX1()));
        assertThat(result.getVectorRegister().get("X_2"), equalTo(expectedSimpleSolutionX2()));
        assertThat(result.getVectorRegister().get("X_3"), equalTo(expectedSimpleSolutionX3()));
        assertThat(result.getSolution(), equalTo(unbox(expectedSimpleSolutionX3())));
    }

    private Double[] expectedSimpleSolutionX1() {
        return new Double[]{0.7, -1.74, 0.982};
    }

    private Double[] expectedSimpleSolutionX2() {
        return new Double[]{0.9498, -1.9863600000000003, 1.005948};
    }

    private Double[] expectedSimpleSolutionX3() {
        return new Double[]{0.9966772, -2.0005250400000003, 1.000822072};
    }

    private double[] expectedSimpleSolution() {
        return new double[] { 1.0, -2.0, 1.0 };
    }

}