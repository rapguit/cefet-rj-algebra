package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import static br.cefet.rj.algebra.util.ArraysUtils.unbox;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConjugatedGradientTest {

    private ConjugatedGradient m;

    @Before
    public void setUp() throws Exception {
        m = new ConjugatedGradient(100, 0.0);
    }

    @Test
    public void calculate_simple() {
        double input[][] = new double[][] {
                { 10.0,  1.0,  0.0, 11.0 },
                {  1.0, 10.0,  1.0, 11.0 },
                {  0.0,  1.0, 10.0,  1.0 }
        };

        Result result = m.calculateResult(input);
        assertThat(result.getVectorRegister().get("V_1"), equalTo(expectedSimpleSolutionV1()));
        assertThat(result.getVectorRegister().get("V_2"), equalTo(expectedSimpleSolutionV2()));
        assertThat(result.getVectorRegister().get("V_3"), equalTo(expectedSimpleSolutionV3()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    private Double[] expectedSimpleSolutionV1() {
        return new Double[]{0.9922048997772828, 0.9922048997772828, 0.09020044543429843};
    }

    private Double[] expectedSimpleSolutionV2() {
        return new Double[]{1.0044667184068428, 0.9954214447518497, 0.0008624196688729047};
    }

    private Double[] expectedSimpleSolutionV3() {
        return new Double[]{1.0, 1.0, 0.000000000000000011709383462843448};
    }

    private double[] expectedSimpleSolution() {
        return unbox(expectedSimpleSolutionV3());
    }
}