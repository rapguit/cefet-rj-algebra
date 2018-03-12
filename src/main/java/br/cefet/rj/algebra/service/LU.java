package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.ArraysUtils;

import java.util.Map;

public class LU implements Method {

    private GaussMethod gauss;

    public LU() {
        this.gauss = new GaussMethod();
    }

    @Override
    public Result calculate(double[][] input) {
        Result result = new Result();
        Result gaussResult = gauss.calculate(input);

        double a[][] = ArraysUtils.copy(input);
        double u[][] = ArraysUtils.copyWithoutB(gaussResult.getMatrixRegister().get("Result"));
        double l[][] = calculateL(input, gaussResult.getMultipliers());
        double vectorB[] = ArraysUtils.vectorB(a);
        double vectorY[] = calculateY(l, vectorB);
        double vectorX[] = calculateX(u, vectorY);

        result.registerMatrix("L", l);
        result.registerMatrix("U", u);
        result.setSolution(vectorX);
        return result;
    }

    private double[] calculateX(double[][] u, double[] vectorY) {
        double vectorX[] = new double[vectorY.length];
        double up[][] = ArraysUtils.copy(u);

        for (int i = vectorY.length-1; i >= 0; i--) {
            for (int j = vectorY.length-1; j > i-1; j--) {
                if(j == i)up[i][j] = vectorY[j] / up[i][j];
                else up[i][j] *= vectorY[j];
            }
        }

        for (int i = 0; i < vectorY.length; i++) {
            for (int j = 0; j < i+1; j++) {
                vectorX[i] += up[i][j];
            }
        }

        return vectorX;
    }

    private double[] calculateY(double[][] l, double[] vectorB) {
        double vectorY[] = new double[vectorB.length];
        double lp[][] = ArraysUtils.copy(l);

        for (int i = 0; i < vectorB.length; i++) {
            for (int j = 0; j < i+1; j++) {
                lp[i][j] *= vectorB[j];
            }
        }

        for (int i = 0; i < vectorB.length; i++) {
            for (int j = 0; j < i+1; j++) {
                vectorY[i] += lp[i][j];
            }
        }

        return vectorY;
    }

    private double[][] calculateL(double[][] in, Map<String, Double> multipliers) {
        double input[][] = ArraysUtils.copyWithoutB(in);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (i==j) input[i][j] = 1;
                if (i<j) input[i][j] = 0;
                if (i>j) input[i][j] = multipliers.getOrDefault(key(i, j),0.0);
            }
        }
        return input;
    }

    private String key(int i, int j) {
        return String.valueOf(i) + String.valueOf(j);
    }

}
