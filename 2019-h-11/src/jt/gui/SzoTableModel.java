package jt.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import jt.db.model.Szo;

public class SzoTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	private static final SimpleDateFormat HU = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private String[] colNames = {"Szöveg", "Kategória", "Kép"};

	private static final int COL_SZOVEG		 			= 0;
	private static final int COL_KATEGORIA 	    		= 1;
	private static final int COL_KEP 			    	= 2;

	public SzoTableModel(Vector<Szo> v) {
		this.dataVector = v;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object o = this.dataVector.get(row);
		if (o instanceof Szo) {
			Szo current = (Szo)o;

			switch (column) {
			case COL_SZOVEG:
				return current.getSzoveg();
			case COL_KATEGORIA:
				return (current.getKategoria() == null) ? "-" : current.getKategoria().getNev();
			case COL_KEP:
				return (current.getKep() == null) ? "Nincs" : "Van";
			default:
				return "";
			}

		}

		return "";
	}


	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return String.class;
	}

	@Override
	public int getRowCount() {
		return (dataVector == null) ? 0 : dataVector.size();
	}

}
