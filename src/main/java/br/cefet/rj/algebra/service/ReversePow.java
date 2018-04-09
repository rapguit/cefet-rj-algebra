package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.box;
import static br.cefet.rj.algebra.util.ArraysUtils.copy;
import static br.cefet.rj.algebra.util.IterativeUtil.maxModOf;
import static br.cefet.rj.algebra.util.IterativeUtil.mod;

public class ReversePow extends Method {

    private int max;
    private double err;

    public ReversePow(int max, double err) {
        this.max = max > 0 ? max : Integer.MAX_VALUE;
        this.err = err;

        System.out.println("\t ENV:");
        System.out.println("\t\t max: " + this.max);
        System.out.println("\t\t err: " + this.err);
    }

    @Override
    public void calculate(double[][] input) {
        double a[][] = copy(input);
        double iterativeY[] = initIterativeVectorY(a);
        double previousY[] = copy(iterativeY);
        double iterativeZ[] = new double[iterativeY.length];
        double[] iterativeLambda = new double[iterativeY.length];
        double[] previousLambda = copy(iterativeLambda);
        double iterativeAlpha = 0.0;

        LU.calculateLU(a, result);

        Double l[][] = result.getMatrixRegister().get("L");
        Double u[][] = result.getMatrixRegister().get("U");

        int iteration = 1;

        while (iteration < max) {
            double lz[] = new double[iterativeY.length];
            for (int i = 0; i < l.length; i++) {
                double sum = 0.0;
                for (int j = 0; j < l[0].length; j++) {
                    sum += 1 / l[i][j] * iterativeY[j];
                }
                lz[i] = sum;
            }

            for (int i = 0; i < u.length; i++) {
                double sum = 0.0;
                for (int j = 0; j < u[0].length; j++) {
                    sum += 1 / u[i][j] * lz[j];
                }
                iterativeZ[i] = sum;
                iterativeAlpha = maxModOf(iterativeZ);
            }

            for (int i = 0; i < iterativeY.length; i++) {
                iterativeY[i] = (1 / iterativeAlpha) * iterativeZ[i];
            }

            if (iteration > 1) {
                for (int i = 0; i < previousLambda.length; i++) {
                    iterativeLambda[i] = iterativeZ[i] / previousY[i];
                }

                if (err >= calculateErr(iterativeLambda, previousLambda)) {
                    break;
                }

                result.registerInteractionVector(box(iterativeLambda), iteration-1, "Lambda");
            }

            previousLambda = copy(iterativeLambda);
            previousY = copy(iterativeY);
            iteration++;
        }

        result.setSolution(iterativeY);
    }

    private double calculateErr(double[] iterativeLambda, double[] previousLambda) {
        double err[] = new double[iterativeLambda.length];
        for (int i = 0; i < iterativeLambda.length; i++) {
            err[i] = mod(iterativeLambda[i] - previousLambda[i]) / mod(previousLambda[i]);
        }
        return maxModOf(err);
    }

    private double[] initIterativeVectorY(double[][] a) {
        double[] iterativeY = new double[a.length];
        for (int i = 0; i < iterativeY.length; i++) {
            iterativeY[i] = 1.0;
        }
        return iterativeY;
    }

}
