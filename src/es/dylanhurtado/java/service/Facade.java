package service;

import io.csv.MeteoReader;
import io.csv.StationReader;
import io.xml.JaxbController;
import io.xml.JdomController;
import model.pojosToRead_XML.Municipio;
import model.pojosToRead_XML.Temperatura;
import model.pojos_CSV_To_XML.Station;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Facade {

    final static String RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator + "es" + File.separator + "dylanhurtado" + File.separator + "resources";
    final static Path METE0CSV = Path.of(RESOURCES + File.separator + "calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES + File.separator + "calidad_aire_estaciones.csv");
    final static String TEMPERAXML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "temperatura.xml";
    final static String MEDICIONESXML = System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml";


    private static Facade instance;
    private String ciudad;


    private Facade() {
    }

    /**
     * Patron Singleton
     *
     * @return instancia de la clase
     */
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    /**
     * Metodo el cual valida los argumentos dados en el main y es el metodo desencadenante para la ejecucion
     *
     * @param args Argumentos para main
     * @throws JAXBException excepcion jaxb
     * @throws IOException  excepcion io
     */
    public void mainRun(String[] args) throws JAXBException, IOException {
        if (args.length == 2 && comprobarNombre(args[0])) {
            File directory = new File(System.getProperty("user.dir") + File.separator + "db");
            if (directory.exists()) {
                mainData(args);
            } else {
                directory.mkdir();
                System.out.println("Directorio db generado con éxito");
                mainData(args);
            }
        } else {
            System.out.println("** Argumentos Incorrectos **");
            System.out.println("Primer Argumento : Nombre de ciudad , Segundo Argumento : Directorio a guardar (URI)");
            System.out.println("Ejemplo: java -jar ciudadEjemplo directorio/de/ejemplo");
        }
    }

    /**
     * Metodo que adjunta pruebas y hechos de lo que se ha realizado en esta práctica
     *
     * @param args Argumentos para main
     * @throws IOException excepcion jaxb
     * @throws JAXBException excepcion io
     */
    public void mainData(String[] args) throws IOException, JAXBException {

        System.out.println("\n*************************\n" +
                "CSV" +
                "\n*************************");

        System.out.println("\n*** METEO ***\n" +
                MeteoReader.readDataOfPath(METE0CSV));

        System.out.println("\n*** STATION ***\n" +
                StationReader.readDataOfPath(STATIONCSV));

        System.out.println("\n*************************\n" +
                "XML CREATE ( JDOM )" +
                "\n*************************");
        JdomController jdomController = JdomController.getInstance();

        jdomController.initData("temperatura");
        jdomController.generateXML("temperatura");


        System.out.println("\n*************************\n" +
                "XML READ ( JAXB )" +
                "\n*************************");

        JaxbController jaxbController = JaxbController.getInstance();
        Temperatura temperatura = jaxbController.loadData(TEMPERAXML);
        System.out.println("Ejemplo: " + temperatura.getMun005().getRadiacion_solar().getMax());
        System.out.println("para que veas que funciona , accede al maximo de Radiacion Solar del municipio id= 5 ");


        System.out.println("\n*************************\n" +
                "CREATE XML MEDICIONES ( JDOM )" +
                "\n*************************");

        jdomController.loadMediciones(ciudad);


        System.out.println("*************************\n" +
                "CREATE INFORME MARKDOWN " +
                "\n*************************");

        File md = new File(args[1]);
        if (md.exists()) {
            System.out.println("Creando markdown de " + ciudad);
        } else {
            md.mkdir();
            System.out.println("");
            System.out.println("Directorio del markdown generado con éxito");
        }
        jdomController.loadMD(args[1]);
        System.out.println("Markdown creado de " + ciudad);

    }

    /**
     * Metodo que comprueba que el nombre de la ciudad sea valido
     *
     * @param nombre Nombre ciudad/municipio
     * @return boolean dependiendo si es valido o no el nombre
     */
    public boolean comprobarNombre(String nombre) {
        Boolean nombreCiudad = false;
        StationUtil stationUtil = new StationUtil(StationReader.readDataOfPath(STATIONCSV));
        List<Station> stations = StationUtil.dataStation(nombre);
        for (Station station : stations) {
            if (station.getNombre().equalsIgnoreCase(nombre)) {
                this.ciudad = station.getNombre();
                nombreCiudad = true;
            }
        }
        return nombreCiudad;

    }


}
