package model.pojosToWrite_XML;

import model.pojosToRead_XML.Medida;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "datos_meteorologicos")

public class DataMeteo {

    private Medida velocidad_del_viento;
    private Medida radiacion_solar;
    private Medida temperatura;
    private Medida direccion_del_viento;
    private Medida humedad_relativa;
    private Medida presion_atmosferica;
    private Medida precipitacion;
    private List<Medida> medidas;


    //Constructors
    public DataMeteo(Medida velocidad_del_viento, Medida radiacion_solar, Medida temperatura, Medida direccion_del_viento, Medida humedad_relativa, Medida presion_atmosferica, Medida precipitacion) {
        this.velocidad_del_viento = velocidad_del_viento;
        this.radiacion_solar = radiacion_solar;
        this.temperatura = temperatura;
        this.direccion_del_viento = direccion_del_viento;
        this.humedad_relativa = humedad_relativa;
        this.presion_atmosferica = presion_atmosferica;
        this.precipitacion = precipitacion;
        medidas = List.of(velocidad_del_viento, radiacion_solar, temperatura, direccion_del_viento,
                humedad_relativa, presion_atmosferica, precipitacion);
    }

    public DataMeteo() {
    }

//Getters And Setters


    @XmlElementWrapper(name = "datos_meteorologicos")
    public List<Medida> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<Medida> medidas) {
        this.medidas = medidas;
    }


    public Medida getVelocidad_del_viento() {
        return velocidad_del_viento;
    }

    public void setVelocidad_del_viento(Medida velocidad_del_viento) {
        this.velocidad_del_viento = velocidad_del_viento;
    }

    public Medida getRadiacion_solar() {
        return radiacion_solar;
    }

    public void setRadiacion_solar(Medida radiacion_solar) {
        this.radiacion_solar = radiacion_solar;
    }

    public Medida getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Medida temperatura) {
        this.temperatura = temperatura;
    }

    public Medida getDireccion_del_viento() {
        return direccion_del_viento;
    }

    public void setDireccion_del_viento(Medida direccion_del_viento) {
        this.direccion_del_viento = direccion_del_viento;
    }

    public Medida getHumedad_relativa() {
        return humedad_relativa;
    }

    public void setHumedad_relativa(Medida humedad_relativa) {
        this.humedad_relativa = humedad_relativa;
    }

    public Medida getPresion_atmosferica() {
        return presion_atmosferica;
    }

    public void setPresion_atmosferica(Medida presion_atmosferica) {
        this.presion_atmosferica = presion_atmosferica;
    }

    public Medida getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(Medida precipitacion) {
        this.precipitacion = precipitacion;
    }
}


