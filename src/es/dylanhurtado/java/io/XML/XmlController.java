package io.XML;

import io.CSV.MeteoReader;
import io.CSV.StationReader;
import model.Station;
import model.UnitMeasures;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import service.MeteoService;
import service.StationService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class XmlController {
    private static XmlController controller;
    private static final String uri = "data";
    private Document data;
    final static String RESOURCES = "src" + File.separator + "es" + File.separator + "dylanhurtado" + File.separator + "resources";
    final static Path METE0CSV = Path.of(RESOURCES + File.separator + "calidad_aire_datos_meteo_mes.csv");
    final static Path STATIONCSV = Path.of(RESOURCES + File.separator + "calidad_aire_estaciones.csv");

    private XmlController() {
    }


    public static XmlController getInstance() {
        if (controller == null)
            controller = new XmlController();
        return controller;
    }


    public void generateXML(String fileName) throws IOException {
        XMLOutputter xmlOutput = xmlFormat();
        xmlOutput.output(data, new FileWriter(uri + File.separator + fileName + ".xml"));
    }

    private XMLOutputter xmlFormat() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    public void initData() {
        // Documento vacío
        this.data = new Document();
        // Nodo raíz
        this.data.setRootElement(new Element("temperatura"));
        initDocumentCities();
        initChildrens();
    }

    private void initDocumentCities() {
        List<Station> stations = StationReader.readDataOfPath(STATIONCSV);
        StationService stationService = new StationService(stations);
        Element root = data.getRootElement();
        for (int i = 0; i < stations.size(); i++) {
            root.addContent(new Element("municipio").setAttribute("id", stationService.getCodeCiudades().get(i).substring(2,5)));
        }
    }

    private void initChildrens() {
        Map<String, String> measures = UnitMeasures.getInstance().getMeasures();
        MeteoService list = new MeteoService(MeteoReader.readDataOfPath(METE0CSV));
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

    private void setCityData(int i, Element child, MeteoService list) {
        child.addContent(new Element("minDate").setText(list.getDataFechaIncio(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("maxDate").setText(list.getDataFechaFin(list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))));
        child.addContent(new Element("min").setText( String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i),list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(1))));
        child.addContent(new Element("max").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i),list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(2))));
        child.addContent(new Element("average").setText(String.valueOf(list.getDataAverageMinMax(list.getListMeasure(list.getPuntosMuestreo().get(i),list.getDataPuntoMuestreo(list.getPuntosMuestreo().get(i)))).get(0))));

    }


}
