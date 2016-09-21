package br.com.sotechsaude.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.sotechsaude.utils.JPAUtil;

public class Dao<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager = JPAUtil.getEntityManager();

	public T inserir(T entity) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.persist(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.err.println(e.getMessage());
			e.printStackTrace();
		} 
		return entity;
	}

	public T salvar(T entity) {
		try {
			manager.getTransaction().begin();
			manager.merge(entity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			manager.getTransaction().rollback();
		} 
		return entity;
	}

	public void excluir(T entity) throws Exception {
		try {
			manager.getTransaction().begin();
			manager.remove(entity);
			manager.getTransaction().commit();			
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.err.println(e.getMessage());
			throw new Exception(e);
		} 
	}
}
