package model.pojos_CSV_To_XML;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Station {

    private String codigo, zona, nombre;

    @Override
    public String toString() {
        return "Station{" +
                "codigo='" + codigo + '\'' +
                ", zona='" + zona + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
