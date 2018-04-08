package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;

import static br.cefet.rj.algebra.util.ArraysUtils.*;
import static br.cefet.rj.algebra.util.IterativeUtil.convergenceCheck;
import static br.cefet.rj.algebra.util.IterativeUtil.maxModOf;
import static br.cefet.rj.algebra.util.IterativeUtil.mod;

public class Jacobi implements Method {

    private int max;
    private double err;

    public Jacobi(int max, double err) {
        this.max = max > 0 ? max : Integer.MAX_VALUE;
        this.err = err;

        System.out.println("\t ENV:");
        System.out.println("\t\t max: " + max);
        System.out.println("\t\t err: " + err);
    }

    @Override
    public Result calculate(double[][] input) {
        Result result = new Result();

        double a[][] = copyWithoutB(input);
        double vectorB[] = vectorB(input);
        Double iteractiveX[] = initIteractiveVectorX(vectorB);
        Double previousX[] = copy(iteractiveX);
        int iteract = 1;

        convergenceCheck(a);

        while (iteract < max) {
            for (int i = 0; i < iteractiveX.length; i++) {
                iteractiveX[i] = operateLine(i, a, vectorB, previousX, iteract);
            }

            result.registerInteractionVector(iteractiveX, iteract);
            iteract++;

            if (err >= calculateErr(iteractiveX, previousX)){
                break;
            }

            previousX = copy(iteractiveX);
        }

        result.setSolution(unbox(result.getVectorRegister().get("X_"+(iteract-1))));
        return result;
    }

    private double operateLine(int i, double[][] a, double[] b, Double[] x, int iteract) {
        double div =  a[i][i];
        double sum = b[i] / div;

        for (int j = 0; j <b.length; j++) {
            if(i != j) {
                sum -= (a[i][j] / div) * x[j];
            }
        }

        return sum;
    }

    private double calculateErr(Double[] iteractiveX, Double[] previousX) {
        double err[] = new double[iteractiveX.length];
        for (int i = 0; i < err.length; i++) {
            err[i] = mod(iteractiveX[i] - previousX[i]);
        }

        return maxModOf(err) / maxModOf(unbox(iteractiveX));
    }

    private Double[] initIteractiveVectorX(double[] vectorB) {
        Double[] x = new Double[vectorB.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = 0.0D;
        }

        return x;
    }

}
