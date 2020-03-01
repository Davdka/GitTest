package jt;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SzoKodoloMain {

	static String algorithm = "DESede";

	public static void main(String[] args) {

		

		try {
			
//			String originalInput = "alma";
//			String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//			System.out.println(encodedString);
//
//			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//			String decodedString = new String(decodedBytes);
//			System.out.println(decodedString);
			
			StringBuffer sb = new StringBuffer();
			List<String> sorok = Files.readAllLines(Paths.get("./szavak/any.txt"));
			String[] szavak = sorok.get(0).split(" ");
			for (String szo : szavak) {
				System.out.print(szo);

				sb.append(Base64.getEncoder().encodeToString(szo.getBytes()));
				sb.append("\n");

			}
			
			String fajl = "./szavak/b64.txt";
			if(new File(fajl).exists()){
				System.out.println("Már van kódolt fájl!");
				return;
			}
			
			Files.write(Paths.get(fajl), sb.toString().getBytes());



		} catch (Exception e) {
			e.printStackTrace();
		}

	}




}
