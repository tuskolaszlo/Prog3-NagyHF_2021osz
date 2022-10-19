package game;

import javax.swing.*;

public class Timer  extends Thread {
	private JLabel L_idomero;
	private Integer ido_mp, ido_p;
	private volatile boolean mehet;
	
	public Timer(JLabel L_idomero) {
		this.L_idomero = L_idomero;
		ido_mp = 0; ido_p = 0;
		mehet = true;
	}
	
	public void run() {
		while (mehet) {
			try {
				L_idomero.setText("Eltelt idő: " + String.format("%02d", ido_p) + ":" + String.format("%02d", ido_mp));
				
				if(ido_mp >= 59) {
					ido_p++;
					ido_mp = 0;
				}
				else ido_mp++;
				Thread.sleep(1000);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public void allj(){
		mehet = false;
	}
}
	

