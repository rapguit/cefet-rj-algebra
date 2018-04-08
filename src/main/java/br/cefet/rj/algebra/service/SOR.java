package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.*;
import static br.cefet.rj.algebra.util.IterativeUtil.*;

public class SOR extends Method {

    private int max;
    private double err;
    private double w;

    public SOR(int max, double err, double w) {
        this.max = max > 0 ? max : Integer.MAX_VALUE;
        this.err = err;
        this.w = w;

        System.out.println("\t ENV:");
        System.out.println("\t\t max: " + this.max);
        System.out.println("\t\t err: " + this.err);
        System.out.println("\t\t w: " + this.w);

        if(w == 1) System.out.println("\t\t Gauss-Seidel param");
        if(w > 0 && w < 1) System.out.println("\t\t Under Relaxation param");
        if(w > 1 && w < 2) System.out.println("\t\t Over Relaxation param");
        if(w >= 2) System.out.println("\t\t Unstable param");
    }

    @Override
    public void calculate(double[][] input) {
        double a[][] = copyWithoutB(input);
        double vectorB[] = vectorB(input);
        Double iterativeX[] = initIterativeVectorX(vectorB);
        Double previousX[] = copy(iterativeX);
        int iteration = 1;

        convergenceCheck(a);

        while (iteration < max) {
            for (int i = 0; i < iterativeX.length; i++) {
                iterativeX[i] = operateLine(i, a, vectorB, iterativeX);
            }

            result.registerInteractionVector(iterativeX, iteration);
            iteration++;

            if (err >= calculateErr(iterativeX, previousX)){
                break;
            }

            previousX = copy(iterativeX);
        }

        result.setSolution(unbox(result.getVectorRegister().get("X_"+(iteration-1))));
    }

    private double operateLine(int i, double[][] a, double[] b, Double[] iterativeX) {
        double div =  a[i][i];
        double sum = b[i] / div;
        double x = 1.0;

        for (int j = 0; j <b.length; j++) {
            if(i != j) {
                sum -= (a[i][j] / div) * iterativeX[j];
            }else {
                x = iterativeX[i];
            }
        }

        return sum * w + (1 - w) * x;
    }

    private double calculateErr(Double[] iterativeX, Double[] previousX) {
        double err[] = new double[iterativeX.length];
        for (int i = 0; i < err.length; i++) {
            err[i] = mod(iterativeX[i] - previousX[i]);
        }

        return maxModOf(err) / maxModOf(unbox(iterativeX));
    }

    private Double[] initIterativeVectorX(double[] vectorB) {
        Double[] x = new Double[vectorB.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0.0D;
        }

        return x;
    }

}
