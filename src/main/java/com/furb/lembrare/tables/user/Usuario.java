package com.furb.lembrare.tables.user;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long idUsuario;
	private Date dtAtualizacao;
	private String dsEmail;
	private String dsSenha;
	private String nmPessoa;
	private String dsCpf;
	private Date dtNascimento;
	private String ieSexo;
	private String dsCidadeNatal;
	private String dsCidadeAtual;
	private String dsEndereco;
	private int nrTelefone;

	public Usuario(String dsEmail, String nmPessoa, String dsCpf, Date dtNascimento,
			String ieSexo, String dsCidadeNatal, String dsCidadeAtual,
			String dsEndereco, int nrTelefone) {
		super();
		this.dtAtualizacao = new Date(System.currentTimeMillis());
		this.dsEmail = dsEmail;
		this.nmPessoa = nmPessoa;
		this.dsCpf = dsCpf;
		this.dtNascimento = dtNascimento;
		this.ieSexo = ieSexo;
		this.dsCidadeNatal = dsCidadeNatal;
		this.dsCidadeAtual = dsCidadeAtual;
		this.dsEndereco = dsEndereco;
		this.nrTelefone = nrTelefone;
	}
	
	public Usuario() {
		super();
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
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

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public String getDsCpf() {
		return dsCpf;
	}

	public void setDsCpf(String dsCpf) {
		this.dsCpf = dsCpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getIeSexo() {
		return ieSexo;
	}

	public void setIeSexo(String ieSexo) {
		this.ieSexo = ieSexo;
	}

	public String getDsCidadeNatal() {
		return dsCidadeNatal;
	}

	public void setDsCidadeNatal(String dsCidadeNatal) {
		this.dsCidadeNatal = dsCidadeNatal;
	}

	public String getDsCidadeAtual() {
		return dsCidadeAtual;
	}

	public void setDsCidadeAtual(String dsCidadeAtual) {
		this.dsCidadeAtual = dsCidadeAtual;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public int getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(int nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	
	
}
