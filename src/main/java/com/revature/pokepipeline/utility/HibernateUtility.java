package com.revature.pokepipeline.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	
	private static SessionFactory sf;
	private static Session ses;
	
	public static Session getSession() {
		if (ses == null) {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			configuration.setProperty("hibernate.connection.username", System.getenv("postgresUsername"));
			configuration.setProperty("hibernate.connection.password", System.getenv("postgresPassword"));
			sf = configuration.buildSessionFactory();
			ses = sf.openSession();
		}

		return ses;
	}
	
	public static void closeSession() {
		ses.close();
		ses = null;
	}

}
