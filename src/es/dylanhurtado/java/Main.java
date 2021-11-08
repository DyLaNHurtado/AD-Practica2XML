import io.xml.JaxbController;
import io.xml.JdomController;
import model.pojosToRead_XML.Temperatura;
import model.pojosToWrite_XML.Resultados;
import service.MeteoUtil;
import service.StationUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    final static String RESOURCES = "src" + File.separator + "es" + File.separator + "dylanhurtado" + File.separator + "resources";
    final static Path METE0CSV = Path.of(RESOURCES + File.separator + "calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES + File.separator + "calidad_aire_estaciones.csv");
    final static String TEMPERAXML = "data" + File.separator + "temperatura.xml";
    static MeteoUtil meteoUtil;
    static StationUtil stationUtil;

    public static void main(String[] args) {


        JdomController JDOMController = JdomController.getInstance();

        JDOMController.initData();
        try {
            JDOMController.generateXML("temperatura");
        } catch (IOException e) {
            System.err.println("Error: No se ha podido generar temperatura.xml " + e.getMessage());
        }

        Resultados resultados = new Resultados();
        JaxbController jaxbController = JaxbController.getInstance();
        try {
            Temperatura temperatura = jaxbController.loadData(TEMPERAXML);
            jaxbController.initData(resultados);

        } catch (JAXBException e) {
            System.err.println("Error: No se ha podido leer y cargar los datos de temperatura.xml " + e.getMessage());
        }

    }
}
