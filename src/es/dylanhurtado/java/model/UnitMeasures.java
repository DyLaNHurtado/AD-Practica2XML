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

    private void initMap(){
        measures.put("1","ug/m³");
        measures.put("6","mg/m³");
        measures.put("7","ug/m³");
        measures.put("8","ug/m³");
        measures.put("9","ug/m³");
        measures.put("10","ug/m³");
        measures.put("12","ug/m³");
        measures.put("14","ug/m³");
        measures.put("20","ug/m³");
        measures.put("22","ug/m³");
        measures.put("30","ug/m³");
        measures.put("42","mg/m³");
        measures.put("44","mg/m³");
        measures.put("431","ug/m³");
        measures.put("81","m/s");
        measures.put("82","Grd");
        measures.put("83","ºc");
        measures.put("86","%");
        measures.put("87","mbar");
        measures.put("88","W/m2");
        measures.put("89","l/m2");
    }
}
