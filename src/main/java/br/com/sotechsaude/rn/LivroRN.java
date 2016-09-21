package br.com.sotechsaude.rn;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.sotechsaude.dao.Dao;
import br.com.sotechsaude.models.Livro;
import br.com.sotechsaude.repository.Livros;

public class LivroRN implements Serializable{

	private static final long serialVersionUID = 1L;

	private Dao<Livro> daoLivro = new Dao<Livro>();
	
	@Inject
	private Livros repLivros;
	
	public void salvar(Livro livro){
		try {
			daoLivro.inserir(livro);
			exibeSucesso("Salvo com sucesso", null);
		} catch (Exception e) {
			exibeErro("Erro ao salvar", null);
			e.printStackTrace();
		}
	}
	
	public void excluir(Livro livro){
		try {
			daoLivro.excluir(livro);
			exibeSucesso("Removido com sucesso", null);
		} catch (Exception e) {
			exibeErro("Erro ao remover", null);
			e.printStackTrace();
		}
	}
	
	public List<Livro> buscaTodosLivros(){
		return repLivros.buscaTodosLivros();
	}
	
	public List<Livro> buscaLivrosPorNome(String livro){
		return repLivros.buscaLivrosPorNome(livro);
	}
	
	public void exibeErro(String cabecalho, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, cabecalho, mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void exibeSucesso(String cabecalho, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, cabecalho, mensagem));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
}
