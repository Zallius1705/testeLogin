package com.dev.republica.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class MoradorReceitaDespesa {

	@EmbeddedId
	private MoradorReceitaDespesaId pk;

	private float valor;

	private float valorPago;

	public MoradorReceitaDespesa() {
	}

	public MoradorReceitaDespesa(Morador morador, ReceitaDespesa receitaDespesa, float valor, float valorPago) {
		pk = new MoradorReceitaDespesaId();
		pk.setMorador(morador);
		pk.setReceitaDespesa(receitaDespesa);
		this.valor = valor;
		this.valorPago = valorPago;
	}

	@JsonIgnoreProperties({ "tarefa" })
	public MoradorReceitaDespesaId getPk() {
		return pk;
	}

	public void setPk(MoradorReceitaDespesaId pk) {
		this.pk = pk;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getValorPago() {
		return valorPago;
	}

	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}

}
