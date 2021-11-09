package model.pojosToWrite_XML;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="resultados")
public class Resultados {
    private List<Resultado> resultadoList;

    public Resultados(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    public Resultados() {
    }


    @XmlElement(name="resultado")
    public List<Resultado> getResultadoList() {
        return this.resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    public String toString() {
        return "Resultados{" +
                "resultadoList=" + resultadoList +
                '}';
    }
}