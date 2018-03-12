package br.cefet.rj.algebra.model;

import br.cefet.rj.algebra.util.ArraysUtils;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private double solution[];
    private Map<String, Double> multipliers;
    private Map<String, Double[][]> matrixRegister;

    public Result() {
        this.multipliers = new HashMap<>();
        this.matrixRegister = new HashMap<>();
    }

    public void registerMultiplier(int i, int j, double multiplier) {
        String key = String.format("%s%s", i, j);
        multipliers.put(key, multiplier);
    }

    public void registerMatrix(String name, double[][] matrix) {
        matrixRegister.put(name, ArraysUtils.box(matrix));
    }

    //GET e SET


    public Map<String, Double[][]> getMatrixRegister() {
        return matrixRegister;
    }

    public Map<String, Double> getMultipliers() {
        return multipliers;
    }

    public double[] getSolution() {
        return solution;
    }

    public void setSolution(double[] solution) {
        this.solution = solution;
    }
}
