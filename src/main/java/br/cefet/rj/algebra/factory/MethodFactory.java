package br.cefet.rj.algebra.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.cefet.rj.algebra.service.Cholesky;
import br.cefet.rj.algebra.service.GaussMethod;
import br.cefet.rj.algebra.service.GramSchmidt;
import br.cefet.rj.algebra.service.Jacobi;
import br.cefet.rj.algebra.service.LU;
import br.cefet.rj.algebra.service.Method;
import br.cefet.rj.algebra.service.Pow;
import br.cefet.rj.algebra.service.ReversePow;
import br.cefet.rj.algebra.service.SOR;
import br.cefet.rj.algebra.service.Seidel;

@Component
public class MethodFactory {

    @Value("${max_it}")
    private int maxIterations;

    @Value("${err}")
    private double errThreshold;

    @Value("${w}")
    private double relaxationParam;

    public Method get(String method) {
        if(method.equals("gauss")){
            return new GaussMethod();
        }
        if(method.equals("lu")){
            return new LU();
        }
        if(method.equals("cholesky")){
            return new Cholesky();
        }
        if(method.equals("jacobi")){
            return new Jacobi(maxIterations, errThreshold);
        }
        if(method.equals("seidel")){
            return new Seidel(maxIterations, errThreshold);
        }
        if(method.equals("sor")){
            return new SOR(maxIterations, errThreshold, relaxationParam);
        }
        if(method.equals("pow")){
            return new Pow(maxIterations, errThreshold);
        }
        if(method.equals("revpow")){
            return new ReversePow(maxIterations, errThreshold);
        }
        if(method.equals("gs")){
            return new GramSchmidt();
        }

        throw new IllegalArgumentException("unknown method!");
    }
}
