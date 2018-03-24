package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.ArraysUtils;

public class GaussMethod implements Method {

    @Override
    public Result calculate(double[][] input) {
        Result result = new Result();
        double in[][] = ArraysUtils.copy(input);

        for (int i = 0; i <= in.length - 1; i++) {
//            pivot(in, i);
            for (int j = i+1; j < in.length; j++) {
                if (in[j][i] != 0.0) {
                    double x = getFactor(in[j][i], in[i][i]);
                    in[j] = operateLine(in[j], in[i], x);
                }
            }
        }

        result.registerMatrix("Result", in);
        result.setSolution(resolve(in));
        return result;
    }

    private double getFactor(double v1, double v2) {
        return (v1 / v2) * -1;
    }

    private double[] operateLine(double[] lineA, double[] lineB, double factor) {
        double[] result = new double[lineA.length];
        for (int i = 0; i < lineA.length; i++) {
            result[i] = lineB[i] * factor + lineA[i];
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

        double sum = 0.0;

        for(int j=inp.length-1;j>=0;j--) {
            sum = vectorB[j];

            for(int k=j+1; k <= inp.length-1; k++) {
                sum = sum - inp[j][k] * vectorX[k];
            }

            vectorX[j] = sum / inp[j][j];
        }


        return vectorX;
    }

}
