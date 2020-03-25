package com.dev.republica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String tipo;

	@NotNull
	private Date dataFeedback;

	@NotBlank
	private String descricao;

    @ManyToOne
    @JoinColumn(name = "idMorador", referencedColumnName = "id")
    @JsonIgnoreProperties({ "republica", "historico" })
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "idRepublica", referencedColumnName = "id")
    @JsonIgnoreProperties({ "moradores" })
    private Republica republica;

	@NotNull
	private boolean anonimo;

	private Date dataSolucao;

	private int idade;

	private String status;

	public Feedback() {
	}

	public Feedback(Long id, @NotBlank String tipo, @NotNull Date dataFeedback, @NotBlank String descricao,
			Morador morador, @NotNull boolean anonimo, Republica republica, Date dataSolucao, int idade,
			String status) {
		this.id = id;
		this.tipo = tipo;
		this.dataFeedback = dataFeedback;
		this.descricao = descricao;
		this.morador = morador;
		this.anonimo = anonimo;
		this.republica = republica;
		this.dataSolucao = dataSolucao;
		this.idade = idade;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Morador getmorador() {
		return morador;
	}

	public void setmorador(Morador morador) {
		this.morador = morador;
	}

	public Republica getRepublica() {
		return republica;
	}

	public void setRepublica(Republica republica) {
		this.republica = republica;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataFeedback() {
		return dataFeedback;
	}

	public void setDataFeedback(Date dataFeedback) {
		this.dataFeedback = dataFeedback;
	}

	public Date getDataSolucao() {
		return dataSolucao;
	}

	public void setDataSolucao(Date dataSolucao) {
		this.dataSolucao = dataSolucao;
	}

	public boolean isAnonimo() {
		return anonimo;
	}

	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
