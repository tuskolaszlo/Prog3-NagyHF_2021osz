package frames;
import file.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.util.*;


public class StatFrame extends JFrame {
    private JPanel P_fo;
    private JPanel P_fejl;
    private static JPanel P_korork;

    public StatFrame() {
        super("NagyHF - Malom -- Eredmények");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        P_fo = new JPanel(new GridLayout(2, 1));
        this.add(P_fo);

        P_fejl = new JPanel(new GridLayout(1, 3));
        P_fejl.setBackground(Color.lightGray);
        P_fejl.add(new JLabel("FEHÉR \nJátékos"));
        P_fejl.add(new JLabel("- Időtartam -"));
        P_fejl.add(new JLabel("FEKETE \nJátékos"));

        P_fo.add(P_fejl);

        
        int db = FileXML.getKorokSzama();
        P_korork = new JPanel(new GridLayout(db, 1));
        P_fo.add(P_korork);
        for (int i = 0; i < db; i++) {
        	String[] adatok = FileXML.getKor(i).split(";");
        	korokMegjelenitese(adatok);
        }

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void korokMegjelenitese(String[] adatok){
    	JPanel p_ujKor = new JPanel(new GridLayout(1, 3));
        P_korork.add(p_ujKor);

        //Fehér adatok
        JPanel p_fehFel = new JPanel(new FlowLayout());
        p_ujKor.add(p_fehFel);
        JLabel L_fehfel = new JLabel(adatok[0]);
        p_fehFel.add(L_fehfel);
        //Köridő
        JLabel L_idoFel = new JLabel(" - "+adatok[2]+" - ");
        p_ujKor.add(L_idoFel);
        //Fekete adatok
        JPanel p_fekFel = new JPanel(new FlowLayout());
        p_ujKor.add(p_fekFel);
        JLabel L_fekfel = new JLabel(adatok[1]);
        p_fekFel.add(L_fekfel);
        //Nyertes
        JLabel L_nyert = new JLabel("(Nyertes)");
        if(adatok[3].equals("FEHÉR")) p_fehFel.add(L_nyert);
        else p_fekFel.add(L_nyert);
    }
}
