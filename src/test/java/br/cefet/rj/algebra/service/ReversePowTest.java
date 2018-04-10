package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.junit.Before;
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
        assertThat(reg.get("Lambda_4"), equalTo(expectedSimpleSolutionLb4()));
        assertThat(reg.get("U_1"), equalTo(expectedSimpleSolution()));
        assertThat(result.getSolution(), equalTo(expectedSimpleLambda()));
    }

    private double[] expectedSimpleLambda() {
        return new double[] { 1.3384913002464642 };
    }
    private double[] expectedSimpleSolution() {
        return new double[] { 1.0,  -0.6615086997535358, 0.1420642336535995 };
    }

    private double[] expectedSimpleSolutionLb1() {
        return new double[] { 0.7023809523809523, 1.619047619047619, 0.369047619047619 };
    }

    private double[] expectedSimpleSolutionLb2() {
        return new double[] { 0.7376916868442293, 0.8249299719887955, 0.6190476190476192 };
    }

    private double[] expectedSimpleSolutionLb3() {
        return new double[] { 0.7454152339272689, 0.7616622200662947, 0.7232659813304974 };
    }

    private double[] expectedSimpleSolutionLb4() {
        return new double[] { 0.7471098241847849, 0.7505623202470671, 0.7442722555449234 };
    }
}