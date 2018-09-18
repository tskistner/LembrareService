package com.furb.lembrare.tables.user;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int idUsuario;
	private String nmUsuario, dsEmail, dsSenha;
	private Date dtNascimento, dtCriacao, dtAtualizacao;

	public Usuario(String nmUsuario, String dsEmail, String dsSenha, Date dtNascimento, Date dtCriacao,
			Date dtAtualizacao) {
		super();
		this.nmUsuario = nmUsuario;
		this.dsEmail = dsEmail;
		this.dsSenha = dsSenha;
		this.dtNascimento = dtNascimento;
		this.dtCriacao = dtCriacao;
		this.dtAtualizacao = dtAtualizacao;
	}
	
	public Usuario() {
		super();
	}

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

}
