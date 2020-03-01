package jt.db.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SZAVAK")
public class Szo extends EntityBase {
	
	private String szoveg;
	
	private byte[] kep;
	
	@JoinColumn
	private Kategoria kategoria;
	
	public Szo() {
	}

	public String getSzoveg() {
		return szoveg;
	}

	public void setSzoveg(String szoveg) {
		this.szoveg = szoveg;
	}

	public byte[] getKep() {
		return kep;
	}

	public void setKep(byte[] kep) {
		this.kep = kep;
	}

	@Override
	public String toString() {
		return szoveg;
	}

	public Kategoria getKategoria() {
		return kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}
	
}
