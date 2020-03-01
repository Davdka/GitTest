package jt;

import java.util.Vector;

import javax.swing.JScrollPane;

import jt.db.DataService;
import jt.db.model.Szo;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;

public class JatekPanel extends JScrollPane {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<Szo> szavak;
	private Szo feladvany;
	private char[] teljesitett;
	private JTextField tfTipp;
	private int maradekElet = 10;

	private FeladvanyPanel feladvanyPanel;
	private JTextArea tfEddigiTippek;
	private JLabel lblMaradekTipp;
	private JButton btnOk;
	private JLabel lblKep;
	private JLabel lblKategoria;

	public JatekPanel() {
		this.szavak = DataService.getAllEntities(Szo.class);
		feladvany = sorsol();											//	 [ a, l, m, a ]
		teljesitett = new char[feladvany.getSzoveg().length()];			//   [ _, _, _, _ ]
		for (int i = 0; i < teljesitett.length; i++) {
			teljesitett[i] = '?';										//	 [ ?, ?, ?, ? ]
		}
		
		JPanel panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(new MigLayout("", "[20px][grow][60px][60px][20px]", "[grow][][20px][][60px][][][20px][60px,fill][20px]"));
		
		feladvanyPanel = new FeladvanyPanel(feladvany);
		panel.add(feladvanyPanel, "cell 1 0 3 1,grow");
		
		lblKep = new JLabel();
		if(feladvany.getKep() != null) {
			lblKep.setIcon(new ImageIcon(feladvany.getKep()));
		}
		panel.add(lblKep, "cell 1 1,grow");
		
		lblKategoria = new JLabel();
		if(feladvany.getKategoria() != null) {
			lblKategoria.setText(feladvany.getKategoria().getNev());
		}
		panel.add(lblKategoria, "cell 2 1 2 1,alignx right");
		
		JLabel lblNewLabel = new JLabel("Eddigi tippek:");
		panel.add(lblNewLabel, "cell 1 3 3 1");
		
		tfEddigiTippek = new JTextArea();
		tfEddigiTippek.setLineWrap(true);
		tfEddigiTippek.setEditable(false);
		tfEddigiTippek.setFont(new Font("Monospaced", Font.BOLD, 20));
		panel.add(tfEddigiTippek, "cell 1 4 3 1,grow");
		
		JLabel lblMaradkTipp = new JLabel("Maradék tipp:");
		panel.add(lblMaradkTipp, "cell 1 5");
		
		lblMaradekTipp = new JLabel(maradekElet + "");
		panel.add(lblMaradekTipp, "cell 1 6");
		
		JLabel lblTipp = new JLabel("Tipp:");
		panel.add(lblTipp, "cell 1 8,alignx trailing");
		
		tfTipp = new JTextField();
		tfTipp.setHorizontalAlignment(SwingConstants.CENTER);
		tfTipp.setFont(new Font("Tahoma", Font.BOLD, 32));
		panel.add(tfTipp, "cell 2 8,growx");
		tfTipp.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onTipp();
			}
		});
		panel.add(btnOk, "cell 3 8,growx");
		
		
		
		//add(new JLabel(feladvany.getSzoveg()));
	}

	protected void onTipp() {
		boolean elfogadhatoTipp = true;
		String tippSzoveg = tfTipp.getText();
		String hiba = null;
		if(tippSzoveg.length() != 1){
			elfogadhatoTipp = false;
			hiba = "A tipp csak egy karakter lehet!";
		}
		
		if (elfogadhatoTipp) {		// ha még elfogadható a tipp, nézzük meg ismétlődésre is
			if(tfEddigiTippek.getText().contains( tfTipp.getText().toUpperCase() )){
				elfogadhatoTipp = false;
				hiba = "Ezt már tippelte!";
			}
		}
		
		if (!elfogadhatoTipp) {
			JOptionPane.showMessageDialog(getTopLevelAncestor(), hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		char tipp = Character.toUpperCase( tippSzoveg.charAt(0) );		// a -> A
		tfEddigiTippek.append(tipp + " ");
			
		boolean talalte = false;
		for (int i = 0; i < feladvany.getSzoveg().length(); i++) {
			if (tipp == Character.toUpperCase( feladvany.getSzoveg().charAt(i) ) ) {
				teljesitett[i] = tipp;
				talalte = true;
			}
			
			System.out.print(teljesitett[i] + " ");
		}
		System.out.println();
		if (!talalte) {
			maradekElet--;
		}
		System.out.println("Maradék élet: " + maradekElet);
		lblMaradekTipp.setText(maradekElet + "");
		if(maradekElet == 0) {
			
			JOptionPane.showMessageDialog(getTopLevelAncestor(), "V E S Z T E T T !");
			btnOk.setEnabled(false);
			tfTipp.setEnabled(false);
			return;
			
		}
		
		feladvanyPanel.frissit(teljesitett);
		tfTipp.setText(null);
		
		boolean nyerte = true;
		for (int i = 0; i < teljesitett.length; i++) {
			if (teljesitett[i] == '?') {
				nyerte = false;
			}
		}
		if (nyerte) {
			JOptionPane.showMessageDialog(getTopLevelAncestor(), "N Y E R T !");
		}
		
	}

	private Szo sorsol() {
		return szavak.get( (int)(Math.random() * szavak.size()) );
	}

}
