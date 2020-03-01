package jt.db;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DataConn {
	private static EntityManagerFactory emf;
	public static final Map<String,String> props = init();


	private static Map<String, String> init() {
		HashMap<String, String> ret = new HashMap<String, String>();
		ret.put("javax.persistence.jdbc.user",		"akasztofa");
		ret.put("javax.persistence.jdbc.password",	"akasztofa");
		ret.put("javax.persistence.jdbc.driver", 	"oracle.jdbc.OracleDriver");
		ret.put("javax.persistence.jdbc.url",		"jdbc:oracle:thin:@localhost:1521:XE");
		return ret;
	}

	public static void open(){
		emf = Persistence.createEntityManagerFactory("akasztofa", props);		// persistence.xml unit neve kell ide
	}

	public static void close(){
		if(emf != null)
			emf.close();
	}

	public static EntityManagerFactory getEmf(){
		if(emf == null || !emf.isOpen())
			open();
		return emf;
	}

	public static EntityManager getEm(){
		return getEmf().createEntityManager();
	}
}