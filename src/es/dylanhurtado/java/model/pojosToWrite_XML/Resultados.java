package model.pojosToWrite_XML;


import model.pojosToWrite_XML.Resultado;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultados")
public class Resultados {
    Resultado resultado;

    @XmlElement(name="resultado")
    public Resultado getResultado() {
        return this.resultado;
    }

    public String toString(){
        return resultado.toString();
    }
}