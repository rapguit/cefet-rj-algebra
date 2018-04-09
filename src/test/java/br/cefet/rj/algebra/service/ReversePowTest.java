package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReversePowTest {

    private ReversePow m;

    @Before
    public void setUp() throws Exception {
        m = new ReversePow(300, 0.01);
    }

    @Test
    @Ignore
    public void calculate_simple() {
        double input[][] = new double[][] {
                {  2.0,  1.0,  0.0 },
                {  2.0,  5.0,  3.0 },
                {  0.0,  1.0,  6.0 }
        };

        Result result = m.calculateResult(input);
        Map<String, Double[]> reg = result.getVectorRegister();

        assertThat(reg.get("Lambda_1"), equalTo(expectedSimpleSolutionLb1()));
        assertThat(reg.get("Lambda_2"), equalTo(expectedSimpleSolutionLb2()));
        assertThat(reg.get("Lambda_3"), equalTo(expectedSimpleSolutionLb3()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    private double[] expectedSimpleSolution() {
        return new double[] { 7.44437,  4.21809, 1.33754 };
    }

    private double[] expectedSimpleSolutionLb1() {
        return new double[] { 5.75, 7.000000000000001, 7.545454545454545 };
    }

    private double[] expectedSimpleSolutionLb2() {
        return new double[] { 6.608695652173913, 7.047619047619047, 7.120481927710843 };
    }

    private double[] expectedSimpleSolutionLb3() {
        return new double[] { 6.888157894736842, 7.020270270270269, 7.030456852791879 };
    }
}