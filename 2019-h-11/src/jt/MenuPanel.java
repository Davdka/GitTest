package jt;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel{
	private AkasztofaMainFrame akasztofaMainFrame;

	public MenuPanel(AkasztofaMainFrame akasztofaMainFrame) {
		this.akasztofaMainFrame = akasztofaMainFrame;
		setLayout(new MigLayout("", "[grow][160px][grow]", "[grow][][40px][40px][40px][40px][grow]"));
		
		JLabel lblAkasztfaJtk = new JLabel("Akasztófa játék");
		lblAkasztfaJtk.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblAkasztfaJtk, "cell 1 1,alignx center");
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onStart();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(btnStart, "cell 1 3,grow");
		
		JButton btnSzavak = new JButton("Szavak");
		btnSzavak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSzavak();
			}
		});
		add(btnSzavak, "cell 1 4,grow");
		
		JButton btnKilps = new JButton("Kilépés");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onKilepes();
			}
		});
		add(btnKilps, "cell 1 5,grow");
	}

	protected void onSzavak() {

		this.akasztofaMainFrame.panelValtas(new SzavakPanel());
		
	}

	protected void onStart() {

		this.akasztofaMainFrame.panelValtas(new JatekPanel());
		
	}

	protected void onKilepes() {
		
		this.akasztofaMainFrame.dispose();

		
		
	}

}
