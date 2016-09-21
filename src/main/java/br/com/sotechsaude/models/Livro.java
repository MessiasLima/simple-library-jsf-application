package br.com.sotechsaude.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkLivro;
	
	@Column(nullable=false)
	private String livro;
	
	@Column(columnDefinition="text")
	private String autor;
	
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	
	public int getPkLivro() {
		return pkLivro;
	}

	public void setPkLivro(int pkLivro) {
		this.pkLivro = pkLivro;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pkLivro;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (pkLivro != other.pkLivro)
			return false;
		return true;
	}
}
