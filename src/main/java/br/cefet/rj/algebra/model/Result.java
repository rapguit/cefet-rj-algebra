package br.cefet.rj.algebra.model;

import br.cefet.rj.algebra.util.ArraysUtils;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private double solution[];
    private Map<String, Double[][]> matrixRegister;
    private Map<String, Double[]> vectorRegister;
    private LocalTime execTime;

    public Result() {
        this.matrixRegister = new LinkedHashMap<>();
        this.vectorRegister = new LinkedHashMap<>();
    }

    public void registerMatrix(String name, double[][] matrix) {
        matrixRegister.put(name, ArraysUtils.box(matrix));
    }

    public void registerInteractionVector(Double[] interactiveX, int interact) {
        vectorRegister.put(String.format("X_%s", interact), ArraysUtils.copy(interactiveX));
    }

    //GET e SET


    public Map<String, Double[][]> getMatrixRegister() {
        return matrixRegister;
    }

    public Map<String, Double[]> getVectorRegister() {
        return vectorRegister;
    }

    public double[] getSolution() {
        return solution;
    }

    public void setSolution(double[] solution) {
        this.solution = solution;
    }

    public void setExecTime(long execTime) {
        this.execTime = LocalTime.ofNanoOfDay(execTime);
    }

    public LocalTime getExecTime() {
        return execTime;
    }
}
