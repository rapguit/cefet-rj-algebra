package br.cefet.rj.algebra.util;

public class ArraysUtils {
    public static double[][] copy(double[][] input) {
        double copy[][] = new double[input.length][input[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                copy[i][j] = input[i][j];
            }
        }

        return copy;
    }

    public static double[][] copy(Double[][] input) {
        double copy[][] = new double[input.length][input[0].length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                copy[i][j] = input[i][j];
            }
        }

        return copy;
    }


    public static double[][] copyWithoutB(double[][] input) {
        double copy[][] = new double[input.length][input.length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                copy[i][j] = input[i][j];
            }
        }

        return copy;
    }

    public static double[][] copyWithoutB(Double[][] input) {
        double copy[][] = new double[input.length][input.length];
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                copy[i][j] = input[i][j];
            }
        }

        return copy;
    }

    public static double[] vectorB(double[][] matrix) {
        double vectorB[] = new double[matrix.length];
        for (int i = 0; i < vectorB.length; i++) {
            vectorB[i] = matrix[i][matrix[0].length-1];
        }
        return vectorB;
    }

    public static void printVector(double[] solution) {
        for (double element : solution) {
            System.out.printf("%5.1f", element);
        }
        System.out.println();
    }

    public static void printMatrix(Double[][] matrix) {
        for (Double[] row : matrix) {
            printVector(unbox(row));
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            printVector(row);
        }
    }

    public static Double[][] box(double[][] matrix) {
        Double boxed[][] = new Double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boxed[i][j] = matrix[i][j];
            }
        }
        return boxed;
    }

    private static double[] unbox(Double[] arr) {
        double unboxed[] = new double[arr.length];
        for (int i = 0; i < unboxed.length; i++) {
            unboxed[i] = arr[i];
        }

        return unboxed;
    }
}
