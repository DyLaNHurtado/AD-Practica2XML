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
     * Almacenar expresion xpath
     *
     * @param xpath expresion
     */
    public void setExpr(String xpath) {
        expr = factory.compile(xpath, Filters.element());
    }

    /**
     * Almacenar el documento
     *
     * @param data documento
     */
    public void setDocumento(Document data) {
        this.data = data;
    }

    /**
     * Obtenemos las consultas
     *
     * @return List<Element> elementos filtrados
     */
    public Element consultasXpath(Element padre) {
        List<Element> elementos = expr.evaluate(data);
        elementos.forEach(elemento -> {
            padre.addContent(new Element(elemento.getName()));
            List<Element> hijos = elemento.getChildren();
            Element hijo = padre.getChild(elemento.getName());
            for (int i = 0; i < hijos.size(); i++) {
                hijo.addContent(new Element(hijos.get(i).getName()).setText(hijos.get(i).getText()));
            }
        });
        return padre;
    }

}