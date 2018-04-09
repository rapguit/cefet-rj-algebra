package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PowTest {

    private Pow m;

    @Before
    public void setUp() throws Exception {
        m = new Pow(300, 0.01);
    }

    @Test
    public void calculate_simple() {
        double input[][] = new double[][] {
                {  3.0,  0.0,  1.0 },
                {  2.0,  2.0,  2.0 },
                {  4.0,  2.0,  5.0 }
        };

        Result result = m.calculateResult(input);
        Map<String, Double[]> reg = result.getVectorRegister();

        assertThat(reg.get("Lambda_1"), equalTo(expectedSimpleSolutionLb1()));
        assertThat(reg.get("Lambda_2"), equalTo(expectedSimpleSolutionLb2()));
        assertThat(reg.get("Lambda_3"), equalTo(expectedSimpleSolutionLb3()));
        assertThat(result.getSolution(), equalTo(expectedSimpleSolution()));
    }

    private double[] expectedSimpleSolution() {
        return new double[] { 0.2501581665432396,  0.5000024521944688, 1.0 };
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