package br.cefet.rj.algebra.factory;

import br.cefet.rj.algebra.service.Cholesky;
import br.cefet.rj.algebra.service.GaussMethod;
import br.cefet.rj.algebra.service.LU;
import br.cefet.rj.algebra.service.Method;
import org.springframework.stereotype.Component;

@Component
public class MethodFactory {
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

        throw new IllegalArgumentException("unknown method!");
    }
}
