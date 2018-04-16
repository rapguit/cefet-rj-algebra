package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.*;
import static br.cefet.rj.algebra.util.IterativeUtil.*;

public class ConjugatedGradient extends Method {

    private int max;
    private double err;

    public ConjugatedGradient(int max, double err) {
        this.max = max > 0 ? max : Integer.MAX_VALUE;
        this.err = err;

        System.out.println("\t ENV:");
        System.out.println("\t\t max: " + this.max);
        System.out.println("\t\t err: " + this.err);
    }

    @Override
    void calculate(double[][] input) {
        double a[][] = copyWithoutB(input);
        double vectorB[] = vectorB(input);
        double iterativeR[] = new double[vectorB.length];
        double previousR[] = new double[iterativeR.length];
        double olderR[] = new double[previousR.length];
        double iterativeV[] = new double[iterativeR.length];
        double previousV[] = new double[iterativeV.length];
        double iterativeP[] = new double[iterativeR.length];
        double previousP[] = new double[iterativeP.length];
        double iterativeQ = 0.0;
        double previousQ = iterativeQ;

        int iteration = 0;

        while (iteration < max) {
            iterativeR = minus(mult(a, iterativeV), vectorB);
            iterativeQ = mult(iterativeR, iterativeR) / mult(mult(a, iterativeR), iterativeR);

            if (iteration > 0){
                if(iteration == 1) {
                    iterativeV = minus(previousV, mult(previousQ, previousR));
                    iterativeP = mult(-1, iterativeR);
                    iterativeR = minus(previousR, mult(previousQ, mult(a, previousR)));
                }

                if (iteration > 1){
                    double alpha = mult(previousR, previousR) / mult(olderR, olderR);
                    iterativeP = plus(mult(-1, iterativeR), mult(alpha, previousP));

                    double[] ap = mult(a, iterativeP);
                    iterativeQ = mult(iterativeR, iterativeR) / mult(ap, iterativeP);
                    iterativeV = plus(previousV, mult(iterativeQ, iterativeP));
                    iterativeR = plus(previousR, mult(iterativeQ, ap));
                }

                result.registerInteractionVector(box(iterativeV), iteration, "V");

                if(err >= calculateErr(iterativeV, previousV)){
                    break;
                }

            }

            olderR = copy(previousR);
            previousR = copy(iterativeR);
            previousQ = iterativeQ;
            previousV = copy(iterativeV);
            previousP = iterativeP;

            iteration++;
        }

        result.setSolution(iterativeV);

    }

    private double calculateErr(double[] iterativeV, double[] previousV) {
        return minModOf(minus(iterativeV, previousV)) / maxModOf(iterativeV);
    }

}
