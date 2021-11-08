package io.csv;

import model.pojos_CSV_To_XML.Station;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StationReader {

    private final static List<Station> listaVacia = new ArrayList<>();

    public static List<Station> readDataOfPath(Path p) {
        if (Files.exists(p)) {
            try (Stream<String> stream = Files.lines(p, Charset.forName("windows-1252"))) {
                return stream.skip(1)
                        .map(s -> s.split(";"))
                        .map(s -> {      //s == array of splits

                            return new Station(s[0], s[1], s[2]);
                        })
                        .collect(Collectors.toList());

            } catch (IOException e) {
                System.err.println(e.getMessage());
                return listaVacia;
            }
        } else {
            return listaVacia;
        }

    }
}
