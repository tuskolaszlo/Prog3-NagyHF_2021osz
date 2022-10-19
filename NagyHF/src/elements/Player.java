package elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

public class Player {
	
	private ArrayList<Puck> korongok;
	private String player_nev;
	private Color player_color;
	private boolean ugralhat_e;
	
	public Player(String nev, Color c) {
		player_nev = nev;
		player_color = c;
		
		korongok = new ArrayList<Puck>();
		for (int i = 0; i < 9; i++) {
			korongok.add(new Puck(i, c));
		}
		ugralhat_e = true; //A felrakásnál bárhova teheti a korongot a játékos
	}
	
	//get-terek
	public String getJatekosNev() { return player_nev; }
	public int getKorongSzam() { return korongok.size(); }
	public Puck getKorong(int i) { return korongok.get(i); }
	public Color getJatekosSzin() { return player_color; }
	public String getJateoksSzinFelirat() {
		String sz = "";
		if (player_color.equals(Color.white)) sz = "fehér";
		else if (player_color.equals(Color.black)) sz = "fekete";
		return sz;
	}
	public boolean getUgralhatE() { return ugralhat_e; }
	public void setUgralhatE(boolean ugralhat) { ugralhat_e = ugralhat; }
	public ArrayList<Puck> getKorongLista() { return korongok; }
	public void korongTorles(int id){ 
		korongok.remove(id);
		for (int i = 0; i < korongok.size(); i++) {
			korongok.get(i).updateId(i);
		}
	}

}
