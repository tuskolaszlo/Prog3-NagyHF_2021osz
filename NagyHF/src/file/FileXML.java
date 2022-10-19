package file;
import game.*;
import frames.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FileXML extends DefaultHandler {
    private HashMap<String,Integer> tagek = new HashMap<>();
    private List<HashMap<String,String>> lehetsegesKorok = new ArrayList<>();
    private static List<String> korok = new ArrayList<>();
    private HashMap<String,String> hmap;

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(tagek.containsKey(qName)){
            tagek.put(qName, tagek.get(qName)+1);
        }
        else{
            tagek.put(qName,1);
        }

        if(qName.equals("kor")){
            hmap = new HashMap<>();
            for(int i = 0; i < attributes.getLength();i++){
                hmap.put(attributes.getQName(i),attributes.getValue(i));
            }
        }

        if(qName.equals("tag")){
            hmap.put(attributes.getValue(0),attributes.getValue(1));
        }
    }
    @Override
    public void endDocument() throws SAXException {
        for(HashMap<String,String> i : lehetsegesKorok){
            String adatok = i.get("feher_j") +";"+ i.get("fekete_j") +";"+ i.get("ido") +";"+ i.get("nyertes");
            korok.add(adatok);
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("kor")){
            lehetsegesKorok.add(hmap);
        }
    }
    
    
    public static void fajlbaMentes(String fajlnev) {
        File f = new File(fajlnev);
        
        if(f.exists() && korok.size()==0) {
        	DefaultHandler h = new FileXML();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser p = factory.newSAXParser();
                p.parse(new java.io.File(fajlnev), h);
            } catch (Exception e) {e.printStackTrace();}
        }
        	

        //https://www.journaldev.com/1211/jdom-write-xml-file-example-object
        Document doc = new Document();
        doc.setRootElement(new Element("malom_eredmenyek"));
        int i = 0;
        for (String s : korok) {
            String[] adatok = s.split(";");
            
            for (String string : adatok) {
				System.out.print(string + " ");
			}

            Element kor = korHozzaad(adatok, i++);
            doc.getRootElement().addContent(kor);
        }
        String[] uj = Game.nyertesekLekerdezese();
        Element kor = korHozzaad(uj, i);
        doc.getRootElement().addContent(kor);

        XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
        try {
            xout.output(doc, new FileOutputStream(f));
            korok.add(uj[0]+";"+uj[1]+";"+uj[2]+";"+uj[3]);
            StatFrame.korokMegjelenitese(uj);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    private static Element korHozzaad(String[] s, int i){    	
        Element kor = new Element("kor");
        kor.setAttribute("id",""+i);
        Element feherE = new Element("tag")
                        .setAttribute("k", "feher_j")
                        .setAttribute("v", s[0]);
        Element feketeE = new Element("tag")
                        .setAttribute("k", "fekete_j")
                        .setAttribute("v", s[1]);
        Element idoE = new Element("tag")
                        .setAttribute("k", "ido")
                        .setAttribute("v", s[2]);
        Element nyertE = new Element("tag")
                        .setAttribute("k", "nyertes")
                        .setAttribute("v", s[3]);
        kor.addContent(feherE);
        kor.addContent(feketeE);
        kor.addContent(idoE);
        kor.addContent(nyertE);
        return kor;
    }
    public static int getKorokSzama() { return korok.size(); }
    public static String getKor(int i){ return korok.get(i); }
}
