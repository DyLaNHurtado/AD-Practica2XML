package io;

import model.Station;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StationReader {
    public static Optional<List<Station>> readDataOfPath(Path p){
        if(Files.exists(p)) {
            try (Stream<String> stream = Files.lines(p, Charset.forName("windows-1252"))) {
                return Optional.of(stream.skip(1)
                        .map(s -> s.split(";"))
                        .map(s -> {      //s == array of splits

                            return new Station(s[0], s[1], s[2]);
                        })
                        .collect(Collectors.toList()));

            } catch (IOException e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }
        }else{return Optional.empty();}

    }
}
