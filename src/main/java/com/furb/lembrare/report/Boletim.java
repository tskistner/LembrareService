package com.furb.lembrare.report;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;

import com.furb.lembrare.TableInterface;

@Entity
@Table(name = "boletim")
@org.hibernate.annotations.DynamicUpdate
public class Boletim implements TableInterface {

	@GeneratedValue
	@Id
	private Long idBoletim;
	private Long idUsuario;
	private Long idCategoria;
	private String dsPessoa;
	private String dsBoletim;
	private Timestamp dtAtualizacao;

	public Boletim() {
		super();
	}

	public Boletim(Long idBoletim, Long idUsuario, Long idCategoria, String dsPessoa, String dsBoletim,
			Timestamp dtAtualizacao) {
		super();
		this.idBoletim = idBoletim;
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.dsPessoa = dsPessoa;
		this.dsBoletim = dsBoletim;
		this.dtAtualizacao = new Timestamp(System.currentTimeMillis());
	}

	public Boletim(Long idUsuario, Long idCategoria, String dsPessoa, String dsBoletim) {
		super();
		this.idUsuario = idUsuario;
		this.idCategoria = idCategoria;
		this.dsPessoa = dsPessoa;
		this.dsBoletim = dsBoletim;
		this.dtAtualizacao = new Timestamp(System.currentTimeMillis());
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDsPessoa() {
		return dsPessoa;
	}

	public void setDsPessoa(String dsPessoa) {
		this.dsPessoa = dsPessoa;
	}

	public String getDsBoletim() {
		return dsBoletim;
	}

	public void setDsBoletim(String dsBoletim) {
		this.dsBoletim = dsBoletim;
	}

	public Timestamp getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Timestamp dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Long getidBoletim() {
		return idBoletim;
	}
	
	@Override
	public ArrayList<Object> getRegisters() {
		ArrayList<Object> registers = new ArrayList<>();
		registers.add(getLine("R_ID", getidBoletim(), false));
		registers.add(getLine("R_DT_REGISTER", new Date(getDtAtualizacao().getTime()), true));
		registers.add(getLine("R_DS_PERSON", getDsPessoa(), true));
		registers.add(getLine("R_GRID_DS_REPORT", getDsBoletim().length() > 50 ? getDsBoletim().substring(0, 50) + "..." : getDsBoletim(), true));
		registers.add(getLine("R_DS_REPORT", getDsBoletim(), false));
		return registers;
	}

}
