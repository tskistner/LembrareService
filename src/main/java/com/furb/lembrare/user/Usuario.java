package com.furb.lembrare.user;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@org.hibernate.annotations.DynamicUpdate
public class Usuario {

	@Id
	@GeneratedValue
	private Long idUsuario;
	private Date dtAtualizacao;
	private String dsSenha;
	private String nmPessoa;
	private String dsCpf;
	private Date dtNascimento;
	private String ieSexo;
	private String dsCidadeNatal;
	private String dsCidadeAtual;
	private String dsEndereco;
	private Long nrTelefone;
	private String ieUsarSenha;

	public Usuario(String nmPessoa, String dsCpf, Date dtNascimento,
			String ieSexo, String dsCidadeNatal, String dsCidadeAtual,
			String dsEndereco, Long nrTelefone) {
		super();
		this.dtAtualizacao = new Date(System.currentTimeMillis());
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
	
	public void copyAll(Usuario other) {
		setNmPessoa(other.getNmPessoa());
		setDsCpf(other.getDsCpf());
		setDtNascimento(other.getDtNascimento());
		setIeSexo(other.getIeSexo());
		setDsCidadeNatal(other.getDsCidadeNatal());
		setDsCidadeAtual(other.getDsCidadeAtual());
		setDsEndereco(other.getDsEndereco());
		setNrTelefone(other.getNrTelefone());
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
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

	public Long getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(Long nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIeUsarSenha(String ieUsarSenha) {
		this.ieUsarSenha = ieUsarSenha;
	}
	
	public String getIeUsarSenha() {
		return ieUsarSenha;
	}
	
	
}
