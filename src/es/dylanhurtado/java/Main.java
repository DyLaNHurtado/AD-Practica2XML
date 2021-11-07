import io.MeteoReader;
import io.StationReader;
import model.Station;
import service.MeteoService;
import service.StationService;

import java.io.File;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    final static String RESOURCES = "src"+ File.separator +"es"+File.separator+"dylanhurtado"+File.separator+"resources";
    final static Path METE0CSV = Path.of(RESOURCES+File.separator+"calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES+File.separator+"calidad_aire_estaciones.csv");
    static MeteoService meteoService;
    static StationService stationService;
    public static void main(String[] args){

        //if (args.length == 2 ) {
            //String city = args[0];
            //String uri = args[1];
        StationReader.readDataOfPath(STATIONCSV).ifPresent((list)->
                stationService = new StationService(list));
        System.out.println(stationService.dataStringStation(stationService.dataStation("Coslada")));
   // }else {
            //System.err.println("Syntax Error: java -jar meteo.jar <exampleCity> <directory> ");
        //}


        //Print aLL MeteoDataRegisters

        Optional<List<Station>> s =StationReader.readDataOfPath(METE0CSV);
        s.ifPresent(System.out::println);


    }
}
