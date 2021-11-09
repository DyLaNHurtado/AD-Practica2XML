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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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


    public static JdomController getInstance() {
        if (controller == null)
            controller = new JdomController();
        return controller;
    }

    //CSV TO XML (temperatura.xml)
    public void generateXML(String fileName) throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data, new FileWriter(DATA_URI + File.separator + fileName + ".xml"));
    }

    private XMLOutputter xmlFormat() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    public void initData(String nombre) throws IOException {
        // Documento vacío
        this.data = new Document();
        // Nodo raíz
        if (nombre.equals("temperatura")) {
            this.data.setRootElement(new Element("temperatura"));
            initDocumentCities();
            initChildrens();
        } else {
            this.data.setRootElement(new Element("resultados"));
            initMediciones(nombre, getId(nombre));
            xmlSafer("mediciones");
        }
    }

    private int getId(String nombre) {
        int id = 0;

        List<Station> stations = StationUtil.dataStation(nombre);
        for (Station station : stations) {
            if (station.getNombre().equalsIgnoreCase(nombre)) {
                id = Integer.parseInt(station.getCodigo().substring(2,5));
            }
        }

        return id;
    }


    private void initDocumentCities() {
        List<Station> stations = StationReader.readDataOfPath(STATIONCSV);
        StationUtil stationUtil = new StationUtil(stations);
        Element root = data.getRootElement();
        for (int i = 0; i < stations.size(); i++) {
            root.addContent(new Element("municipio").setAttribute("id", String.valueOf(Integer.parseInt(stationUtil.getCodeCiudades().get(i).substring(2, 5)))));
        }
    }

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

    private void setCityData(int i, Element child, MeteoUtil list) {
        child.addContent(new Element("minDate").setText(list.getDataFechaIncio(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("maxDate").setText(list.getDataFechaFin(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("min").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(1))));
        child.addContent(new Element("max").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(2))));
        child.addContent(new Element("average").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i), list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(0))));

    }


    //



    public Document loadDocument(String name) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        if (Objects.equals(name, "temperatura")) {
            return (Document) builder.build(new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + name + ".xml"));
        }else{
            return (Document) builder.build(new File(System.getProperty("user.dir") + File.separator + "db" + File.separator + name + ".xml"));
        }
    }



    private Element generaResultado(String nombreArchivo, int id) {
        XPathController xpath = XPathController.getInstance();
        Element root = new Element(nombreArchivo);
        try {
            xpath.setDocumento(loadDocument(nombreArchivo));
            xpath.setExpr("//" + nombreArchivo + "/municipio[@id=" + id + "]/*");
            root = xpath.consultasXpath(root);
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return root;
    }

    private void xmlSafer(String nombre) throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data, new FileWriter(DB_URI + File.separator + nombre + ".xml"));
    }


    private void initMediciones(String nombre, int id) {
        Element root = data.getRootElement();
        String creacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        root.addContent(new Element("resultado").setAttribute("id", UUID.randomUUID().toString()));
        if (root.getChildren().size() == 1) {
            root.getChild("resultado").addContent(new Element("ciudad").setText(nombre));
            root.getChild("resultado").addContent(new Element("fecha_creacion").setText(creacion));
            root.getChild("resultado").addContent(new Element("tiempo_ejecucion").setText(String.valueOf((System.currentTimeMillis() - Main.init) / 1000)).setAttribute("unidad", "segundos"));
            root.getChild("resultado").addContent(generaResultado("temperatura", id));
            solucion(root);
        } else {
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("ciudad").setText(nombre));
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("fecha_creacion").setText(creacion));
            root.getChildren().get(root.getChildren().size() - 1).addContent(new Element("tiempo_ejecucion").setText(String.valueOf((System.currentTimeMillis() - Main.init) / 1000)).setAttribute("unidad", "segundos"));
            root.getChildren().get(root.getChildren().size() - 1).addContent(generaResultado("temperatura", id));
            solucion(root.getChildren().get(root.getChildren().size() - 1));
        }
    }

    public void agregaMediciones(String nombre) throws IOException, JDOMException {
        this.data = loadDocument("mediciones");
        initMediciones(nombre, getId(nombre));
        try {
            xmlSafer("mediciones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void solucion(Element root) {
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

    public void generaMediciones(String nombre) throws IOException {
        initData(nombre);
        try {
            xmlSafer("mediciones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
