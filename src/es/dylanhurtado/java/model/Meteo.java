package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meteo {
    private String provincia,municipio,estacion,magnitud,puntoMuestreo,anio,mes,dia,
            h01,v01,h02,v02,h03,v03,h04,v04,h05,v05,h06,v06,h07,v07,
            h08,v08,h09,v09,h10,v10,h11,v11,h12,v12,h13,v13,
            h14,v14,h15,v15,h16,v16,h17,v17,h18,v18,h19,v19,
            h20,v20,h21,v21,h22,v22,h23,v23,h24,v24;
}
