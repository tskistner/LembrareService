package com.furb.lembrare.complementaryInformation;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.furb.lembrare.TableInterface;

@Entity
@Table(name = "informacao_complementar")
public class InformacaoComplementar implements TableInterface {

	@Id
	@GeneratedValue
	private Long idInfoCompl;
	private Long idUsuario;
	private Long idPessoa;
	private String nmPessoa;
	private Date dtNascimento;

	public InformacaoComplementar(Long idUsuario, Long idPessoa, String nmPessoa, Date dtNascimento) {
		super();
		this.idUsuario = idUsuario;
		this.idPessoa = idPessoa;
		this.nmPessoa = nmPessoa;
		this.dtNascimento = dtNascimento;
	}
	
	public InformacaoComplementar() {
		super();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Long getIdInfoCompl() {
		return idInfoCompl;
	}

	@Override
	public ArrayList<Object> getRegisters() {
		ArrayList<Object> registers = new ArrayList<>();
		registers.add(getLine("id", getIdInfoCompl(), false));
		registers.add(getLine("ai_nm_pessoa", getNmPessoa(), true));
		registers.add(getLine("ai_dt_nascimento", getDtNascimento(), true));
		registers.add(getLine("ai_id_pessoa", getIdPessoa(), false));
		return registers;
	}

}
