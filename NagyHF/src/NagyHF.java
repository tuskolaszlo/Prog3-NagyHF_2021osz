//NagyHáziFeladat - Malom
//Mérnökinfó - Prog3 - 2021/őszi félév
//Készítette: Tuskó László István (CGTRRV)

import frames.*;
import file.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
//import tests.*;

public class NagyHF {
    public static void main(String[] args) throws Exception {
        System.out.println("Nagy Házi Feladat - Malom");

        File f = new File("eredmenyek.xml");
        if(f.exists()){
            DefaultHandler h = new FileXML();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser p = factory.newSAXParser();
                p.parse(new java.io.File("eredmenyek.xml"), h);
            } catch (Exception e) {e.printStackTrace();}
        }    
        NewGFrame ngf = new NewGFrame();
    }
}
