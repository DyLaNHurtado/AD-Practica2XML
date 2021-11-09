package io.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.util.List;

public class XPathController {
    private static XPathController instance;
    private Document data;
    private final XPathFactory factory;
    private XPathExpression<Element> expr;

    private XPathController() {
        factory = XPathFactory.instance();
    }

    /**
     * Singleton
     *
     * @return instance instacia de XpathController
     */
    public static XPathController getInstance() {
        if (instance == null) {
            instance = new XPathController();
        }
        return instance;
    }

    /**
     * Metodo para almacenar expresion xpath
     *
     * @param xpath expresion xpath
     */
    public void setExpresion(String xpath) {
        expr = factory.compile(xpath, Filters.element());
    }

    /**
     * Metodo que inicializa el documento data para almacenarlo en variable local
     *
     * @param data documento
     */
    public void setDocumento(Document data) {
        this.data = data;
    }

    /**
     * Metodo que obtiene las consultas realizadas en XPath
     *
     * @return Lista  elementos filtrados
     * @param padre  elemento padre
     */
    public Element consultsXpath(Element padre) {
        List<Element> elementos = expr.evaluate(data);
        elementos.forEach(elemento -> {
            padre.addContent(new Element(elemento.getName()));
            List<Element> hijos = elemento.getChildren();
            Element hijo = padre.getChild(elemento.getName());
            for (Element element : hijos) {
                hijo.addContent(new Element(element.getName()).setText(element.getText()));
            }
        });
        return padre;
    }

}