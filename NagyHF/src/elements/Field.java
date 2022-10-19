package elements;
import game.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class Field extends JPanel {
	private int szint, sor, oszlop;
    private Puck ittLevoKorong;
	
	public Field(int szint, int sor, int oszlop) {
		this.szint = szint;
		this.sor = sor;
		this.oszlop = oszlop;
        ittLevoKorong = null;
		
		this.addMouseListener(new MyActionListener());
	}
	
	public int getSzint() { return szint; }
	public int getSor() { return sor; }
	public int getOszlop() { return oszlop; }
	public Puck getIttLevoKorong() { return ittLevoKorong; }
	public void setPuck(Puck p) { ittLevoKorong = p; }
	
	
	public boolean szomszedosE(Field f) {
		boolean szomszed = false;
		//Sorban keresi a szomszédot
		if(f.getOszlop() > oszlop || f.getOszlop() < oszlop) {
			if(sor != 2) {
				if((szint == f.getSzint() && sor == f.getSor()) && 
					(f.getOszlop() == oszlop+1 || f.getOszlop()== oszlop-1)) {
					szomszed = true;
				}
			}
		}
		else {
            //A második sor a középső, ezek pedig a szinteket kötik össze, ezért SZINT alapján kell vizsgálni
            if((sor == 2 && f.getSor() == 2) &&
                (oszlop == f.getOszlop() && sor == f.getSor()) &&
				(f.getSzint() == szint+1 || f.getSzint() == szint-1)) {
					szomszed = true;
			}
            //A második oszlop a középső, ezek pedig a szinteket kötik össze, ezért SZINT alapján kell vizsgálni
			else if ((oszlop == 2 && f.getOszlop() == 2) &&
                (sor == f.getSor() && oszlop == f.getOszlop()) &&
                (f.getSzint() == szint+1 || f.getSzint() == szint-1)) {
                    szomszed = true;
            }

            else if(oszlop != 2) {
				if((szint == f.getSzint() && oszlop == f.getOszlop()) &&
					(f.getSor() == sor+1 || f.getSor() == sor-1)) {
					szomszed = true;
				}
			}
		}
		
		return szomszed;
	}
	
	
    private class MyActionListener implements MouseListener {
    	/**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
        	System.out.println(szint + ";" + sor +" "+ oszlop);
            Game.mezoKivalasztas(szint, sor, oszlop);
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         * @param e the event to be processed
         */
        public void mousePressed(MouseEvent e){
        }

        /**
         * Invoked when a mouse button has been released on a component.
         * @param e the event to be processed
         */
        public void mouseReleased(MouseEvent e){
        }

        /**
         * Invoked when the mouse enters a component.
         * @param e the event to be processed
         */
        public void mouseEntered(MouseEvent e){
        }

        /**
         * Invoked when the mouse exits a component.
         * @param e the event to be processed
         */
        public void mouseExited(MouseEvent e){
        }
    }
}
