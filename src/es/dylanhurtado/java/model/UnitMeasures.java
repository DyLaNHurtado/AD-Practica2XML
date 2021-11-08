package model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UnitMeasures {

    private Map<String,String> measures = new HashMap<>();

    private static UnitMeasures unitMeasures=null;

    private UnitMeasures(){
        initMap();
    }

    public static UnitMeasures getInstance(){
        if (unitMeasures==null){
            unitMeasures = new UnitMeasures();
        }
        return unitMeasures;
    }

    public void initMap(){
        measures.put("Velocidad del viento","m/s");
        measures.put("Dirección del viento","Grd");
        measures.put("Temperatura","ºc");
        measures.put("Humedad relativa","%");
        measures.put("Presión atmosferica","mbar");
        measures.put("Radiacion solar","W/m2");
        measures.put("Precipitacion","l/m2");
    }
}
