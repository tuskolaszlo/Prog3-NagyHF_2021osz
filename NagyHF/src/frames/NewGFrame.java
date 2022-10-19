package frames;
import game.*;
import elements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGFrame extends JFrame {
	
	/*private JPanel P_fo;
	private JPanel P_fek, P_feh;
	private JLabel L_fek_fel, L_feh_fel;
	private JCheckBox CB_fek_gep, CB_feh_gep;
	private JButton B_start;*/
	private JTextField TF_fek_nev, TF_feh_nev;
	
	public NewGFrame() {
		super("NagyHF - Malom -- Új Játék");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setSize(1000, 1000);
        
        
        JPanel P_fo = new JPanel(new BorderLayout(1, 2));
        this.add(P_fo);
        
        JPanel P_feh = new JPanel(new GridLayout(4, 1));
        P_fo.add(P_feh, BorderLayout.WEST);
        JPanel P_fek = new JPanel(new GridLayout(3, 1));
        P_fo.add(P_fek, BorderLayout.EAST);
        
        JLabel L_feh_fel = new JLabel("FEHÉR");
        JPanel p_feh_hat = new JPanel(new FlowLayout());
        p_feh_hat.setBackground(Color.GRAY);
        p_feh_hat.add(L_feh_fel);
        L_feh_fel.setForeground(Color.WHITE);
        
        
        TF_feh_nev = new JTextField("Fehér");
        TF_fek_nev = new JTextField("Fekete");
        
        //
        //TF_feh_nev = new JTextField("Adja meg a nevet!");!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        TF_feh_nev.setEnabled(true);
        //
        JCheckBox CB_feh_gep = new JCheckBox("CPU");
        CB_feh_gep.addActionListener(new Feh_gep());
        //**************************************************************
        //**************************************************************
        JLabel L_fek_fel = new JLabel("FEKETE");
        JPanel p_fek_hat = new JPanel(new FlowLayout());
        p_fek_hat.setBackground(Color.GRAY);
        p_fek_hat.add(L_fek_fel);
        L_fek_fel.setForeground(Color.BLACK);
        //
        //TF_fek_nev = new JTextField("Adja meg a nevet!");!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        TF_fek_nev.setEnabled(true);
        //
        JCheckBox CB_fek_gep = new JCheckBox("CPU");
        CB_fek_gep.addActionListener(new Fek_gep());
        
        JButton B_start = new JButton("Játék indítása");
        B_start.addActionListener(new B_start_al());
        
        
        P_feh.add(p_feh_hat, BorderLayout.CENTER);
        P_feh.add(TF_feh_nev);
        //P_feh.add(CB_feh_gep);//Nincs még megírva hozzás az ai
        //--------------------
        P_fek.add(p_fek_hat, BorderLayout.CENTER);
        P_fek.add(TF_fek_nev);
        //P_fek.add(CB_fek_gep); //Nincs még megírva hozzá az ai
        
        P_feh.add(B_start);
        
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
	}
	
	private class Feh_gep implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		if(TF_feh_nev.isEditable()) {
    			TF_feh_nev.setEditable(false);
    			TF_feh_nev.setText("CPU");
    		}
    		else {
    			TF_feh_nev.setEditable(true);
    			TF_feh_nev.setText("Fehér");
    		}	
    	}
    }
	private class Fek_gep implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		if(TF_fek_nev.isEditable()) {
    			TF_fek_nev.setEditable(false);
    			TF_fek_nev.setText("CPU");
    		}
    		else {
    			TF_fek_nev.setEditable(true);
    			TF_fek_nev.setText("Fekete");
    		}	
    	}
    }
	 
	 private class B_start_al implements ActionListener {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		/*Frame f = new Frame(new Player(TF_feh_nev.getText(), Color.white),
	    							new Player(TF_fek_nev.getText(), Color.black));*/
                Game g = new Game(new Player(TF_feh_nev.getText(), Color.white),
                                    new Player(TF_fek_nev.getText(), Color.black));
                
	    		dispose();
	    	}
	    }
}
