package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.cefet.rj.algebra.util.ArraysUtils.*;
import static br.cefet.rj.algebra.util.ArraysUtils.printVector;

@Service
public class PrintService {

    public void printResults(String method, Result result) {
        boolean hasSolution = result.getSolution() != null;

        System.out.println("[[ " + method.toUpperCase() + " ]]");
        result.getMatrixRegister().forEach( (name, matrix) -> {
            System.out.println(name+": ");
            if(hasSolution) printMatrix(matrix);
            else printMatrixRaw(matrix);
        });
        result.getVectorRegister().forEach( (name, vector) -> {
            System.out.println(name+": ");
            printVectorRaw(unbox(vector));
        });

        Optional.ofNullable(result.getSolution()).ifPresent(sol -> {
            System.out.println("Solution: ");
            printVector(sol);
        } );

        System.out.println("Execution: ");
        System.out.println("\t time: "+result.getExecTime());
    }
}
