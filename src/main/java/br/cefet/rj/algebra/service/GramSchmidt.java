package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.changeCol4Row;
import static br.cefet.rj.algebra.util.ArraysUtils.copy;
import static br.cefet.rj.algebra.util.IterativeUtil.transposedOf;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class GramSchmidt extends Method {

	@Override
	void calculate(double[][] input) {
		double u[][] = copy(input);
		double uc[][] = changeCol4Row(u);
		double v[][] = new double[u.length][u[0].length];
		double w[][] = new double[u.length][u[0].length];

		v[0] = uc[0];
		w[0] = norm(v[0]);

		for (int i = 1; i < uc.length; i++) {
			v[i] = plus(uc[i], proj(uc[i], v, i-1));
			w[i] = norm(v[i]);
		}

		result.registerMatrix("M_U", transposedOf(v));
		result.registerMatrix("M_W", transposedOf(w));
	}

	private double[] proj(double[] u, double[][] mtx_v, int k) {
		double v[] = mtx_v[k];
		double div = - mult(u, v) / mult(v, v);
		double result[] = new double[u.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = div * v[i];
		}

		if(k != 0){
			result = plus(result, proj(u, mtx_v, k-1));
		}

		return result;
	}

	private double[] norm(double[] e) {
		double norm[] = new double[e.length];
		double div = sqrt(plusPow(e));
		for (int i = 0; i < e.length; i++) {
			norm[i] = e[i] / div;
		}

		return norm;
	}

	private double mult(double[] u, double[] v) {
		double sum = 0.0;
		for (int i = 0; i < u.length; i++) {
			sum += u[i] * v[i];
		}

		return sum;
	}

	private double[] plus(double[] u, double[] v) {
		double result[] = new double[u.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = u[i] + v[i];
		}
		return result;
	}

	private double plusPow(double[] v) {
		double result = 0.0;
		for (int i = 0; i < v.length; i++) {
			result += pow(v[i], 2);
		}
		return result;
	}
}
