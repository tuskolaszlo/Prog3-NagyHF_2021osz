package tests;
import java.awt.Color;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.*;
import org.xml.sax.helpers.DefaultHandler;

import elements.Player;
import file.FileXML;
import game.Game;

public class FileTest {

	@Test
	public void iras() {
		Game game = new Game(new Player("Test Játékos_Fehér", Color.white), new Player("Test Játékos_Fekete", Color.black));	
		FileXML.fajlbaMentes("teszt.xml");
	}
	
	@Test
	public void beolvasas() {
		DefaultHandler h = new FileXML();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser p = factory.newSAXParser();
            p.parse(new java.io.File("teszt.xml"), h);
        } catch (Exception e) {e.printStackTrace();}
	}
	
}
