package br.cefet.rj.algebra.service;

import br.cefet.rj.algebra.model.Result;

public interface Method {
    Result calculate(double[][] input);
}
