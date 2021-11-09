package io.xml;

import io.csv.MeteoReader;
import io.csv.StationReader;
import model.pojos_CSV_To_XML.Station;
import model.pojos_CSV_To_XML.UnitMeasures;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import service.MeteoUtil;
import service.StationUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

import main.Main;

public class JdomController {
    private static JdomController controller;
    private static final String DATA_URI = System.getProperty("user.dir") + File.separator + "data";
    private static final String DB_URI = System.getProperty("user.dir") + File.separator + "db";
    private Document data;
    final static String RESOURCES = "src" + File.separator + "es" + File.separator + "dylanhurtado" + File.separator + "resources";
    final static Path METE0CSV = Path.of(RESOURCES + File.separator + "calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES + File.separator + "calidad_aire_estaciones.csv");

    private JdomController() {
    }

    /**
     * Patron Singleton
     * @return instancia clase
     */
    public static JdomController getInstance() {
        if (controller == null)
            controller = new JdomController();
        return controller;
    }

    //CSV TO XML (temperatura.xml)

    /**
     *  Guarda el archivo xml en la uri de DATA_URI
     * @param fileName nombre archivo
     * @throws IOException exception io
     */
    public void generateXML(String fileName) throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data, new FileWriter(DATA_URI + File.separator + fileName + ".xml"));
    }

    /**
     * Da formato bonito a los xml para que salgan ordenados
     * @return xmlOutput
     */
    private XMLOutputter xmlFormat() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    /**
     * Metodo que incializa dependiendo del nombre si es temperatura o si es otra cosa
     * @param nombre nombre archivo
     * @throws IOException
     */
    public void initData(String nombre) throws IOException {
        // Documento vacío
        this.data = new Document();
        // Nodo raíz
        if (nombre.equals("temperatura")) {
            this.data.setRootElement(new Element("temperatura"));
            initDocumentCities();
            initChildrens();
            printFileInConsole();
        } else {
            this.data.setRootElement(new Element("resultados"));
            initMediciones(nombre, getId(nombre));
            xmlSafer("mediciones");
            printFileInConsole();
        }
    }

    /**
     * Metodo para obtener la id de las estaciones
     * @param nombre nombre estacion
     * @return int id
     */
    private int getId(String nombre) {
        int id = 0;

        List<Station> stations = StationUtil.dataStation(nombre);
        for (Station station : stations) {
            if (station.getNombre().equalsIgnoreCase(nombre)) {
                id = Integer.parseInt(station.getCodigo().substring(2, 5));
            }
        }

        return id;
    }

    /**
     * Metodo que incializa los elementos municipio del documento temperatura.xml
     */
    private void initDocumentCities() {
        List<Station> stations = StationReader.readDataOfPath(STATIONCSV);
        StationUtil stationUtil = new StationUtil(stations);
        Element root = data.getRootElement();
        for (int i = 0; i < stations.size(); i++) {
            root.addContent(new Element("municipio").setAttribute("id", String.valueOf(Integer.parseInt(stationUtil.getCodeCiudades().get(i).substring(2, 5)))));
        }
    }

    /**
     * Metodo que incializa el contenido de el elemento municipio
     */
    private void initChildrens() {
        Map<String, String> measures = UnitMeasures.getInstance().getMeasures();
        MeteoUtil list = new MeteoUtil(MeteoReader.readDataOfPath(METE0CSV));
        Set<String> keysMap = measures.keySet();
        List<String> keysList = new ArrayList<>(keysMap);
        Element root = data.getRootElement();
        List<Element> cities = root.getChildren();
        cities.forEach(city -> {
                    for (int i = 0; i < keysMap.size(); i++) {

                        city.addContent(new Element(keysList.get(i).toLowerCase(Locale.ROOT).replace(" ", "_")));
                        Element child = city.getChild(keysList.get(i).toLowerCase(Locale.ROOT).replace(" ", "_"));

                        setCityData(i, child, list);
                    }

                }
        );
    }

    /**
     *
     * @param i Variable de un for que representa el valor del 1-7 que son el tipo de numero de medida que hay
     * @param child Elemento hijo
     * @param list Lista de objetos de clase Meteo
     */
    private void setCityData(int i, Element child, MeteoUtil list) {
        child.addContent(new Element("minDate").setText(list.getDataFechaIncio(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("maxDate").setText(list.getDataFechaFin(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("min").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(1))));
        child.addContent(new Element("max").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(2))));
        child.addContent(new Element("average").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(0))));

    }



    //

    /**
     *  Construye el documento dicho por parametro temperatura/otro
     * @param name nombre archivo
     * @return Devuelve un Document
     * @throws IOException
     * @throws JDOMException
     */
    public Document loadDocument(String name) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        if (Objects.equals(name, "temperatura")) {
            return (Document) builder.build(new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + name + ".xml"));
        } else {
            return (Document) builder.build(new File(System.getProperty("user.dir") + File.separator + "db" + File.separator + name + ".xml"));
        }
    }

    /**
     * Metodo que utiliza una consulta de XPath para devolver un elemento root
     * @param nombreArchivo nombre del archivo
     * @param id identificador municipio
     * @return Element root
     */
    private Element generaResultado(String nombreArchivo, int id) {
        XPathController xpath = XPathController.getInstance();
        Element root = new Element(nombreArchivo);
        try {
            xpath.setDocumento(loadDocument(nombreArchivo));
            xpath.setExpresion("//" + nombreArchivo + "/municipio[@id=" + id + "]/*");
            root = xpath.consultsXpath(root);
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return root;
    }

    /**
     * Guarda el archivo en la ruta db
     * @param nombre nombre arhivo a guardar
     * @throws IOException
     */
    private void xmlSafer(String nombre) throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data, new FileWriter(DB_URI + File.separator + nombre + ".xml"));
    }

    /**
     * Printea el contenido del documento data por pantalla
     * @throws IOException
     */
    public void printFileInConsole() throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data,System.out);
    }

    /**
     * Metodo que inicaliza las mediciones que son los hijos del element resultado
     * @param nombre nombre ciudad
     * @param id id ciudad
     */
    private void initMediciones(String nombre, int id) {
        Element root = data.getRootElement();
        String creacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        root.addContent(new Element("resultado").setAttribute("id", UUID.randomUUID().toString()));
        if (root.getChildren().size() == 1) {
            root.getChild("resultado").addContent(new Element("ciudad").setText(nombre));
            root.getChild("resultado").addContent(new Element("fecha_creacion").setText(creacion));
            root.getChild("resultado").addContent(new Element("tiempo_ejecucion").setText(String.valueOf((System.currentTimeMillis() - Main.init) / 1000)).setAttribute("unidad", "segundos"));
            root.getChild("resultado").addContent(generaResultado("temperatura", id));
            mostrarPantallaUltimoElemento(root);
        } else {
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("ciudad").setText(nombre));
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("fecha_creacion").setText(creacion));
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("tiempo_ejecucion").setText(String.valueOf((System.currentTimeMillis() - Main.init) / 1000)).setAttribute("unidad", "segundos"));
            root.getChildren().get(root.getChildren().size() - 1).addContent(generaResultado("temperatura", id));
            mostrarPantallaUltimoElemento(root.getChildren().get(root.getChildren().size() - 1));
        }
    }

    /**
     * Metodo que permite seguir ampliando la base de datos mediciones.xml
     * @param nombre nombre ciudad
     * @throws IOException
     * @throws JDOMException
     */
    public void agregaMediciones(String nombre) throws IOException, JDOMException {
        this.data = loadDocument("mediciones");
        initMediciones(nombre, getId(nombre));
        try {
            xmlSafer("mediciones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Muestra y escribe el ultimo elemento que agregas
     * @param root Elemento principal
     */
    private void mostrarPantallaUltimoElemento(Element root) {
        Document mediciones = new Document();
        Element padre = new Element("resultados");
        mediciones.setRootElement(padre);

        if (root.getChildren().size() == 1) {
            padre.addContent(new Element("resultado").setAttribute("id", root.getChild("resultado").getAttributeValue("id")));

            List<Element> datos = root.getChild("resultado").getChildren();
            padre.getChild("resultado").addContent(new Element(datos.get(0).getName()).setText(datos.get(0).getText()));
            padre.getChild("resultado").addContent(new Element(datos.get(1).getName()).setText(datos.get(1).getText()));
            padre.getChild("resultado").addContent(new Element(datos.get(2).getName()).setText(datos.get(2).getText())
                    .setAttribute("unidad", datos.get(2).getAttributeValue("unidad")));
            padre.getChild("resultado").addContent(new Element(datos.get(3).getName()));
            //padre.getChild("resultado").addContent(new Element(datos.get(4).getName()));

            List<Element> temperatura = datos.get(3).getChildren();
            temperatura.forEach(m -> {
                padre.getChild("resultado").getChild("temperatura").addContent(new Element(m.getName()));
                for (int i = 0; i < m.getChildren().size(); i++) {
                    padre.getChild("resultado").getChild("temperatura").getChild(m.getName())
                            .addContent(new Element(m.getChildren().get(i).getName()).setText(m.getChildren().get(i).getText()));
                }
            });
        } else {
            padre.addContent(new Element("resultado").setAttribute("id", root.getAttributeValue("id")));

            List<Element> datos = root.getChildren();
            padre.getChild("resultado").addContent(new Element(datos.get(0).getName()).setText(datos.get(0).getText()));
            padre.getChild("resultado").addContent(new Element(datos.get(1).getName()).setText(datos.get(1).getText()));
            padre.getChild("resultado").addContent(new Element(datos.get(2).getName()).setText(datos.get(2).getText())
                    .setAttribute("unidad", datos.get(2).getAttributeValue("unidad")));
            padre.getChild("resultado").addContent(new Element(datos.get(3).getName()));

            List<Element> temperatura = datos.get(3).getChildren();
            temperatura.forEach(m -> {
                padre.getChild("resultado").getChild("temperatura").addContent(new Element(m.getName()));
                for (int i = 0; i < m.getChildren().size(); i++) {
                    padre.getChild("resultado").getChild("temperatura").getChild(m.getName())
                            .addContent(new Element(m.getChildren().get(i).getName()).setText(m.getChildren().get(i).getText()));
                }
            });
        }
        try {
            XMLOutputter xmlOutput = xmlFormat();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(mediciones, System.out);
            xmlOutput.output(mediciones, new FileWriter(DB_URI + File.separator + "mediciones.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el archivo mediciones.xml si no existe lo crea y agrega y genera las mediciones
     * @param nombre
     * @throws IOException
     */
    public void loadMediciones(String nombre) throws IOException {
        File mediciones = new File(System.getProperty("user.dir") + File.separator + "db" + File.separator + "mediciones.xml");
        if (!mediciones.exists()) {
            generaMediciones(nombre);
        } else {
            try {
                agregaMediciones(nombre);
            } catch (IOException | JDOMException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que genera las mediciones
     * @param nombre nombre archivo
     * @throws IOException exception io
     */
    public void generaMediciones(String nombre) throws IOException {
        initData(nombre);
        try {
            xmlSafer("mediciones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //

    /**
     * Metodo que crea el archivo markdown y muestra su estructura por pantalla gracias al Document
     * @param uri Direccion del archivo
     */
    public void loadMD(String uri) {
        File md = null;
        PrintWriter writer = null;

        try {
            Element padre = null;
            if (data.getRootElement().getChildren().size() == 1) {
                Element root = data.getRootElement();
                padre = root.getChild("resultado");
            } else {
                padre = data.getRootElement().getChildren().get(data.getRootElement().getChildren().size() - 1);
            }

            String fecha = new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
            md = new File(uri + File.separator + "informe_" + padre.getChild("ciudad").getText().toLowerCase().replace(" ", "") + "_" + fecha + ".md");
            writer = new PrintWriter(new FileWriter(md, StandardCharsets.UTF_8));

            writer.println("## INFORME XML ");
            writer.println("- **ID del informe:** " + padre.getAttributeValue("id"));
            writer.println("- **Fecha de creación del informe:** " + padre.getChild("fecha_creacion").getText());
            writer.println("- **Tiempo de ejecución:** " + padre.getChild("tiempo_ejecucion").getText() + " " + padre.getChild("tiempo_ejecucion").getAttributeValue("unidad"));
            writer.println("-  Datos de " + padre.getChild("ciudad").getText() + ": ");
            writer.println(" ### DATOS METEOROLÓGICOS:");
            List<Element> temperatura = padre.getChild("temperatura").getChildren();
            for (Element m : temperatura) {
                writer.println(" - " + m.getName().replace("_", " ").toUpperCase());
                writer.println("* **Fecha del mínimo:** " + m.getChild("minDate").getText());
                writer.println("* **Fecha del máximo:** " + m.getChild("maxDate").getText());
                writer.println("* **Mínimo:** " + m.getChild("min").getText());
                writer.println("* **Maximo:** " + m.getChild("max").getText());
                writer.println("* **Media**: " + m.getChild("average").getText());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    printFileInConsole();
                    writer.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
