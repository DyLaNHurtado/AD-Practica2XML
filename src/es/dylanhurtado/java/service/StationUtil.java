package service;

import model.pojos_CSV_To_XML.Station;

import java.util.List;
import java.util.stream.Collectors;

public class StationUtil {

    static List<Station> data;

    public StationUtil(List<Station> data) {
        this.data = data;
    }

    /**
     * Metedo que sirve para filtrar teniendo el nombre de la ciudad
     * @param city nombre ciudad o municipio
     * @return devuelve una lista con el nombre que coincida con el pasado por parametro
     */
    public static List<Station> dataStation(String city) {

        return data.stream().filter(x -> x.getNombre().equalsIgnoreCase(city)).collect(Collectors.toList());
    }

    /**
     * Metodo usado para obtener todos los codigos de las ciudades sin repetir y ordenados
     * @return Lista de codigos ciudades
     */
    public List<String> getCodeCiudades() {
        return data.stream().map(Station::getCodigo).sorted().distinct().collect(Collectors.toList());
    }
}

