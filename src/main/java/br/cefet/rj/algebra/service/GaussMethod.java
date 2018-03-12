package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.ArraysUtils;

public class GaussMethod implements Method {

    @Override
    public Result calculate(double[][] input) {
        Result result = new Result();
        double in[][] = ArraysUtils.copy(input);

        for (int i = 0; i <= in.length - 1; i++) {
            pivot(in, i);
            for (int j = i+1; j < in.length; j++) {
                if (in[j][i] != 0.0) {
                    double x = getFactor(in[j][i], in[i][i]);
                    result.registerMultiplier(j, i, x);
                    in[j] = operateLine(in[j], in[i], x);
                }
            }
        }

        result.registerMatrix("Result", in);
        result.setSolution(resolve(in));
        return result;
    }

    private double getFactor(double v1, double v2) {
        return (v1 * -1) / v2;
    }

    private double[] operateLine(double[] lineA, double[] lineB, double factor) {
        double[] result = new double[lineA.length];
        for (int i = 0; i < lineA.length; i++) {
            result[i] = lineA[i] + factor * lineB[i];
        }
        return result;
    }

    private void pivot(double[][] input, int row) {
        int maxIdx = row;
        double maxval = input[row][row];
        for (int i = row; i < input.length; i++) {
            if (maxval < input[i][row]) {
                maxval = input[i][row];
                maxIdx = i;
            }
        }

        double[] current = input[row];
        double[] max = input[maxIdx];

        input[row] = max;
        input[maxIdx] = current;
    }

    private double[] resolve(double[][] in) {
        double vectorB[] = ArraysUtils.vectorB(in);
        double vectorX[] = new double[vectorB.length];
        double inp[][] = ArraysUtils.copyWithoutB(in);

        for (int i = vectorB.length-1; i >= 0; i--) {
            for (int j = vectorB.length-1; j > i-1; j--) {
                if(j == i)inp[i][j] = vectorB[j] / inp[i][j];
                else inp[i][j] *= vectorB[j];
            }
        }

        for (int i = 0; i < vectorB.length; i++) {
            for (int j = 0; j < i+1; j++) {
                vectorX[i] += inp[i][j];
            }
        }

        return vectorX;
    }

}
