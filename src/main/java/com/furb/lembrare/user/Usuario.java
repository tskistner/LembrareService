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

	@GeneratedValue
	@Id
	private Long idUsuario;
	
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String dsSenha;
	private String nmPessoa;
	private String dsCpf;
	private Date dtNascimento;
	private String ieSexo;
	private String dsCidadeNatal;
	private String dsCidadeAtual;
	private String dsEndereco;
	private String nrTelefone;
	private String ieUsarSenha;
	private String ieFase;
	private String qtTempo;
	private String dsMedicacao;
	private String ieAntecedentes;
	private String dsOutrasDoencas;	
	
	
	public Usuario(Date dtAtualizacao, Date dtCriacao, String dsSenha, String nmPessoa, String dsCpf, Date dtNascimento, String ieSexo,
			String dsCidadeNatal, String dsCidadeAtual, String dsEndereco, String nrTelefone, String ieUsarSenha,
			String ieFase, String qtTempo, String dsMedicacao, String ieAntecedentes, String dsOutrasDoencas) {
		super();
		this.dtAtualizacao = new Date(System.currentTimeMillis());
		this.dtCriacao = new Date(System.currentTimeMillis());
		this.dsSenha = dsSenha;
		this.nmPessoa = nmPessoa;
		this.dsCpf = dsCpf;
		this.dtNascimento = dtNascimento;
		this.ieSexo = ieSexo;
		this.dsCidadeNatal = dsCidadeNatal;
		this.dsCidadeAtual = dsCidadeAtual;
		this.dsEndereco = dsEndereco;
		this.nrTelefone = nrTelefone;
		this.ieUsarSenha = ieUsarSenha;
		this.ieFase = ieFase;
		this.qtTempo = qtTempo;
		this.dsMedicacao = dsMedicacao;
		this.ieAntecedentes = ieAntecedentes;
		this.dsOutrasDoencas = dsOutrasDoencas;
	}

	public Usuario(Date dtAtualizacao, String dsSenha, String nmPessoa, String dsCpf, Date dtNascimento, String ieSexo,
			String dsCidadeNatal, String dsCidadeAtual, String dsEndereco, String nrTelefone, String ieUsarSenha) {
		super();
		this.dtAtualizacao = new Date(System.currentTimeMillis());
		this.dsSenha = dsSenha;
		this.nmPessoa = nmPessoa;
		this.dsCpf = dsCpf;
		this.dtNascimento = dtNascimento;
		this.ieSexo = ieSexo;
		this.dsCidadeNatal = dsCidadeNatal;
		this.dsCidadeAtual = dsCidadeAtual;
		this.dsEndereco = dsEndereco;
		this.nrTelefone = nrTelefone;
		this.ieUsarSenha = ieUsarSenha;
		
	}
	
	public Usuario(String ieFase, String qtTempo, String dsMedicacao, String ieAntecedentes, String dsOutrasDoencas) {
		super();
		this.ieFase = ieFase;
		this.qtTempo = qtTempo;
		this.dsMedicacao = dsMedicacao;
		this.ieAntecedentes = ieAntecedentes;
		this.dsOutrasDoencas = dsOutrasDoencas;
	}

	public Usuario() {
		super();
	}

	public String getIeFase() {
		return ieFase;
	}

	public void setIeFase(String ieFase) {
		this.ieFase = ieFase;
	}

	public String getQtTempo() {
		return qtTempo;
	}

	public void setQtTempo(String qtTempo) {
		this.qtTempo = qtTempo;
	}

	public String getDsMedicacao() {
		return dsMedicacao;
	}

	public void setDsMedicacao(String dsMedicacao) {
		this.dsMedicacao = dsMedicacao;
	}

	public String getIeAntecedentes() {
		return ieAntecedentes;
	}

	public void setIeAntecedentes(String ieAntecedentes) {
		this.ieAntecedentes = ieAntecedentes;
	}

	public String getDsOutrasDoencas() {
		return dsOutrasDoencas;
	}

	public void setDsOutrasDoencas(String dsOutrasDoencas) {
		this.dsOutrasDoencas = dsOutrasDoencas;
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
		setIeFase(other.getIeFase());
		setQtTempo(other.getQtTempo());
		setDsMedicacao(other.getDsMedicacao());
		setIeAntecedentes(other.getIeAntecedentes());
		setDsOutrasDoencas(other.getDsOutrasDoencas());
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

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
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
