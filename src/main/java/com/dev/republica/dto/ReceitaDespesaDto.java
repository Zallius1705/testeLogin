package com.dev.republica.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;

public class ReceitaDespesaDto {

	private Republica republica;

	private String tipo; // despesa ou receita

	private String descricao;

	private float valor;

	private byte numeroParcelas;

	private Date dataVencimentoRecebimento;

	private List<Morador> moradores = new ArrayList<>();

	public ReceitaDespesaDto() {
	}

	public ReceitaDespesaDto(Republica republica, String tipo, String descricao, float valor, byte numeroParcelas,
			Date dataVencimentoRecebimento, List<Morador> moradores) {
		this.republica = republica;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.numeroParcelas = numeroParcelas;
		this.dataVencimentoRecebimento = dataVencimentoRecebimento;
		this.moradores = moradores;
	}

	public Republica getRepublica() {
		return republica;
	}

	public void setRepublica(Republica republica) {
		this.republica = republica;
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

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public byte getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(byte numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Date getDataVencimentoRecebimento() {
		return dataVencimentoRecebimento;
	}

	public void setDataVencimentoRecebimento(Date dataVencimentoRecebimento) {
		this.dataVencimentoRecebimento = dataVencimentoRecebimento;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

}
