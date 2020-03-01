package jt.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "KATEGORIAK")
public class Kategoria extends EntityBase {
	
	private String kod;
	
	private String nev;
	
	
	public Kategoria() {
	}


	public String getKod() {
		return kod;
	}


	public void setKod(String kod) {
		this.kod = kod;
	}


	public String getNev() {
		return nev;
	}


	public void setNev(String nev) {
		this.nev = nev;
	}

	@Override
	public String toString() {
		return nev;
	}
	
}
