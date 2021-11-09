package main;

import service.Facade;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static long init = System.currentTimeMillis();

    public static void main(String[] args) {

        Facade facade = Facade.getInstance();
        try {
            facade.mainRun(args);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
