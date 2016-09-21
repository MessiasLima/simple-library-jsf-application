package br.com.sotechsaude.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;
	private static EntityManager manager;

	static {
		factory = Persistence.createEntityManagerFactory("LIVROPU");
	}

	public static EntityManager getEntityManager() {
		if (manager == null) {
			manager = factory.createEntityManager();
			return manager;
		} else {
			return manager;
		}
	}

	public static void close() {
		factory.close();
	}

}