package br.cefet.rj.algebra.service;

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
                {  2.0,  3.0,  -4.0,  4.0,  -1.0 },
                { -4.0, -7.0,  11.0, -6.0,   5.0 },
                {  6.0, 11.0, -20.0, 10.0, -13.0 },
                { -2.0, -7.0,  22.0, -6.0,  25.0 }
        };

//        Double[][] result = m.calculate(input).getMatrixRegister().get("L");
//        assertThat(result, equalTo(expected()));
    }

    private Double[][] expected() {
        return new Double[][] {
                {  1.0,  0.0,   0.0,  0.0,  -1.0 },
                { -2.0,  1.0,   0.0,  0.0,   5.0 },
                {  3.0, -2.0,   1.0,  0.0, -13.0 },
                { -1.0,  4.0,  -3.0,  1.0,  25.0 }
        };
    }
}