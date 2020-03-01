package jt;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jt.db.DataService;
import jt.db.model.Szo;
import jt.gui.SzoTableModel;
import net.miginfocom.swing.MigLayout;

public class SzavakPanel extends JSplitPane{
	private JTextField tfSzoveg;
	private JComboBox coKategoria;
	private JTable table;
	private SzoTableModel model;
	private Szo kivalasztottSzo = null;
	public SzavakPanel() {

		JScrollPane scrollPane = new JScrollPane();
		setRightComponent(scrollPane);

		JPanel detailPanel = new JPanel();
		scrollPane.setViewportView(detailPanel);
		detailPanel.setLayout(new MigLayout("", "[grow]", "[][20px][][][20px][][][20px][][][20px][grow][]"));

		JLabel lblSz = new JLabel("Szó");
		lblSz.setFont(new Font("Tahoma", Font.BOLD, 16));
		detailPanel.add(lblSz, "cell 0 0");

		JLabel lblSzveg = new JLabel("Szöveg:");
		detailPanel.add(lblSzveg, "cell 0 2");

		tfSzoveg = new JTextField();
		detailPanel.add(tfSzoveg, "cell 0 3,growx");
		tfSzoveg.setColumns(10);

		JLabel lblKategria = new JLabel("Kategória:");
		detailPanel.add(lblKategria, "cell 0 5");

		coKategoria = new JComboBox();
		detailPanel.add(coKategoria, "cell 0 6,growx");

		JLabel lblKp = new JLabel("Kép:");
		detailPanel.add(lblKp, "cell 0 8");

		JButton btnMents = new JButton("Mentés");
		detailPanel.add(btnMents, "cell 0 12,alignx right");

		JPanel panel = new JPanel();
		setLeftComponent(panel);
		panel.setLayout(new MigLayout("", "[][grow]", "[][20px][grow][20px][]"));

		JLabel lblsszesSz = new JLabel("Összes szó");
		lblsszesSz.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblsszesSz, "cell 0 0 2 1");

		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "cell 0 2 2 1,grow");

		Vector<Szo> szavak = DataService.getAllEntities(Szo.class);
		model = new SzoTableModel(szavak);

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					if(table.getSelectedRow() >= 0) {
						kivalasztottSzo = (Szo)model.getDataVector().get(table.getSelectedRow());
					} else {
						kivalasztottSzo = null;
					}
					onKivalasztas();
				}
			}
		});
		scrollPane_1.setViewportView(table);

		JButton btnj = new JButton("Új");
		panel.add(btnj, "cell 0 4");

		JButton btnTrls = new JButton("Törlés");
		panel.add(btnTrls, "cell 1 4");
		setDividerLocation(500);
	}
	protected void onKivalasztas() {

		if(kivalasztottSzo == null) {
			tfSzoveg.setText(null);
		} else {
			tfSzoveg.setText(kivalasztottSzo.getSzoveg());
		}
		
	}

}
