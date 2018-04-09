package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.IterativeUtil.transposedOf;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import br.cefet.rj.algebra.util.ArraysUtils;

public class Cholesky extends Method {

	@Override
	public void calculate(double[][] input) {
		double a[][] = ArraysUtils.copyWithoutB(input);
		double g[][] = calculateG(a);
		double gt[][] = transposedOf(g);
		double vectorB[] = ArraysUtils.vectorB(input);
		double vectorY[] = calculateY(g, vectorB);
		double vectorX[] = calculateX(gt, vectorY);


		result.registerMatrix("G", g);
		result.registerMatrix("GT", gt);
		result.setSolution(vectorX);
	}

	private double[][] calculateG(double[][] a) {
		double g[][] = new double[a.length][a.length];

		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				if(i == 0 && j == 0) g[i][j] = sqrt(a[i][j]);
				if(j == 0 && i != 0) g[i][j] = a[i][j] / g[j][j];
				if(j < i && j >= 1) g[i][j] = (a[i][j] - sumProd(i, j, g)) / g[j][j];
				if(i == j && i != 0) g[i][j] = sqrt(a[i][j] - sumDiag(i, g));
			}
		}

		return g;
	}

	private double sumDiag(int i, double[][] g) {
		double sum = 0.0;
		for (int k = 0; k <= i-1; k++) {
			sum += pow(g[i][k], 2);
		}

		return sum;
	}

	private double sumProd(int i, int j, double[][] g) {
		double sum = 0.0;
		for (int k = 0; k <= j-1; k++) {
			sum += g[i][k] * g[j][k];
		}

		return sum;
	}

	private double[] calculateY(double[][] g, double[] vectorB) {
		double vectorY[] = new double[vectorB.length];
		double gp[][] = ArraysUtils.copy(g);

		for (int i = 0; i < vectorB.length; i++) {
			double sum = 0.0;
			for (int j = 0; j < i+1; j++) {
				if(i != 0 && i != j) sum = sum - vectorY[j] * gp[i][j];
				else sum += vectorB[i];

				if(i == j)vectorY[i] = sum / g[i][j];
			}
		}

		return vectorY;
	}

	private double[] calculateX(double[][] gt, double[] vectorY) {
		double vectorX[] = new double[vectorY.length];
		double sum = 0.0;

		for(int j=gt.length-1;j>=0;j--) {
			sum = vectorY[j];

			for(int k=j+1; k <= gt.length-1; k++) {
				sum = sum - gt[j][k] * vectorX[k];
			}

			vectorX[j] = sum / gt[j][j];
		}

		return vectorX;
	}
}