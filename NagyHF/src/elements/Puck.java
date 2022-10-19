package elements;
import game.*;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.plaf.synth.Region;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.plaf.synth.Region;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Flow;
import javax.swing.*;


public class Puck extends JLabel {
	private Color c;
	private int id;
	private JPanel parent;
	private boolean kivalaszthato, torolhato;
	
	public Puck(int id, Color c) {
		this.c = c;
		this.setBackground(c);
		this.id = id;
		kivalaszthato = true;
        torolhato = false;
		
		//this.imageUpdate(null, ERROR, ALLBITS, ABORT, WIDTH, HEIGHT);
		if(c.equals(Color.white)) 
			this.setIcon(new ImageIcon("images/feherKorong.png"));
		else if(c.equals(Color.black)) 
			setIcon(new ImageIcon("images/feketeKorong.png"));
		else {}
		
		this.setForeground(Color.green);
		this.addMouseListener(new MyActionListener());
	}
	
	public Color getColor( ) { return c; }
	public int getId() { return id; }
    public void updateId(int i) { id = i; }
	public JPanel getParent() { return parent; }
	public void setParent(JPanel p) { parent = p; }
	public void setKivalaszthato(boolean i_n) { kivalaszthato = i_n; }
    public void setTorolheto(boolean i_n) { torolhato = i_n; }
	
	private class MyActionListener implements MouseListener {
    	/**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         * @param e the event to be processed
         */
        public void mouseClicked(MouseEvent e){
        	System.out.println(c.toString() + " " + id);
            if(!torolhato){
                if (kivalaszthato) {
                    Game.korongKivalasztas(id, c);
                }
            }
        	else{
                Game.korongTorles(id, c);
            }
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
