package br.cefet.rj.algebra.util;

public class IterativeUtil {
    public static void convergenceCheck(double[][] m) {
        diagonalDominantCheck(m);
    }

    private static void diagonalDominantCheck(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < m[0].length; j++) {
                if(i != j) sum += mod(m[i][j]);
            }
            if(mod(m[i][i]) < sum) throw new RuntimeException("Sistema n converge!");
        }
    }

    public static double maxModOf(double[] v) {
        double max = 0.0;
        for (double val : v) {
            if (mod(val) > max) max = mod(val);
        }
        return max;
    }

    public static double minModOf(double[] v) {
        double min = v[0];
        for (double val : v) {
            if (mod(val) < min) min = mod(val);
        }
        return min;
    }

    public static double mod(double val) {
        if(val < 0) return val * -1;
        return val;
    }

    public static double[][] transposedOf(double[][] l) {
        double transposed[][] = new double[l.length][l.length];
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                transposed[j][i] = l[i][j];
            }
        }

        return transposed;
    }
}
