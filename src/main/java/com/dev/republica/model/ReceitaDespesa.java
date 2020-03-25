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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReceitaDespesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JsonIgnore
	private Republica republica;

	@NotBlank
	private String tipo; // despesa ou receita

	@NotBlank
	private String descricao;

	@NotNull
	private float valor;

	private byte numeroParcelas;

	@NotNull
	private Date dataLancamento;

	@NotNull
	private Date dataVencimentoRecebimento;

	@NotNull
	private boolean efetivado;

	@NotNull
	private boolean aprovado;

	@OneToMany(mappedBy = "pk.receitaDespesa", fetch = FetchType.LAZY)
	private List<MoradorReceitaDespesa> moradorReceitaDespesas = new ArrayList<>();

	public ReceitaDespesa() {
	}

	public ReceitaDespesa(Long id, @NotNull Republica republica, @NotBlank String tipo, @NotBlank String descricao,
			@NotNull float valor, byte numeroParcelas, @NotNull Date dataLancamento,
			@NotNull Date dataVencimentoRecebimento, @NotNull boolean efetivado, @NotNull boolean aprovado,
			List<MoradorReceitaDespesa> moradorReceitaDespesas) {
		this.id = id;
		this.republica = republica;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.numeroParcelas = numeroParcelas;
		this.dataLancamento = dataLancamento;
		this.dataVencimentoRecebimento = dataVencimentoRecebimento;
		this.efetivado = efetivado;
		this.aprovado = aprovado;
		this.moradorReceitaDespesas = moradorReceitaDespesas;
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

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataVencimentoRecebimento() {
		return dataVencimentoRecebimento;
	}

	public void setDataVencimentoRecebimento(Date dataVencimentoRecebimento) {
		this.dataVencimentoRecebimento = dataVencimentoRecebimento;
	}

	public boolean isEfetivado() {
		return efetivado;
	}

	public void setEfetivado(boolean efetivado) {
		this.efetivado = efetivado;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public List<MoradorReceitaDespesa> getMoradorReceitaDespesas() {
		return moradorReceitaDespesas;
	}

	public void setMoradorReceitaDespesas(List<MoradorReceitaDespesa> moradorReceitaDespesas) {
		this.moradorReceitaDespesas = moradorReceitaDespesas;
	}

}
