package com.dev.republica.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Republica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	private Date dataFundacao;

	private Date dataExtincao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ENDERECO_ID", nullable = false)
	@NotNull
	private Endereco endereco;

	@NotBlank
	private String vantagens;

	@NotNull
	private byte numeroVagas;

	@NotNull
	private byte numeroVagasDisponiveis;

	@NotBlank
	private String tipoImovel;

	@NotBlank
	private String genero;

	private String linkEstatuto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REPRESENTANTE_ID", nullable = false)
	@JsonIgnoreProperties({ "republica", "historico" })
	private Morador representante;

	@OneToMany(mappedBy = "republica")
	@JsonIgnoreProperties({ "republica", "historico" })
	private List<Morador> moradores = new ArrayList<>();

	@OneToMany(mappedBy = "republica")
	@JsonIgnore
	private List<ReceitaDespesa> receitaDespesas = new ArrayList<>();

	public Republica() {
	}

	public Republica(Long id, @NotBlank String nome, @NotNull Date dataFundacao, Date dataExtincao,
			@NotNull Endereco endereco, @NotBlank String vantagens, @NotNull byte numeroVagas,
			@NotNull byte numeroVagasDisponiveis, @NotBlank String tipoImovel, @NotBlank String genero,
			String linkEstatuto, Morador representante, List<Morador> moradores) {
		this.id = id;
		this.nome = nome;
		this.dataFundacao = dataFundacao;
		this.dataExtincao = dataExtincao;
		this.endereco = endereco;
		this.vantagens = vantagens;
		this.numeroVagas = numeroVagas;
		this.numeroVagasDisponiveis = numeroVagasDisponiveis;
		this.tipoImovel = tipoImovel;
		this.genero = genero;
		this.linkEstatuto = linkEstatuto;
		this.representante = representante;
		this.moradores = moradores;
	}

	public void alterarRepresentante(Morador novoRepresentante) {
		this.representante.setRepresentante(false);
		this.setRepresentante(novoRepresentante);
	}

	public boolean addMorador(Morador morador) {
		return moradores.add(morador);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public Date getDataExtincao() {
		return dataExtincao;
	}

	public void setDataExtincao(Date dataExtincao) {
		this.dataExtincao = dataExtincao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getVantagens() {
		return vantagens;
	}

	public void setVantagens(String vantagens) {
		this.vantagens = vantagens;
	}

	public byte getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(byte numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public byte getNumeroVagasDisponiveis() {
		return numeroVagasDisponiveis;
	}

	public void setNumeroVagasDisponiveis(byte numeroVagasDisponiveis) {
		this.numeroVagasDisponiveis = numeroVagasDisponiveis;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLinkEstatuto() {
		return linkEstatuto;
	}

	public void setLinkEstatuto(String linkEstatuto) {
		this.linkEstatuto = linkEstatuto;
	}

	public Morador getRepresentante() {
		return representante;
	}

	public void setRepresentante(Morador novoRepresentante) {
		novoRepresentante.setRepresentante(true);
		this.representante = novoRepresentante;
	}

	public List<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(List<Morador> moradores) {
		this.moradores = moradores;
	}

	public List<ReceitaDespesa> getReceitaDespesas() {
		return receitaDespesas;
	}

	public void setReceitaDespesas(List<ReceitaDespesa> receitaDespesas) {
		this.receitaDespesas = receitaDespesas;
	}

}
