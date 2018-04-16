package br.cefet.rj.algebra.service.csr;

import static br.cefet.rj.algebra.util.ArraysUtils.copy;
import static br.cefet.rj.algebra.util.ArraysUtils.unbox;
import static br.cefet.rj.algebra.util.IterativeUtil.maxModOf;
import static br.cefet.rj.algebra.util.IterativeUtil.mod;

import br.cefet.rj.algebra.model.CSRData;

public class SORCompact extends CSRMethod {
	private int max;
	private double err;
	private double w;

	public SORCompact(int max, double err, double w) {
		this.max = max > 0 ? max : Integer.MAX_VALUE;
		this.err = err;
		this.w = w;

		System.out.println("\t ENV:");
		System.out.println("\t\t max: " + this.max);
		System.out.println("\t\t err: " + this.err);
		System.out.println("\t\t w: " + this.w);

		if(w == 1) System.out.println("\t\t Gauss-Seidel param");
		if(w > 0 && w < 1) System.out.println("\t\t Under Relaxation param");
		if(w > 1 && w < 2) System.out.println("\t\t Over Relaxation param");
		if(w >= 2) System.out.println("\t\t Unstable param");
	}

	@Override
	public void calculate(CSRData input) {
		CSRData a = input.a();
		CSRData vectorB = input.b();
		Double iterativeX[] = initIterativeVectorX(vectorB.getN());
		Double previousX[] = copy(iterativeX);
		int iteration = 1;

		while (iteration < max) {
			for (int i = 0; i < iterativeX.length; i++) {
				iterativeX[i] = operateLine(i, a, vectorB, iterativeX);
			}

			result.registerInteractionVector(iterativeX, iteration, "X");
			iteration++;

			if (err >= calculateErr(iterativeX, previousX)){
				break;
			}

			previousX = copy(iterativeX);
		}

		result.setSolution(unbox(result.getVectorRegister().get("X_"+(iteration-1))));
	}

	private double operateLine(int i, CSRData a, CSRData b, Double[] iterativeX) {
		double div =  a.read(i , i);
		double sum = b.read(i,0) / div;
		double x = 1.0;

		for (int j = 0; j <b.getN(); j++) {
			if(i != j) {
				sum -= (a.read(i,j) / div) * iterativeX[j];
			}else {
				x = iterativeX[i];
			}
		}

		return sum * w + (1 - w) * x;
	}

	private double calculateErr(Double[] iterativeX, Double[] previousX) {
		double err[] = new double[iterativeX.length];
		for (int i = 0; i < err.length; i++) {
			err[i] = mod(iterativeX[i] - previousX[i]);
		}

		return maxModOf(err) / maxModOf(unbox(iterativeX));
	}

	private Double[] initIterativeVectorX(int size) {
		Double[] x = new Double[size];
		for (int i = 0; i < x.length; i++) {
			x[i] = 0.0D;
		}

		return x;
	}

}
