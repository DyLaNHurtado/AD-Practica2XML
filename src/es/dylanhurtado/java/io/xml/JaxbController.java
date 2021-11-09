package io.xml;

import model.pojosToRead_XML.Municipio;
import model.pojosToRead_XML.Temperatura;
import model.pojosToWrite_XML.Resultados;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.UUID;

public class JaxbController {
    private static JaxbController controller;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;
    private Resultados resultados;

    private JaxbController() {
        String uri = "data";
    }


    public static JaxbController getInstance() {
        if (controller == null)
            controller = new JaxbController();
        return controller;
    }

    public Temperatura loadData(String uri) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Temperatura.class,Municipio.class);
        this.unmarshaller = context.createUnmarshaller();
        Temperatura temperatura = (Temperatura) this.unmarshaller.unmarshal(new File(uri));
        System.out.println("Leyendo -> "+ uri + "...\n Archivo leido correctamente");
        return temperatura;
        }

    public void initData(Resultados resultados) throws JAXBException {
        this.resultados=resultados;
        JAXBContext context = JAXBContext.newInstance(Resultados.class);
        this.marshaller = context.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }


    /**
     * Escribe un fichero XML con el contenido de los datos
     *
     * @param uri
     * @throws JAXBException
     */

    public void writeXMLFile(String uri) throws JAXBException {
        this.marshaller.marshal(resultados, new File(uri));
        System.out.println("Fichero XML generado con éxito");

    }
    public void printXML() throws JAXBException {
        this.marshaller.marshal(resultados, System.out);
    }
}

