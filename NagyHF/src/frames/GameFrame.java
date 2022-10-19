package frames;
import elements.*;
import javax.swing.border.Border;
import javax.swing.plaf.synth.Region;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameFrame extends JFrame {

	//Jmenü
    /*private JMenuBar menuBar;
    private JMenu menu, m_stat;
    private JMenuItem mi_kilep, mi_ujjatek;
	private JMenuItem mi_eredm;*/
	
    private JPanel P_Ntabla;
    private JPanel P_tabla;
    
    private JPanel P_feh_sav, P_fek_sav;
    
    //Szint, index
    private static JPanel[][] P_mezok;
	private Color mezo_szin;
    
    //szín, korongok_helye
    private JPanel[][] P_korong_savok;
    
    
    private JPanel P_Feliratok;
    private JLabel L_feh_jnev, L_fek_jnev;
    private JTextArea TF_utasitasok;
    private JLabel L_idomero;
    
    
    public GameFrame(Player feherJ, Player feketeJ) throws HeadlessException{
        super("NagyHF - Malom");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 1000);

        //this.setLayout(new Grid);
        menubar_Inic();

		mezo_szin = Color.blue;
        palya_Inic();
        felhFul_Inic(feherJ.getJatekosNev(), feketeJ.getJatekosNev());
        
        korong_Felrakas(feherJ);
        korong_Felrakas(feketeJ);
        
        /*Timer timer = new Timer(L_idomero);
        timer.start();*/

        this.pack();
        this.setVisible(true);
        //this.setEnabled(false);
        this.setResizable(false);
    }
    
    private void menubar_Inic() {
    	 JMenu menu = new JMenu("Menu");
    	 JMenu m_stat = new JMenu("Stat");
		 JMenuBar menuBar = new JMenuBar();
         menuBar.add(menu);
		 menuBar.add(m_stat);
         this.setJMenuBar(menuBar);
         
         JMenuItem mi_ujjatek = new JMenuItem("Új játék");
         JMenuItem mi_kilep = new JMenuItem("Kilepés");
         JMenuItem mi_eredm = new JMenuItem("Eredmények");
         menu.add(mi_ujjatek);
         menu.add(mi_kilep);
		 m_stat.add(mi_eredm);
         
         mi_ujjatek.addActionListener(new mal_uj());
         //mi_kilep.setActionCommand("exit");
         mi_kilep.addActionListener(new mal1());
		 mi_eredm.addActionListener(new mal_ered());

    }
    
    private void mezo_Inic() {
    	P_mezok = new JPanel[3][8];
    	for (int sz = 0; sz < 3; sz++) {
			for (int i = 0; i < 8; i++) {
				switch (i) {
				case 0:  P_mezok[sz][i] = new Field(sz+1, 1, 1);  break;
				case 1:  P_mezok[sz][i] = new Field(sz+1, 1, 2);  break;
				case 2:  P_mezok[sz][i] = new Field(sz+1, 1, 3);  break;
				case 3:  P_mezok[sz][i] = new Field(sz+1, 2, 1);  break;
				case 4:  P_mezok[sz][i] = new Field(sz+1, 2, 3);  break;
				case 5:  P_mezok[sz][i] = new Field(sz+1, 3, 1);  break;
				case 6:  P_mezok[sz][i] = new Field(sz+1, 3, 2);  break;
				case 7:  P_mezok[sz][i] = new Field(sz+1, 3, 3);  break;
				}
				P_mezok[sz][i].setPreferredSize(new Dimension(45, 45));
				//P_mezok[sz][i].addMouseListener(new MyActionListener());
			}
		}
    }
    
    //Pályát létrehzása 
    private void palya_Inic(){
    	mezo_Inic();
    	
        P_Ntabla = new JPanel(new BorderLayout());
        this.add(P_Ntabla, BorderLayout.WEST);
        
        P_tabla = new JPanel(new GridLayout(15, 15));
        P_Ntabla.add(P_tabla, BorderLayout.CENTER);
        P_tabla.setBackground(Color.gray);
        //Pálya felépítése -----------------------------
        for (int i = 0; i < 15; i++) ures_Inic();
        sor_Inic(1, 1, true);
        sor_Inic(1, 2, true);
        sor_Inic(1, 3, true);
        kozepsoSor_Inic();
        sor_Inic(3, 3, false);
        sor_Inic(3, 2, false);
        sor_Inic(3, 1, false);
        for (int i = 0; i < 15; i++) ures_Inic();
        //-----------------------------------------------
        P_feh_sav = new JPanel(new GridLayout(1, 9, 10, 10));
        P_Ntabla.add(P_feh_sav, BorderLayout.NORTH);
        P_feh_sav.setBackground(Color.gray);
                
        P_fek_sav = new JPanel(new GridLayout(1, 9, 10, 10));
        P_Ntabla.add(P_fek_sav, BorderLayout.SOUTH);
        P_fek_sav.setBackground(Color.gray);
        
        korongT_Inic();
    }
    private void sor_Inic(int sor, int sz, boolean u2d) {
    	int szorzo=1;
    	switch (sz) {
			case 1: szorzo = 5; break; 
			case 2: szorzo = 3; break;
			case 3: szorzo = 1; break;
    	}
    	
    	//u2d -> fentről lefelé haladva pakolja az elemeket
    	if (u2d) {
    		szelek_Inic(sz, true);
        	for(int i = 0; i < 3; i++) {
        		/*P_tabla.add(P_mezok[sz-1][mezoIdx(sor, i)]);
        		P_mezok[sz-1][mezoIdx(sor, i)].setBackground(Color.blue);*/
        		mezo_Inic(sz, mezoIdx(sor, i));
        		
        		for(int j = 0; j < szorzo; j++) {
        			 if(i < 2) ut_Inic(Color.lightGray);
        		}
        	}
        	szelek_Inic(sz, false);
        	ures_Inic();
        	uresSor_Inic(sz);
		}
    	else {
        	uresSor_Inic(sz);
        	ures_Inic();
    		szelek_Inic(sz, true);
        	for(int i = 0; i < 3; i++) {	
        		mezo_Inic(sz, mezoIdx(sor, i));
        		
        		for(int j = 0; j < szorzo; j++) {
       			 	if(i < 2) ut_Inic(Color.lightGray);
        		}
        	}
        	szelek_Inic(sz, false);
		}
    	ures_Inic();
    }
    
    private void mezo_Inic(int sz, int idx) {
    	P_tabla.add(P_mezok[sz-1][idx]);
		P_mezok[sz-1][idx].setBackground(mezo_szin);
    }
    private void ut_Inic(Color c) {
    	JPanel jp = new JPanel();        
        P_tabla.add(jp);
        
        jp.setBackground(c);
    }
    private void ures_Inic() {
    	JPanel jp = new JPanel();
		P_tabla.add(jp);
		jp.setBackground(jp.getParent().getBackground());
    }
    
    private void uresSor_Inic(int sz) {
    	ures_Inic();
    	ut_Inic(Color.lightGray);
    	
    	int plusz_ut_db = sz-1;
    	ArrayList<Integer> p_ut_idx = new ArrayList<>();
    	    	
    	//mezők közötti üres szakaszok, bal széle
    	for (int i = 0; i < 5; i++) {
    		
    		if(i % 2 == 1 && plusz_ut_db > 0) {
    			ut_Inic(Color.lightGray);
    			plusz_ut_db--;
    			p_ut_idx.add(i);
    		}
    		else {
    			ures_Inic();
    		}
		}
    	//középső összekötő út
    	if (sz != 3) 
    		ut_Inic(Color.lightGray);
    	else {
    		ures_Inic();
		}
    	//mezők közötti üres szakaszok, jobb széle
    	for (int i = 0; i < 5; i++) {
    		
    		boolean ut_e = false;
    		
    		for (int j = p_ut_idx.size()-1; j >= 0; j--) {
				if (4-p_ut_idx.get(j) == i) {
					ut_Inic(Color.lightGray);
					ut_e = true;
				}
			}
    		if (!ut_e) {
    			ures_Inic();
			}
		}
    	ut_Inic(Color.lightGray);  
    }
    private void szelek_Inic(int sz, boolean l2r) {
    	//l2r-> balról jobbra haladva 
    	if (l2r) {
    		ures_Inic();
    		for (int i = 0; i < sz-1; i++) {
        		ut_Inic(Color.lightGray);
            	ures_Inic();
    		}
		}
    	else {
    		for (int i = 0; i < sz-1; i++) {
    			ures_Inic();
        		ut_Inic(Color.lightGray);
    		}
		}
    	
    }
    
    private void kozepsoSor_Inic() {
    	ures_Inic();
    	mezo_Inic(1, mezoIdx(2, 0));
    	ut_Inic(Color.lightGray);
    	mezo_Inic(2, mezoIdx(2, 0));
    	ut_Inic(Color.lightGray);
    	mezo_Inic(3, mezoIdx(2, 0));
    	
    	for (int i = 0; i < 3; i++) {
    		JPanel jp = new JPanel();
    		P_tabla.add(jp);
    		jp.setBackground(jp.getParent().getBackground());
    	}
		
    	mezo_Inic(3, mezoIdx(2, 2));
    	ut_Inic(Color.lightGray);
    	mezo_Inic(2, mezoIdx(2, 2));
    	ut_Inic(Color.lightGray);
    	mezo_Inic(1, mezoIdx(2, 2));
    	ures_Inic();
    }
    
    //megadott sor és x-edik elem alapján visszaadja a tároló tömb indexét
    private int mezoIdx(int sor, int i) {
    	switch (sor) {
		case 1: return i; //break;
		case 2: {
			switch (i) {
			case 0: return 3; //break; 
			//a második sorban nincs középső mező
			case 2: return 4; //break; 
			}	
		}
		case 3: {
			switch (i) {
			case 0: return 5; //break; 
			case 1: return 6; //break;
			case 2: return 7; //break; 
			}	
		}
		default: return -1; //break;
		}
    	
    }
    public Field getMezo(int szint, int sor, int oszlop){
		try {
			return (Field)P_mezok[szint - 1][mezoIdx(sor, oszlop-1)];
		} catch (Exception e) {
			return null;
		}
	}
    
    private void korongT_Inic() {
    	P_korong_savok = new JPanel[2][9];	
    	for (int i = 0; i < 9; i++) {
    		P_korong_savok[0][i] = new JPanel(new FlowLayout());
    		P_feh_sav.add(P_korong_savok[0][i]);
    		P_korong_savok[0][i].setBackground(Color.gray);
    		P_korong_savok[0][i].setPreferredSize(new Dimension(40, 40));
    		
    		P_korong_savok[1][i] = new JPanel(new FlowLayout());
    		P_fek_sav.add(P_korong_savok[1][i]);
    		P_korong_savok[1][i].setBackground(Color.gray);
    		P_korong_savok[1][i].setPreferredSize(new Dimension(40, 40));
		}
    }
    private void korong_Felrakas(Player jatekos) {
    	int ks;
    	if(jatekos.getJatekosSzin().equals(Color.white)) ks = 0;
    	else if (jatekos.getJatekosSzin().equals(Color.black)) ks = 1;
    	else ks = -1;
    	
    	for (int i = 0; i < 9; i++) {
    		P_korong_savok[ks][i].add(jatekos.getKorong(i));
    		jatekos.getKorong(i).setParent(P_korong_savok[ks][i]);
		}
    }
    
    
    private void felhFul_Inic(String feherNev, String feketeNev) {
    	P_Feliratok = new JPanel(new GridLayout(4, 1));
        this.add(P_Feliratok, BorderLayout.EAST);
        JPanel p_cim = new JPanel(new FlowLayout());
        JLabel Lh1 = new JLabel("MALOM");
        Lh1.setFont(new Font("Arial", Font.BOLD, 25));
        p_cim.add(Lh1);
        
        JPanel p_jatekosok_felir = new JPanel(new GridLayout(2,2));
        JLabel Lfhn = new JLabel("FEHÉR:");
        JPanel p_feh_hat = new JPanel(new FlowLayout());
        p_feh_hat.setBackground(Color.LIGHT_GRAY);
        p_feh_hat.add(Lfhn);
        Lfhn.setForeground(Color.WHITE);
        p_jatekosok_felir.add(p_feh_hat);
        
        JLabel Lfkn = new JLabel("FEKETE:");
        JPanel p_fek_hat = new JPanel(new FlowLayout(2));
        p_fek_hat.setBackground(Color.LIGHT_GRAY);
        p_fek_hat.add(Lfkn);
        Lfkn.setForeground(Color.BLACK);
        p_jatekosok_felir.add(p_fek_hat);
        
        //----
        L_feh_jnev = new JLabel(feherNev);
        //L_feh_jnev.setForeground(Color.white);
        L_fek_jnev = new JLabel(feketeNev);
        p_jatekosok_felir.add(L_feh_jnev);
        p_jatekosok_felir.add(L_fek_jnev);
        
        JPanel p_utasitashoz = new JPanel(new FlowLayout());
        TF_utasitasok = new JTextArea(10, 25);
        TF_utasitasok.setEditable(false);
        p_utasitashoz.add(TF_utasitasok);
        JScrollPane sc = new JScrollPane(TF_utasitasok);
        setUtasitasText("A FEHÉR játékos kezd!\n"
        		+ feherNev + ", válasszon ki egy korongot!");
        
        JPanel p_idomero = new JPanel(new FlowLayout());
        L_idomero = new JLabel("Eltelt idő: x");
        p_idomero.add(L_idomero);
        
        P_Feliratok.add(p_cim);
        P_Feliratok.add(p_jatekosok_felir);
        P_Feliratok.add(sc);
        //P_Feliratok.add(p_utasitashoz);
        P_Feliratok.add(p_idomero);
    }
    
   	public void setUtasitasText(String utasitas) {
		TF_utasitasok.setText(utasitas);
   	}
    
	public static void addPuck2Field(Puck p, Field f){
		f.add(p);
	}

	public JLabel getTimerLabel(){ return L_idomero; }

    private class mal_uj implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
    		JOptionPane opt = new JOptionPane("Bizotsan új játékot kezd?", 
    				JOptionPane.QUESTION_MESSAGE,
    				JOptionPane.YES_NO_OPTION);
    		JDialog jd = opt.createDialog("Új játék");
    		jd.setVisible(true);
    		//System.out.println(opt.getValue().toString());
    		if (opt.getValue().toString().equals("0")) {
				
    			NewGFrame ngf = new NewGFrame();
    			dispose();
			}
    	}
    }
    
    private class mal1 implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		System.exit(0);
    	}
    }

	private class mal_ered implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		StatFrame sf = new StatFrame();
    	}
    }
	
}
