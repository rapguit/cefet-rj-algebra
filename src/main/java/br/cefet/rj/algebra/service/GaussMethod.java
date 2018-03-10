package br.cefet.rj.algebra.service;

public class GaussMethod implements Method {

    @Override
    public double[][] calculate(double[][] input) {
        for (int i = 0; i <= input.length - 1; i++) {
            pivot(input, i);
            for (int j = i+1; j < input.length; j++) {
                if (input[j][i] != 0.0) {
                    double x = getFactor(input[j][i], input[i][i]);
                    input[j] = operateLine(input[j], input[i], x);
                }
            }
        }

        return input;
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

}
