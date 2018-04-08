package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import static br.cefet.rj.algebra.util.ArraysUtils.unbox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SORTest {

    private SOR m;

    @Before
    public void setUp() throws Exception {
        m = new SOR(Integer.MAX_VALUE, 0.0, 1.4);
    }

    @Test
    public void calculate_simple_saidel() {
        m = new SOR(Integer.MAX_VALUE, 0.0, 1.0);
        double input[][] = new double[][] {
                { 10.0,  2.0,  1.0,  7.0 },
                {  1.0,  5.0,  1.0, -8.0 },
                {  2.0,  3.0, 10.0,  6.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("X_1"), equalTo(expectedSimpleSolutionSeidelX1()));
        assertThat(result.getVectorRegister().get("X_2"), equalTo(expectedSimpleSolutionSeidelX2()));
        assertThat(result.getVectorRegister().get("X_3"), equalTo(expectedSimpleSolutionSeidelX3()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolutionSeidel()));
    }

    @Test
    public void calculate_simple() {
        m = new SOR(Integer.MAX_VALUE, 0.0, 1.2);
        double input[][] = new double[][] {
                {  2.0, -1.0,  0.0, 0.0 },
                { -1.0,  2.0, -1.0, 1.0 },
                {  0.0, -1.0,  2.0, 2.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    @Test
    public void calculate_simple2() {
        m = new SOR(Integer.MAX_VALUE, 0.0, 1.1);
        double input[][] = new double[][] {
                {  2.0,  0.0,  1.0, 6.0 },
                {  0.0,  2.0,  1.0, 3.0 },
                {  0.0,  1.0,  2.0, 4.5 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("X_1"), equalTo(expectedSimpleSolution2X1()));
        assertThat(result.getVectorRegister().get("X_2"), equalTo(expectedSimpleSolution2X2()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution2()));
    }

    @Test
    public void calculate_simple_max_1_interaction_seidel() {
        m = new SOR(4, 0.0, 1.0);
        double input[][] = new double[][] {
                { 10.0,  2.0,  1.0,  7.0 },
                {  1.0,  5.0,  1.0, -8.0 },
                {  2.0,  3.0, 10.0,  6.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("X_1"), equalTo(expectedSimpleSolutionSeidelX1()));
        assertThat(result.getVectorRegister().get("X_2"), equalTo(expectedSimpleSolutionSeidelX2()));
        assertThat(result.getVectorRegister().get("X_3"), equalTo(expectedSimpleSolutionSeidelX3()));
        assertThat(result.getSolution(), equalTo(unbox(expectedSimpleSolutionSeidelX3())));
    }

    private Double[] expectedSimpleSolutionSeidelX1() {
        return new Double[]{0.7, -1.74, 0.982};
    }

    private Double[] expectedSimpleSolutionSeidelX2() {
        return new Double[]{0.9498, -1.9863600000000003, 1.005948};
    }

    private Double[] expectedSimpleSolutionSeidelX3() {
        return new Double[]{0.9966772, -2.0005250400000003, 1.000822072};
    }

    private double[] expectedSimpleSolutionSeidel() {
        return new double[] { 1.0, -2.0, 1.0 };
    }


    private double[] expectedSimpleSolution() {
        return new double[] { 1.0, 2.0, 2.0 };
    }

    private Double[] expectedSimpleSolution2X1() {
        return new Double[]{3.3000000000000003, 1.6500000000000001, 1.5675};
    }

    private Double[] expectedSimpleSolution2X2() {
        return new Double[]{2.107875, 0.622875, 1.97566875};
    }

    private double[] expectedSimpleSolution2() {
        return new double[] { 2.0, 0.5, 2.0 };
    }

}