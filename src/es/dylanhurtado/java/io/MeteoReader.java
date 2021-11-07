package io;

import model.Meteo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MeteoReader {

    public static Optional<List<Meteo>> readDataOfPath(Path p){
        if(Files.exists(p)) {
            try (Stream<String> stream = Files.lines(p, Charset.forName("windows-1252"))) {
                return Optional.of(stream.skip(1)
                        .map(s -> s.split(";"))
                        .map(s -> {      //s == array of splits

                            return new Meteo(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7],
                                    s[8], s[9], s[10], s[11], s[12], s[13], s[14], s[15], s[16],
                                    s[17], s[18], s[19], s[20], s[21], s[22], s[23], s[24], s[25],
                                    s[26], s[27], s[28], s[29], s[30], s[31], s[32], s[33], s[34],
                                    s[35], s[36], s[37], s[38], s[39], s[40], s[41], s[42], s[43],
                                    s[44], s[45], s[46], s[47], s[48], s[49], s[50], s[51], s[52], s[53], s[54], s[55]);
                        })
                        .collect(Collectors.toList()));

            } catch (IOException e) {
                System.err.println(e.getMessage());
                return Optional.empty();
            }
        }else{return Optional.empty();}

    }
}
