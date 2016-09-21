package br.com.sotechsaude.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.sotechsaude.models.Livro;
import br.com.sotechsaude.utils.JPAUtil;

public class Livros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	EntityManager manager = JPAUtil.getEntityManager();
	
	public List<Livro> buscaTodosLivros(){
		return manager.createQuery("FROM Livro l order by l.livro ASC", Livro.class).getResultList();
	}	
	
	public List<Livro> buscaLivrosPorNome(String livro){
		return manager.createQuery("FROM Livro l "
				+ " WHERE l.livro like :livro "
				+ " ORDER BY l.livro ASC", Livro.class).setParameter("livro", livro).getResultList();
	}	
	

}
