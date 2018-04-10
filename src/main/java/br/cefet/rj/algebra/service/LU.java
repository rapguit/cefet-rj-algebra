package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.ArraysUtils;

public class LU extends Method {

    @Override
    public void calculate(double[][] input) {
        double a[][] = ArraysUtils.copyWithoutB(input);
        double vectorB[] = ArraysUtils.vectorB(input);

        calculateLU(a, result);
        double vectorY[] = calculateY(result.getMatrixRegister().get("L"), vectorB);
        double vectorX[] = calculateX(result.getMatrixRegister().get("U"), vectorY);

        result.setSolution(vectorX);
    }

    public static double[] calculateX(Double[][] u, double[] vectorY) {
        double vectorX[] = new double[vectorY.length];
        double up[][] = ArraysUtils.copy(u);

        double sum;

        for (int j = up.length - 1; j >= 0; j--) {
            sum = vectorY[j];

            for (int k = j + 1; k <= up.length - 1; k++) {
                sum = sum - up[j][k] * vectorX[k];
            }

            vectorX[j] = sum / up[j][j];
        }


        return vectorX;
    }

    public static double[] calculateY(Double[][] l, double[] vectorB) {
        double vectorY[] = new double[vectorB.length];
        double lp[][] = ArraysUtils.copy(l);

        for (int i = 0; i < vectorB.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < i + 1; j++) {
                if (i == 0 & i == j) {
                    sum = vectorB[i];
                }else{
                    if(i == j) sum += vectorB[j] / lp[i][j];
                    else sum = sum - vectorY[j] * lp[i][j];
                }
            }
            vectorY[i] = sum;
        }

        return vectorY;
    }

    public static void calculateLU(double[][] a, Result result) {
        double u[][] = new double[a.length][a.length];
        double l[][] = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) l[i][j] = 1;

                if (i == 0) {
                    u[i][j] = a[i][j];
                    if (j > 0) l[j][i] = a[j][i] / u[i][i];
                } else {
                    if (i <= j) u[i][j] = a[i][j] - l[i][i - 1] * u[i - 1][j];
                    if (i > j && j != 0) l[i][j] = (a[i][j] - l[i][j - 1] * u[j - 1][j]) / u[j][j];
                }
            }
        }

        result.registerMatrix("L", l);
        result.registerMatrix("U", u);
    }

}
