package br.cefet.rj.algebra.model;

import br.cefet.rj.algebra.util.ArraysUtils;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private double solution[];
    private Map<String, Double[][]> matrixRegister;

    public Result() {
        this.matrixRegister = new HashMap<>();
    }

    public void registerMatrix(String name, double[][] matrix) {
        matrixRegister.put(name, ArraysUtils.box(matrix));
    }

    //GET e SET


    public Map<String, Double[][]> getMatrixRegister() {
        return matrixRegister;
    }

    public double[] getSolution() {
        return solution;
    }

    public void setSolution(double[] solution) {
        this.solution = solution;
    }
}
