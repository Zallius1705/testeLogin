package com.dev.republica.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class MoradorReceitaDespesaId implements Serializable {

	private static final long serialVersionUID = -8079659733969001854L;

	@ManyToOne
	private Morador morador;

	@ManyToOne
	private ReceitaDespesa receitaDespesa;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "moradorReceitaDespesas", "republica" })
	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	@JsonIgnoreProperties({ "moradorReceitaDespesas" })
	public ReceitaDespesa getReceitaDespesa() {
		return receitaDespesa;
	}

	public void setReceitaDespesa(ReceitaDespesa receitaDespesa) {
		this.receitaDespesa = receitaDespesa;
	}

}
