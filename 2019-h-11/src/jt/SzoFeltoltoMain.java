package jt;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import jt.db.DataService;
import jt.db.model.Szo;

public class SzoFeltoltoMain {

	public static void main(String[] args) {

		try {
			List<String> sorok = Files.readAllLines(Paths.get("./szavak/any.txt"));
			String[] szavak = sorok.get(0).split(" ");
			for (String szo : szavak) {
				System.out.print(szo);
				
				try {
					Szo szoRekord = new Szo();
					szoRekord.setSzoveg(szo);
					DataService.insert(szoRekord);
					
					System.out.println("... OK");
				} catch (Exception e) {
					System.out.println("... HIBA: " + e.getMessage());
				}
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
