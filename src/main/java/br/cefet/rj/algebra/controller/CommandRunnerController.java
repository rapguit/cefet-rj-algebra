package br.cefet.rj.algebra.controller;

import br.cefet.rj.algebra.model.Result;
import br.cefet.rj.algebra.service.MathService;
import br.cefet.rj.algebra.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandRunnerController implements ApplicationRunner{

    @Autowired private MathService service;
    @Autowired private PrintService printer;

    @Override
    public void run(ApplicationArguments arguments) throws Exception {
        List<String> args = arguments.getNonOptionArgs();

        if (args.isEmpty()){
            System.out.println("usage: [method] [NxM] [input_file]");
            System.out.println("Example: gauss 4x4 input_file.txt");

        }else {
            String method = args.get(0);
            String size = args.get(1);
            String file = args.get(2);

            Result result = service.calculate(method, size, file);
            printer.printResults(method, result);

        }
    }
}
