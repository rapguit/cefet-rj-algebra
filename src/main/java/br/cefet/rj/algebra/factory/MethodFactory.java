package br.cefet.rj.algebra.factory;

import br.cefet.rj.algebra.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

        throw new IllegalArgumentException("unknown method!");
    }
}
