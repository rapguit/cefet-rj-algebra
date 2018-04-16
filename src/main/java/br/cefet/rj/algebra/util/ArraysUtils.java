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

    public static Double[] copy(Double[] input) {
        Double copy[] = new Double[input.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = input[i];
        }

        return copy;
    }

    public static double[] copy(double[] input) {
        double copy[] = new double[input.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = input[i];
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
        String mask = solution.length == 1 ? "%5.16f" : "%5.1f";
        for (double element : solution) {
            System.out.printf(mask, element);
        }
        System.out.println();
    }

    public static void printVectorRaw(double[] solution) {
        for (double element : solution) {
            if (element >= 0) System.out.printf(" %5.16f \t", element);
            else System.out.printf("%5.16f \t", element);
        }
        System.out.println();
    }

    public static void printMatrix(Double[][] matrix) {
        for (Double[] row : matrix) {
            printVector(unbox(row));
        }
    }

    public static void printMatrixRaw(Double[][] matrix) {
        for (Double[] row : matrix) {
            printVectorRaw(unbox(row));
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

    public static Double[] box(double[] vector) {
        Double boxed[] = new Double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            boxed[i] = vector[i];
        }
        return boxed;
    }

    public static double[] unbox(Double[] arr) {
        double unboxed[] = new double[arr.length];
        for (int i = 0; i < unboxed.length; i++) {
            unboxed[i] = arr[i];
        }

        return unboxed;
    }

    public static double[][] changeCol4Row(double[][] matrix){
        double changed[][] = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                changed[i][j] = matrix[j][i];
            }
        }
        return changed;
    }

}
