package br.cefet.rj.algebra.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.cefet.rj.algebra.service.csr.CSRMethod;
import br.cefet.rj.algebra.service.csr.SORCompact;

@Component
public class CSRMethodFactory {
	@Value("${max_it}")
	private int maxIterations;

	@Value("${err}")
	private double errThreshold;

	@Value("${w}")
	private double relaxationParam;

	public CSRMethod get(String method) {
		if(method.equals("jacobi")){
		}
		if(method.equals("sor")){
			return new SORCompact(maxIterations, errThreshold, relaxationParam);
		}
		if(method.equals("gradconj")){
		}

		throw new IllegalArgumentException("unknown method!");
	}
}
