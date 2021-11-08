package service;

import model.Station;

import java.util.List;
import java.util.stream.Collectors;

public class StationService {

    List<Station> data;

    public StationService(List<Station> data){
        this.data=data;
    }

    public List<Station> dataStation(String city){

        return data.stream().filter(x -> x.getNombre().equalsIgnoreCase(city)).collect(Collectors.toList());
    }

    public String dataStringStation(List<Station> stations){
        if(stations.isEmpty()){return " ** No hay estaciones asociadas ** ";}
        else {
            StringBuilder s = new StringBuilder("");
            stations.forEach(x->{
                s.append(x.getNombre()).append(" ").append(x.getZona()).append(" (").append(x.getCodigo()).append("),");
            });
            return s.toString();
        }}

        public List<String> getCodeCiudades(){
            return data.stream().map(Station::getCodigo).sorted().distinct().collect(Collectors.toList());
    }
}

