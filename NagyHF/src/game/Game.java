package game;
import elements.*;
import frames.*;
import file.*;
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
	private static GameFrame frame;
	private static Timer timer;
	private static HashMap<Color, Player> Jatekosok;
	private static Puck selectedPuck;
	private static Color aktualisJatekos;
	private static int felrakasSzamlalo;
	
	public Game(Player feherJ, Player feketeJ) {
		Jatekosok = new HashMap<>();
		Jatekosok.clear();
		Jatekosok.put(Color.white, feherJ);
		Jatekosok.put(Color.black, feketeJ);
		
		frame = new GameFrame(feherJ, feketeJ);
		
		selectedPuck = null;
		aktualisJatekos = Color.white;
		felrakasSzamlalo = 9;

		timer = new Timer(frame.getTimerLabel());
		timer.start();
	}
	
	public static void korongKivalasztas(int id, Color c) {	
		
		if (aktualisJatekos.equals(c)) {
			if(selectedPuck != null && selectedPuck != Jatekosok.get(c).getKorong(id)) {
				frame.setUtasitasText("Már kiválasztott egy korongot ("+(selectedPuck.getId()+1)+".)\n"
					+ "Válassza ki azt a mezőt, amelyre lépni akar!");
			}
			else if (selectedPuck != Jatekosok.get(c).getKorong(id)) {
				selectedPuck = Jatekosok.get(c).getKorong(id);
				frame.setUtasitasText("A(z) " + (selectedPuck.getId()+1) + ". számú (" + Jatekosok.get(c).getJateoksSzinFelirat() 
									+") korong kiválasztva.\n"
									+"Most válassza ki azt a mezőt,\n\t amelyre lépni szeretne!");
				korongKivalasztas_felirat(true);
			}
			else if (selectedPuck == Jatekosok.get(c).getKorong(id)) {
				korongKivalasztas_felirat(false);
				selectedPuck = null;
				frame.setUtasitasText("A kiválasztás megszüntetve\n"
									+"A "+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() +" játékos jön!"
										+"\n"+Jatekosok.get(aktualisJatekos).getJatekosNev()+", válasszon ki egy korongot!");
			}
		}
	}
	public static void korongTorles(int id, Color c) {
		if (!aktualisJatekos.equals(c)) {
			selectedPuck = Jatekosok.get(c).getKorong(id);
			Field f = (Field)selectedPuck.getParent();

			if(!malomEllenorzes(f, c)){
				f.remove(selectedPuck);
				//f2.revalidate();
				f.updateUI();
				f.setBackground(f.getBackground());
				f.setPuck(null);
	
				Jatekosok.get(c).korongTorles(id);
				selectedPuck = null;
				
				Color masik_szin = aktualisJatekos; //getMasikSzin();
				for (Puck p : Jatekosok.get(c).getKorongLista()) {
					p.setTorolheto(false);
				}
				for (Puck p : Jatekosok.get(masik_szin).getKorongLista()) {
					try {
						Field torlFd = (Field)p.getParent();
						if (felrakasSzamlalo <= 0) 
							p.setKivalaszthato(true);
						
					} catch (Exception e) {
						p.setKivalaszthato(true);
					}
				}
				frame.setUtasitasText(Jatekosok.get(masik_szin).getJatekosNev() + ", levett egy korongot a "
									+ Jatekosok.get(c).getJateoksSzinFelirat() + " korongjaiból.\n"
									+ Jatekosok.get(c).getJateoksSzinFelirat().toUpperCase() +" játékos következik!");
	
				if(aktualisJatekos.equals(Color.white)) setKiJon(false);
				else {
					if(felrakasSzamlalo >= 0) felrakasSzamlalo--;
					setKiJon(true);
				}

				if (Jatekosok.get(c).getKorongSzam() == 3) {
					Jatekosok.get(c).setUgralhatE(true);
					frame.setUtasitasText(Jatekosok.get(c).getJateoksSzinFelirat().toUpperCase() +" játékosnak már csak 3 korongja maradt.\n"
									+ Jatekosok.get(c).getJatekosNev() + ", innentől ugrálhat a korongokkal.\n" 
									+ "(Bármelyik mezőre léphet)\n"
									+ Jatekosok.get(c).getJateoksSzinFelirat().toUpperCase() + " játékos következik!");
				}
				else if (Jatekosok.get(c).getKorongSzam() < 3){
					jatekVege(Jatekosok.get(c).getJateoksSzinFelirat().toUpperCase() + " játékosnak elfogytak a korongjai.");
				}
			}
			else{
				frame.setUtasitasText("Másik malomban lévő korongot nem vehet le!\n"
									+ "Válasszon egy másik korongot!");
			}
		}
		
	}


	public static void mezoKivalasztas(int szint, int sor, int oszlop) {
		if(selectedPuck != null) {
			Field fd = frame.getMezo(szint, sor, oszlop);
			
			try {
				Field f_elozo = (Field)selectedPuck.getParent();
				
				if (!Jatekosok.get(aktualisJatekos).getUgralhatE()) {
					if (f_elozo.szomszedosE(fd)) {
						leptetes(fd);
						/*frame.setUtasitasText("Átléptette a kiválasztott korongot\n\t"
								+ "egy másik szomszédos mezőre.\n"
								+"A "+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() +" játékos jön!"
								+"\n"+Jatekosok.get(aktualisJatekos).getJatekosNev()+", válasszon ki egy korongot!");*/
					}
					else {
						frame.setUtasitasText("Ide nem léphet, mert nem szomszédos mezőn\n"
								+ "van a korong\n"
								+" Válasszon másik mezőt!");
					}
				}
				
			} catch (Exception e) { }
			
			if (Jatekosok.get(aktualisJatekos).getUgralhatE()) {
				leptetes(fd);
				/*frame.setUtasitasText("");*/
			}
		}
	}
	
	private static void leptetes(Field fd) {
		if(fd.getIttLevoKorong() == null) {
			
			//JPanel f2;// = (Field)selectedPuck.getParent();
			try {
				Field f2 = (Field)selectedPuck.getParent();
				f2.remove(selectedPuck);
				//f2.revalidate();
				f2.updateUI();
				f2.setBackground(f2.getBackground());
				f2.setPuck(null);
				
			}
			catch (Exception e) {
				JPanel f2 = selectedPuck.getParent();
				f2.remove(selectedPuck);
				//f2.revalidate();
				f2.updateUI();
				f2.setBackground(f2.getBackground());
			}
			
			fd.add(selectedPuck);
			fd.setPuck(selectedPuck);
			
			selectedPuck.setParent(fd);
			korongKivalasztas_felirat(false);
			
					
			Color masik_szin;
			if(aktualisJatekos.equals(Color.white)) masik_szin = Color.black;
			else masik_szin = Color.white;

			if (malomEllenorzes(fd, aktualisJatekos)) {
				boolean csak_malom = true;
				for (Puck p : Jatekosok.get(masik_szin).getKorongLista()) {
					try {
						if(!malomEllenorzes((Field)p.getParent(), p.getColor())) {
							csak_malom = false;
							break;
						}
					} catch (Exception e) {	}
				}

				if (!csak_malom) {
					frame.setUtasitasText("MALOM\n"
									+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() + " játékos malmot rakott ki!\n"
									+ Jatekosok.get(aktualisJatekos).getJatekosNev() +", vegyen le egy "
									+ Jatekosok.get(masik_szin).getJateoksSzinFelirat().toUpperCase() +" korongot! \n");
				
					for (Puck p : Jatekosok.get(masik_szin).getKorongLista()) {
						try {
							Field torlFd = (Field)p.getParent();
							p.setTorolheto(true);
						} catch (Exception e) {
							//frame.setUtasitasText("Csak olyan korongot vehet le, ami a pályán van!");
						}
					}
					for (Puck p : Jatekosok.get(aktualisJatekos).getKorongLista()) {
						p.setKivalaszthato(false);
					}
				}
				else{
					lepesEllenorzes();
					masik_szin = getMasikSzin();
					frame.setUtasitasText("MALOM\n"
										+ Jatekosok.get(masik_szin).getJateoksSzinFelirat().toUpperCase() + " játékos malmot rakott ki!\n"
										+ "DE a "+Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase()+" játékos minden korongja\n\t"
										+ "malomban van!\n"
										+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase()+" játékos következik!");
					
				}
			}
			else {
				lepesEllenorzes();
			}

			if(!Jatekosok.get(masik_szin).getUgralhatE()){
				ArrayList<Boolean> szabadSzomszed = new ArrayList<>();
				//if(felrakasSzamlalo > 0){
				for (Puck p : Jatekosok.get(masik_szin).getKorongLista()) {
					try {
						szabadSzomszed.add(szomszedokSzabadok((Field)p.getParent()));
					} catch (Exception e) {	}
				}
				//}
				//Ellenőrzi hogy van-e szabad szomszédja minden mezőnek
				//Ha nincs egyiknek sem, akkor vége a játéknak
				if (!szabadSzomszed.contains(true) && !szabadSzomszed.isEmpty()){
					jatekVege(Jatekosok.get(masik_szin).getJateoksSzinFelirat() + " játékos nem tud többet lépni.");
				}	
			}

			selectedPuck = null;
		}
		else {
			frame.setUtasitasText("Ezen a mezőn már van korong.\n"
					+ "Válasszon egy másik mezőt!");
		}
	}
	private static void lepesEllenorzes(){
		if(aktualisJatekos.equals(Color.white)) setKiJon(false);
		else {
			felrakasSzamlalo--;
			setKiJon(true);
		}

		if(felrakasSzamlalo > 0) {
			frame.setUtasitasText("Letette a korongot az egyik mezőre.\n"
					+"A "+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() +" játékos jön!"
					+"\n"+Jatekosok.get(aktualisJatekos).getJatekosNev()+", válasszon ki egy korongot!");
			selectedPuck.setKivalaszthato(false);
		}
		else if (felrakasSzamlalo == 0){
			//frame.setUtasitasText(null);
			for (Puck p : Jatekosok.get(Color.white).getKorongLista()) {
				p.setKivalaszthato(true);
			}
			for (Puck p : Jatekosok.get(Color.black).getKorongLista()) {
				p.setKivalaszthato(true);
			}
			felrakasSzamlalo--;

			frame.setUtasitasText("Mindkét játékos felrakta az összes korongot.\n"
								+"Innentől a korongokat kell léptetni.\n"
								+"Csak egy szomszédos üres mezőre szabad léptetni egy\n\t"
								+"kiválasztott kiválaszott korongot\n"
								+"(kivétel ha 3 korongja maradt egy játékosnak)\n"
								+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() + " játkos következik");

			Jatekosok.get(Color.white).setUgralhatE(false);
			Jatekosok.get(Color.black).setUgralhatE(false);
		}
		else{
			frame.setUtasitasText(".\n"
					+"A "+ Jatekosok.get(aktualisJatekos).getJateoksSzinFelirat().toUpperCase() +" játékos jön!"
					+"\n"+Jatekosok.get(aktualisJatekos).getJatekosNev()+", válasszon ki egy korongot!");
		}
	}
	
	/*private*/public static boolean malomEllenorzes(Field f, Color c){ //a teszteléshez kell hogy public legyen
		//boolean malom = false;
		Field f2, f3;
		//Sorok ellenőrzése
		if(f.getSor() != 2) {
			int f2_o = 2, f3_o = 3;
			switch (f.getOszlop()) {
				case 2: f2_o = 1; break;
				case 3: f3_o = 1; break;
			}
			f2 = frame.getMezo(f.getSzint(), f.getSor(), f2_o);
			f3 = frame.getMezo(f.getSzint(), f.getSor(), f3_o);

			if(malom_ellenorzes_korongok(f,f2,f3, c)) return true;
		}
		else{
			int f2_sz = 2, f3_sz = 3;
			switch (f.getSzint()) {
				case 2: f2_sz = 1; break;
				case 3: f3_sz = 1; break;
			}
			f2 = frame.getMezo(f2_sz, f.getSor(), f.getOszlop());
			f3 = frame.getMezo(f3_sz, f.getSor(), f.getOszlop());
			if(malom_ellenorzes_korongok(f,f2,f3, c)) return true;
		}
		//Oszlopok ellenőrzése
		if (f.getOszlop() != 2) {
			int f2_s = 2, f3_s = 3;
			switch (f.getSor()) {
				case 2: f2_s = 1; break;
				case 3: f3_s = 1; break;
			}
			f2 = frame.getMezo(f.getSzint(), f2_s, f.getOszlop());
			f3 = frame.getMezo(f.getSzint(), f3_s, f.getOszlop());
			if(malom_ellenorzes_korongok(f,f2,f3, c)) return true;
		}
		else{
			int f2_sz = 2, f3_sz = 3;
			switch (f.getSzint()) {
				case 2: f2_sz = 1; break;
				case 3: f3_sz = 1; break;
			}
			f2 = frame.getMezo(f2_sz, f.getSor(), f.getOszlop());
			f3 = frame.getMezo(f3_sz, f.getSor(), f.getOszlop());
			if(malom_ellenorzes_korongok(f,f2,f3, c)) return true;
		}
		return false;
	}
	//Korongok vizsgálata
	private static boolean malom_ellenorzes_korongok(Field f1, Field f2, Field f3, Color c){
		if (f2.getIttLevoKorong()!=null && f3.getIttLevoKorong()!=null) {
			if (f1.getIttLevoKorong().getColor().equals(c) &&
				f2.getIttLevoKorong().getColor().equals(c) &&
				f3.getIttLevoKorong().getColor().equals(c)) {
				return true;
			}
		}
		return false;
	}
	private static boolean szomszedokSzabadok(Field f){
		Field[] fds = new Field[4];

		//Sarkok ellenőrzése
		if(f.getSor() != 2 && f.getOszlop() != 2) {
			try {
				fds[0] = frame.getMezo(f.getSzint(), f.getSor()+1, f.getOszlop()); //Fölötte lévő mező
			} catch (Exception e) { fds[0] = null;	}
			try {
				fds[1] = frame.getMezo(f.getSzint(), f.getSor(), f.getOszlop()-1); //Balra lévő mező
			} catch (Exception e) { fds[1] = null;	}
			try {
				fds[2] = frame.getMezo(f.getSzint(), f.getSor(), f.getOszlop()+1); //Jobbra lévő mező
			} catch (Exception e) { fds[2] = null;	}
			try {
				fds[3] = frame.getMezo(f.getSzint(), f.getSor()-1, f.getOszlop()); //Alatta lévő mező
			} catch (Exception e) { fds[3] = null;	}
		}
		else{
			if(f.getSor() == 2){
				try {
					fds[0] = frame.getMezo(f.getSzint(), f.getSor()+1, f.getOszlop()); //Fölötte lévő mező
				} catch (Exception e) { fds[0] = null;	}
				try {
					fds[1] = frame.getMezo(f.getSzint()-1, f.getSor(), f.getOszlop()); //Balra lévő mező
				} catch (Exception e) { fds[1] = null;	}
				try {
					fds[2] = frame.getMezo(f.getSzint()+1, f.getSor(), f.getOszlop()); //Jobbra lévő mező
				} catch (Exception e) { fds[2] = null;	}
				try {
					fds[3] = frame.getMezo(f.getSzint(), f.getSor()-1, f.getOszlop()); //Alatta lévő mező
				} catch (Exception e) { fds[3] = null;	}
			}
			else if(f.getOszlop() == 2){
				try {
					fds[0] = frame.getMezo(f.getSzint()+1, f.getSor(), f.getOszlop()); //Fölötte lévő mező
				} catch (Exception e) { fds[0] = null;	}
				try {
					fds[1] = frame.getMezo(f.getSzint(), f.getSor(), f.getOszlop()-1); //Balra lévő mező
				} catch (Exception e) { fds[1] = null;	}
				try {
					fds[2] = frame.getMezo(f.getSzint(), f.getSor(), f.getOszlop()+1); //Jobbra lévő mező
				} catch (Exception e) { fds[2] = null;	}
				try {
					fds[3] = frame.getMezo(f.getSzint()-1, f.getSor(), f.getOszlop()); //Alatta lévő mező
				} catch (Exception e) { fds[3] = null;	}
			}	
		}

		for (Field f_i : fds) {
			if(f_i != null){
				if(f_i.getIttLevoKorong() == null) 
					return true;
			}
		}
		return false;
	}
	
	private static void korongKivalasztas_felirat(boolean kival_hozzad) {
		if(kival_hozzad) selectedPuck.setText("|");
		else selectedPuck.setText(null);
	}
	public static void setKiJon(boolean feher) {
		if(feher) aktualisJatekos = Color.white;
		else aktualisJatekos = Color.black;
	}
	/*private*/public static Color getMasikSzin() { //A teszteléshez szükséges
		if(aktualisJatekos.equals(Color.white)) return Color.black;
		return Color.white;
	 }

	private static void jatekVege(String V_ok){
		for (Puck p : Jatekosok.get(Color.white).getKorongLista()) {
			p.setKivalaszthato(false);
		}
		for (Puck p : Jatekosok.get(Color.black).getKorongLista()) {
			p.setKivalaszthato(false);
		}

		Color masik_szin = getMasikSzin();
		frame.setUtasitasText("A játék véget ért!\n"
							+V_ok + "\n"
							+"A NYERTES: " + Jatekosok.get(masik_szin).getJatekosNev() + ", a "
							+Jatekosok.get(masik_szin).getJateoksSzinFelirat().toUpperCase()+" játékos\n");
		timer.allj();

		FileXML.fajlbaMentes("eredmenyek.xml");
	}
	public static String[] nyertesekLekerdezese() {
		//i.get("feher_j") +";"+ i.get("fekete_j") +";"+ i.get("ido") +";"+ i.get("nyertes");
		String[] adatok = new String[4];
		adatok[0] = Jatekosok.get(Color.white).getJatekosNev();
		adatok[1] = Jatekosok.get(Color.black).getJatekosNev();

		String felir = frame.getTimerLabel().getText();
		adatok[2] = felir.split(" ")[2];
		
		if(getMasikSzin().equals(Color.white)) adatok[3] = "FEHÉR";
		else adatok[3] = "FEKETE";

		return adatok;
	}
	
	//Csak a teszteléshez szükséges függvény
	public Field getFiedlFomFrame(int szint, int sor, int oszlop) {
		return frame.getMezo(szint, sor, oszlop);
	}
}
