package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.factory.MethodFactory;
import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    @Autowired private MethodFactory factory;

    public Result calculate(String method, String size, String file) {
        double input[][] = FileUtils.load(size, file);
        return factory.get(method).calculate(input);
    }

    public double[] solve(double[][] result) {
        double solution[] = new double[result.length];
        solution[solution.length-1] = result[result.length-1][result[0].length-1] / result[result.length-2][result[0].length-2];
        return solution;
    }
}
