package com.furb.lembrare.address;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.furb.lembrare.TableInterface;

@Entity
@Table(name = "enderecos")
public class Enderecos implements TableInterface {

	@Id
	@GeneratedValue
	private Long idEndereco;
	private Long idUsuario;
	private String dsLocal;
	private String dsLocalizacao;

	public Enderecos(Long idUsuario, String dsLocal, String dsLocalizacao) {
		super();
		this.idUsuario = idUsuario;
		this.dsLocal = dsLocal;
		this.dsLocalizacao = dsLocalizacao;
	}
	
	public Enderecos() {
		super();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDsLocal() {
		return dsLocal;
	}

	public void setDsLocal(String dsLocal) {
		this.dsLocal = dsLocal;
	}

	public String getDsLocalizacao() {
		return dsLocalizacao;
	}

	public void setDsLocalizacao(String dsLocalizacao) {
		this.dsLocalizacao = dsLocalizacao;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	@Override
	public ArrayList<Object> getRegisters() {
		ArrayList<Object> registers = new ArrayList<>();
		registers.add(getLine("id", getIdEndereco(), false));
		registers.add(getLine("end_ds_local", getDsLocal(), true));
		registers.add(getLine("end_ds_localizacao", getDsLocalizacao(), true));
		return registers;
	}

}
