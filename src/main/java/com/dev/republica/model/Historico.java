package com.dev.republica.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Morador morador;

	@ManyToOne
	@NotNull
	@JsonIgnoreProperties({ "moradores" })
	private Republica republica;

	@NotNull
	private Date dataEntrada;

	private Date dataSaida;

	public Historico() {
	}

	public Historico(@NotNull Morador morador, @NotBlank Republica republica, @NotNull Date dataEntrada) {
		this.morador = morador;
		this.republica = republica;
		this.dataEntrada = dataEntrada;
	}

	public Historico(Long id, @NotNull Morador morador, @NotBlank Republica republica, @NotNull Date dataEntrada,
			Date dataSaida) {
		this.id = id;
		this.morador = morador;
		this.republica = republica;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
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

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

}
