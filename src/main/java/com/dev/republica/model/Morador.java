package com.dev.republica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Morador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	private String apelido;

	@NotBlank
	private String telefone;

	@NotBlank
	private String linkRedeSocial;

	@NotBlank
	private String telefoneResponsavel1;

	@NotBlank
	private String telefoneResponsavel2;

	@NotBlank
	private String sexo;

	private boolean representante;

	@ManyToOne
	private Republica republica;

	@OneToMany(mappedBy = "pk.morador")
	@JsonIgnore
	private List<MoradorReceitaDespesa> moradorReceitaDespesas = new ArrayList<>();

	@OneToMany(mappedBy = "morador")
	@JsonIgnoreProperties({ "morador" })
	private List<Historico> historico = new ArrayList<>();

	public Morador() {
	}

	public Morador(Long id, @NotBlank String nome, String apelido, @NotBlank String telefone,
			@NotBlank String linkRedeSocial, @NotBlank String telefoneResponsavel1,
			@NotBlank String telefoneResponsavel2, @NotBlank String sexo) {
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.telefone = telefone;
		this.linkRedeSocial = linkRedeSocial;
		this.telefoneResponsavel1 = telefoneResponsavel1;
		this.telefoneResponsavel2 = telefoneResponsavel2;
		this.sexo = sexo;
		this.representante = false;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLinkRedeSocial() {
		return linkRedeSocial;
	}

	public void setLinkRedeSocial(String linkRedeSocial) {
		this.linkRedeSocial = linkRedeSocial;
	}

	public String getTelefoneResponsavel1() {
		return telefoneResponsavel1;
	}

	public void setTelefoneResponsavel1(String telefoneResponsavel1) {
		this.telefoneResponsavel1 = telefoneResponsavel1;
	}

	public String getTelefoneResponsavel2() {
		return telefoneResponsavel2;
	}

	public void setTelefoneResponsavel2(String telefoneResponsavel2) {
		this.telefoneResponsavel2 = telefoneResponsavel2;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isRepresentante() {
		return representante;
	}

	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}

	public Republica getRepublica() {
		return republica;
	}

	public void setRepublica(Republica republica) {
		this.republica = republica;
	}

	public List<MoradorReceitaDespesa> getMoradorReceitaDespesas() {
		return moradorReceitaDespesas;
	}

	public void setMoradorReceitaDespesas(List<MoradorReceitaDespesa> moradorReceitaDespesas) {
		this.moradorReceitaDespesas = moradorReceitaDespesas;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
}
