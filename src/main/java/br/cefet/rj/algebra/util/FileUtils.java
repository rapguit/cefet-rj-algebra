package br.cefet.rj.algebra.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {
    public static double[][] load(String size, String file) {
        try {

            return _load(size, file);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static double[][] _load(String size, String file) throws IOException {
        List<List<Double>> data = loadFromFile(file);

        String[] nm = size.split("x");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        double matrix[][] = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = data.get(i).get(j);
            }
        }

        return matrix;
    }

    private static List<List<Double>> loadFromFile(String file) throws IOException {
        List<List<Double>> col = new ArrayList<>();
        Files.lines(Paths.get(file)).forEach(line -> {
            List<Double> row = new ArrayList<>();
            Stream.of(line.split(","))
                    .map(Double::parseDouble)
                    .forEach(row::add);
            col.add(row);
        });

        return col;
    }
}
