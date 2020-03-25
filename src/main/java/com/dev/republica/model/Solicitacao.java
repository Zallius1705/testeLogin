package com.dev.republica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "idMorador", referencedColumnName = "id")
    private Morador solicitante;

    @ManyToOne
    @JoinColumn(name = "idRepublica", referencedColumnName = "id")
    private Republica republica;

	public Solicitacao(Long id, Morador solicitante, Republica republica) {
		this.id = id;
		this.solicitante = solicitante;
		this.republica = republica;
	}
    
	public Solicitacao() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Morador getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Morador solicitante) {
		this.solicitante = solicitante;
	}

	public Republica getRepublica() {
		return republica;
	}

	public void setRepublica(Republica republica) {
		this.republica = republica;
	}
	 
}
