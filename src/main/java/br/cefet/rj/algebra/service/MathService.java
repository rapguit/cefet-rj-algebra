package br.cefet.rj.algebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.cefet.rj.algebra.factory.CSRMethodFactory;
import br.cefet.rj.algebra.factory.MethodFactory;
import br.cefet.rj.algebra.model.CSRData;
import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.FileUtils;

@Service
public class MathService {

    @Value("${csr}")
    private boolean csr;

    @Autowired private MethodFactory factory;
    @Autowired private CSRMethodFactory csrFactory;

    public Result calculate(String method, String size, String file) {
        if(csr){
            CSRData input = FileUtils.csr_load(file);
            return csrFactory.get(method).calculateResult(input);
        }

        double input[][] = FileUtils.load(size, file);
        return factory.get(method).calculateResult(input);
    }
}
