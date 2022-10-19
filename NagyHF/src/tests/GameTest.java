package tests;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import elements.*;
import game.*;

public class GameTest {
	Player p_feher, p_fekete;
	Game game;
	
	@Before
	public void init() {
		p_feher = new Player("Test Játékos_Fehér", Color.white);
		p_fekete = new Player("Test Játékos_Fekete", Color.black);
		game = new Game(p_feher, p_fekete);
	}
	
	@Test
	public void malomellenorzes() {
		assertFalse("Nincs malom felrakva",  game.malomEllenorzes(new Field(1, 1, 1), Color.white));
	}
	@Test
	public void feherJatekosKorongok() {
		boolean mindfeher = true;
		for (Puck p : p_feher.getKorongLista()) {
			if(!p.getColor().equals(Color.white)) mindfeher = false;
		}
		assertTrue("Nem lehet fekete korong a fehérek között!", mindfeher);
	}
	@Test
	public void korongTorles() {
		p_fekete.korongTorles(1);
		p_fekete.korongTorles(6);
		assertEquals("Annyi koronggal kell kevesebbnek lennie a másikhoz képest, amennyit elvettünk belőle"
				, p_feher.getKorongSzam()-2, p_fekete.getKorongSzam());
	}
	@Test
	public void szomszedosMezok_igen() {
		//2;2;1-en indexelt mező szomszédja a 3;2;1-en indexelt mezőnek
		Field f = new Field(2, 2, 1);
		assertTrue("A megadott mezőknek szomszédosnak kell lenniük", f.szomszedosE(new Field(3,2,1)));
	}
	@Test
	public void szomszedosMezok_nem() {
		//1;1;1-en indexelt mező, és 1;3;3-on indexelt mezők a tábla két külön sarkán találhatóak
		Field f = new Field(1,1,1);
		assertFalse("Két külön sarokban lévő mező nem lehet szomszédos", f.szomszedosE(new Field(1,3,3)));
	}
	@Test
	public void masikJatekosEllenorzes() {
		Color c = game.getMasikSzin();
		assertEquals("A fehér játékos kezd, ezért a másik játékos a fekete kell hogy legyen", c, Color.black);
	}
	@Test
	public void helyesLeptetes() {
		game.korongKivalasztas(5, Color.white);
		game.mezoKivalasztas(1, 1, 1);
		
		assertEquals(p_feher.getKorong(5).getParent(), game.getFiedlFomFrame(1, 1, 1));
	}
	@Test
	public void malombanVan() {
		game.setKiJon(false);
		game.korongKivalasztas(2, Color.black);
		game.mezoKivalasztas(2, 1, 3);
		game.setKiJon(false);
		game.korongKivalasztas(4, Color.black);
		game.mezoKivalasztas(2, 2, 3);
		game.setKiJon(false);
		game.korongKivalasztas(6, Color.black);
		game.mezoKivalasztas(2, 3, 3);
		
		assertTrue("2;2;3 egy olyan mező, amin egy malomnak a része", 
					game.malomEllenorzes(game.getFiedlFomFrame(2, 2, 3), Color.black));
	}
}
