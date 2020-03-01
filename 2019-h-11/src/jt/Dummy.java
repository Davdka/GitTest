package jt;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Dummy extends JPanel{
	public Dummy() {
		
		JLabel label = new JLabel("?");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("Courier New", Font.PLAIN, 32));
		add(label);
	}

}
