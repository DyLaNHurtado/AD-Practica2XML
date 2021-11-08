package model.pojosToWrite_XML;


import com.sun.xml.txw2.annotation.XmlAttribute;
import model.pojos_CSV_To_XML.Meteo;

public class Resultado {
    private String id;
    private String ciudad;
    private String fecha_creacion;
    private String tiempo_ejecucion;
    private Meteo meteo;

    @XmlAttribute()
    public String getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public String getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public Meteo getMeteo() {
        return meteo;
    }


    public String toString(){
        return "id "+id+" ciudad "+ciudad+" fecha_creacion "+fecha_creacion;
    }
}