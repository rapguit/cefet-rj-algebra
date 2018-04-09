package br.cefet.rj.algebra.service;

import static br.cefet.rj.algebra.util.ArraysUtils.copy;

public class GramSchmidt extends Method {

	@Override
	void calculate(double[][] input) {
		double h[][] = copy(input);
		double[][] w = new double[h.length][h[0].length];

		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < h[0].length; j++) {
				if (i == 0){
					w[j][i] = h[j][i];
				}



			}
		}
	}

}
