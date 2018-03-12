package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.ArraysUtils;

public class Cholesky implements Method {

	@Override
	public Result calculate(double[][] input) {
		final Result result = new Result();
		double in[][] = ArraysUtils.copyWithoutB(input);
		double l[][] = calculateL(in);
		double lt[][] = transposedOf(l);
		double vectorB[] = ArraysUtils.vectorB(input);
		double vectorY[] = calculateY(l, vectorB);
		double vectorX[] = calculateX(lt, vectorY);


		result.registerMatrix("L", l);
		result.registerMatrix("LT", lt);
		result.setSolution(vectorX);
		return result;
	}

	private double[][] calculateL(double[][] in) {
		double l[][] = new double[in.length][in.length];
		double pivot = 0.0;

		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (i == j) pivot = in[i][j];
				if (i == 0 && j == 0) {
					pivot = Math.sqrt(in[i][j]);
					l[i][j] = pivot;
				} else {
					l[i][j] = in[i][j] / pivot;
				}
			}
		}

		return l;
	}

	private double[][] transposedOf(double[][] l) {
		double transposed[][] = new double[l.length][l.length];
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l.length; j++) {
				transposed[j][i] = l[i][j];
			}
		}
		return transposed;
	}

	private double[] calculateY(double[][] l, double[] vectorB) {
		return new double[0];
	}

	private double[] calculateX(double[][] lt, double[] vectorY) {
		return new double[0];
	}
}