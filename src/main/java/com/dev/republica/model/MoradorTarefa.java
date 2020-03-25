package com.dev.republica.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MoradorTarefa {

	@EmbeddedId
	private MoradorTarefaId pk;

	private String descricao;

	private boolean finalizada;

	public MoradorTarefa() {
	}

	public MoradorTarefa(Morador morador, Tarefa tarefa, String descricao, boolean finalizada) {
		pk = new MoradorTarefaId();
		pk.setMorador(morador);
		pk.setTarefa(tarefa);
		this.descricao = descricao;
		this.finalizada = finalizada;
	}

	public MoradorTarefaId getPk() {
		return pk;
	}

	public void setPk(MoradorTarefaId pk) {
		this.pk = pk;
	}

	@JsonIgnore
	public Morador getMorador() {
		return this.pk.getMorador();
	}

	@JsonIgnore
	public Tarefa getTarefa() {
		return this.pk.getTarefa();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

}
