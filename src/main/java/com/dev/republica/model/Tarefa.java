package com.dev.republica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnoreProperties({ "moradores" })
	private Republica republica;

	@NotNull
	private Date dataAgendamento;

	@OneToMany(mappedBy = "pk.morador", fetch = FetchType.LAZY)
	private List<MoradorTarefa> moradorTarefas = new ArrayList<>();

	@NotBlank
	private String descricao;

	@NotNull
	private Date dataTermino;

	@NotNull
	private boolean finalizada;

	public Tarefa() {
	}

	public Tarefa(Long id, Republica republica, @NotNull Date dataAgendamento, List<MoradorTarefa> moradorTarefas,
			@NotBlank String descricao, @NotNull Date dataTermino, @NotNull boolean finalizada) {
		this.id = id;
		this.republica = republica;
		this.dataAgendamento = dataAgendamento;
		this.moradorTarefas = moradorTarefas;
		this.descricao = descricao;
		this.dataTermino = dataTermino;
		this.finalizada = finalizada;
	}

	public Long getId() {
		return id;
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

	public List<MoradorTarefa> getMoradorTarefas() {
		return moradorTarefas;
	}

	public void setMoradorTarefas(List<MoradorTarefa> moradorTarefas) {
		this.moradorTarefas = moradorTarefas;
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

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

}
