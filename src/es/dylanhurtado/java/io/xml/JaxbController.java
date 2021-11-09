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

    /**
     * Patron Singleton
     * @return instacia de la clase
     */
    public static JaxbController getInstance() {
        if (controller == null)
            controller = new JaxbController();
        return controller;
    }

    /**
     * Metodo que carga los datos de temperatura.xml a objetos gracias a JAXB
     * @param uri Direccion archivo
     * @return Objeto clase Temperatura
     * @throws JAXBException
     */
    public Temperatura loadData(String uri) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Temperatura.class,Municipio.class);
        this.unmarshaller = context.createUnmarshaller();
        Temperatura temperatura = (Temperatura) this.unmarshaller.unmarshal(new File(uri));
        System.out.println("Leyendo -> "+ uri + "...\n Archivo leido correctamente");
        return temperatura;
        }

}

