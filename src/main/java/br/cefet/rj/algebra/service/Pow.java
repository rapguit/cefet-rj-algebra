package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.box;
import static br.cefet.rj.algebra.util.ArraysUtils.copy;
import static br.cefet.rj.algebra.util.IterativeUtil.maxModOf;
import static br.cefet.rj.algebra.util.IterativeUtil.mod;

public class Pow extends Method {

    private int max;
    private double err;

    public Pow(int max, double err) {
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
        int lambdaIndexFound = -1;

        int iteration = 1;

        while (iteration < max) {
            for (int i = 0; i < a.length; i++) {
                double sum = 0.0;
                for (int j = 0; j < a[0].length; j++) {
                    sum += a[i][j] * iterativeY[j];
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

                result.registerInteractionVector(box(iterativeLambda), iteration - 1, "Lambda");

                if(iteration > 2) {

                    lambdaIndexFound = calculateErr(err, iterativeLambda, previousLambda);
                    if (lambdaIndexFound != -1) {
                        break;
                    }

                }
            }

            previousLambda = copy(iterativeLambda);
            previousY = copy(iterativeY);
            iteration++;
        }

        result.registerInteractionVector(box(iterativeY), 1, "U");
        double sol[] = new double[1];
        sol[0] = iterativeLambda[lambdaIndexFound];
        result.setSolution(sol);
    }

    private int calculateErr(double err, double[] iterativeLambda, double[] previousLambda) {
        double lowestErr = err;
        int lowestErrIndx = -1;
        for (int i = 0; i < iterativeLambda.length; i++) {
            double _err = mod(iterativeLambda[i] - previousLambda[i]) / mod(previousLambda[i]);
            if(err >= _err) {
                if(_err < lowestErr){
                    lowestErr = _err;
                    lowestErrIndx = i;
                }
            }
        }
        return lowestErrIndx;
    }

    private double[] initIterativeVectorY(double[][] a) {
        double[] iterativeY = new double[a.length];
        for (int i = 0; i < iterativeY.length; i++) {
            iterativeY[i] = 1.0;
        }
        return iterativeY;
    }

}
