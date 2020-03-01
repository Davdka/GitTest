package jt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jt.db.DataService;
import jt.db.model.Szo;

public class AkasztofaMainFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private Vector<Szo> szavak;

	public static void main(String[] args) {
		new AkasztofaMainFrame();
	}

	public AkasztofaMainFrame() {

		szavak = DataService.getAllEntities(Szo.class);

		//filebolHozzaOlvas();

		setSize(800, 600);
		setTitle("Akasztófa játék");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setContentPane(new MenuPanel(this));

		setVisible(true);
	}

	private void filebolHozzaOlvas() {

		try {

			List<String> sorok = Files.readAllLines(Paths.get("./szavak/b64.txt"));
			for (String sor : sorok) {

				System.out.print(sor + " -> ");

				byte[] decodedBytes = Base64.getDecoder().decode(sor);
				String decodedString = new String(decodedBytes);
				System.out.println(decodedString);

				Szo szo = new Szo();
				szo.setSzoveg(decodedString);

				this.szavak.add(szo);


			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void panelValtas(JComponent panel) {

		setContentPane(panel);
		this.revalidate();
		
	}

}
