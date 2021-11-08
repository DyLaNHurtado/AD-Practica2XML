import io.CSV.StationReader;
import io.XML.XmlController;
import model.Station;
import service.MeteoService;
import service.StationService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Main {
    final static String RESOURCES = "src"+ File.separator +"es"+File.separator+"dylanhurtado"+File.separator+"resources";
    final static Path METE0CSV = Path.of(RESOURCES+File.separator+"calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES+File.separator+"calidad_aire_estaciones.csv");
    static MeteoService meteoService;
    static StationService stationService;

    public static void main(String[] args){
        XmlController xmlController = XmlController.getInstance();

        xmlController.initData();
        try {
            xmlController.generateXML("temperatura");
        } catch (IOException e) {
            System.err.println("Error: No se ha podido generar temperatura.xml " + e.getMessage());
        }



    }
}
