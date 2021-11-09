package main;

import io.xml.JaxbController;
import io.xml.JdomController;
import model.pojosToRead_XML.Temperatura;
import model.pojosToWrite_XML.DataMeteo;
import model.pojosToWrite_XML.Resultado;
import model.pojosToWrite_XML.Resultados;
import model.pojos_CSV_To_XML.Meteo;
import service.MeteoUtil;
import service.StationUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    final static String RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator + "es" + File.separator + "dylanhurtado" + File.separator + "resources";
    final static Path METE0CSV = Path.of(RESOURCES + File.separator + "calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES + File.separator + "calidad_aire_estaciones.csv");
    final static String TEMPERAXML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "temperatura.xml";
    final static String MEDICIONESXML = System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml";
    static MeteoUtil meteoUtil;
    static StationUtil stationUtil;
    public static long init = System.currentTimeMillis();

    public static void main(String[] args) {


        JdomController JDOMController = JdomController.getInstance();

        try {
            JDOMController.initData("temperatura");

            JDOMController.generateXML("temperatura");

            JDOMController.loadMediciones("Getafe");
            JDOMController.generateMD();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JaxbController jaxbController = JaxbController.getInstance();
        try {
            Temperatura temperatura = jaxbController.loadData(TEMPERAXML);

        } catch (JAXBException e) {
            e.printStackTrace();
            System.err.println("Error: No se ha podido leer y cargar los datos de temperatura.xml " + e.getMessage());
        }

    }
}
