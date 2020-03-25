package com.dev.republica.dto;

import java.util.Date;
import java.util.List;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;

public class TarefaDto {

	private Republica republica;

	private Date dataAgendamento;

	private List<Morador> moradores;

	private String descricao;

	private Date dataTermino;

	public TarefaDto() {
	}

	public TarefaDto(Republica republica, Date dataAgendamento, 
			List<Morador> moradores, String descricao,
			Date dataTermino) {
		this.republica = republica;
		this.dataAgendamento = dataAgendamento;
		this.moradores = moradores;
		this.descricao = descricao;
		this.dataTermino = dataTermino;
	}

	public Republica getRepublica() {
		return republica;
	}

	public void setRepublica(Republica republica) {
		this.republica = republica;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}
}
