package model.pojosToWrite_XML;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="resultado")
@XmlType(propOrder = {
        "id","ciudad","fecha_creacion","tiempo_ejecucion", "datos_meteorologicos"
})
public class Resultado {
    private String id;
    private String ciudad;
    private String fecha_creacion;
    private String tiempo_ejecucion;
    private DataMeteo datos_meteorologicos;

    @XmlAttribute(name = "id")
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

    public DataMeteo getDatos_meteorologicos() {
        return datos_meteorologicos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setTiempo_ejecucion(String tiempo_ejecucion) {
        this.tiempo_ejecucion = tiempo_ejecucion;
    }

    public void setDatos_meteorologicos(DataMeteo datos_meteorologicos) {this.datos_meteorologicos = datos_meteorologicos;}

    public String toString(){
        return "id "+id+" ciudad "+ciudad+" fecha_creacion "+fecha_creacion;
    }


}