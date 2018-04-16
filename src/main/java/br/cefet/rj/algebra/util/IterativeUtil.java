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

    public static double mult(double[] u, double[] v) {
        double sum = 0.0;
        for (int i = 0; i < u.length; i++) {
            sum += u[i] * v[i];
        }

        return sum;
    }

    public static double[] mult(double[][] m, double[] v) {
        double[] result = new double[v.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = mult(m[i], v);
        }

        return result;
    }

    public static double[] mult(double scalar, double[] v) {
        double[] result = new double[v.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = scalar * v[i];
        }

        return result;
    }

    public static double[] plus(double[] u, double[] v) {
        double result[] = new double[u.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = u[i] + v[i];
        }
        return result;
    }

    public static double[] minus(double[] u, double[] v) {
        double result[] = new double[u.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = u[i] - v[i];
        }
        return result;
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
