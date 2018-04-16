package br.cefet.rj.algebra.service.csr;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import br.cefet.rj.algebra.model.CSRData;
import br.cefet.rj.algebra.model.Result;

public abstract class CSRMethod {

    Result result = new Result();

    abstract void calculate(CSRData input);

    public Result calculateResult(CSRData input){
        LocalTime startAt = LocalTime.now();
        calculate(input);
        LocalTime endAt = LocalTime.now();

        result.setExecTime(startAt.until(endAt, ChronoUnit.NANOS));

        return result;
    }

    //GET SET

    public Result getResult() {
        return result;
    }
}
