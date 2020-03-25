package com.dev.republica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.republica.model.Republica;

public interface RepublicaService {

	Republica getRepublica(Long id);

	Republica save(Republica republica);

	ResponseEntity<Republica> update(Long id, Republica republica);

	ResponseEntity<?> deleteById(Long id);

	List<Republica> getRepublicasDisponiveis();

	List<Republica> getRepublicasByNome(String nome);
	
}
