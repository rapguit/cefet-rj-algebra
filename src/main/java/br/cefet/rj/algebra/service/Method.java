package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Method {

    Result result = new Result();

    abstract void calculate(double[][] input);

    public Result calculateResult(double[][] input){
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
