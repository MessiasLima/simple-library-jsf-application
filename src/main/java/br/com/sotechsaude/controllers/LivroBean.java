package br.com.sotechsaude.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sotechsaude.models.Livro;
import br.com.sotechsaude.rn.LivroRN;

@Named
@RequestScoped
public class LivroBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	
	@Inject
	private LivroRN rnLivro;
	private String buscaLivro;
	private List<Livro> listLivros;	
	private Boolean btnExcluir;
	
	public LivroBean() {
		setLivro(new Livro());	
		setBtnExcluir(true);
	}
	
	public void salvar(){
		rnLivro.salvar(getLivro());
		setLivro(new Livro());
		setBtnExcluir(true);
	}

	public String remover(){
		rnLivro.excluir(getLivro());
		return limpar();
	}
	
	public void aplicaObjeto(SelectEvent event){
		setLivro((Livro)event.getObject());
		setBtnExcluir(false);
	}
	
	public String limpar(){
		return "livros?faces-redirect=true";
	}
	
	public void buscaTodosLivros(){
		if(getBuscaLivro().equals(" ")){
			setListLivros(rnLivro.buscaTodosLivros());
		}
		setListLivros(rnLivro.buscaLivrosPorNome(getBuscaLivro()));
	}
	
	/*Get's and Set's*/
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getBuscaLivro() {
		return buscaLivro;
	}

	public void setBuscaLivro(String buscaLivro) {
		this.buscaLivro = buscaLivro;
	}

	public List<Livro> getListLivros() {
		return listLivros;
	}

	public void setListLivros(List<Livro> listLivros) {
		this.listLivros = listLivros;
	}

	public Boolean getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(Boolean btnExcluir) {
		this.btnExcluir = btnExcluir;
	}	
}
