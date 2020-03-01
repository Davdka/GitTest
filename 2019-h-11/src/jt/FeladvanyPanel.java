package jt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import jt.db.model.Szo;
import net.miginfocom.swing.MigLayout;

public class FeladvanyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Szo feladvany;
	
	private ArrayList<JLabel> betuKartyak = new ArrayList<>();

	public FeladvanyPanel(Szo feladvany) {
		this.feladvany = feladvany;
		setLayout(new MigLayout("", "[grow][80px:n][grow]", "[grow]"));
		
		//alapból FLOW, ha kell akkor BOX layoutra áttenni
		JPanel box = new JPanel();
		add(box, "cell 1 0,grow");

		for (int i = 0; i < feladvany.getSzoveg().length(); i++) {
			JLabel betu = new JLabel("?");
			betu.setToolTipText(feladvany.getSzoveg().charAt(i) + "");
			betu.setFont(new Font("Courier New", Font.PLAIN, 32));
			betu.setBorder(new LineBorder(new Color(0, 0, 0)));
			betu.setPreferredSize(new Dimension(40, 40));
			betu.setHorizontalAlignment(SwingConstants.CENTER);
			box.add(betu);
			betuKartyak.add(betu);
		}
		
	}

	public void frissit(char[] teljesitett) {		// [ ? , ő , t , é , ? ]
		for (int i = 0; i < teljesitett.length; i++) {
			betuKartyak.get(i).setText( teljesitett[i]+""  );
		}

	}
		

}
