package com.dev.republica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;

public interface MoradorService {

	Morador getMorador(Long id);

	Morador save(Morador morador);

	ResponseEntity<Morador> update(Long id, Morador morador);

	ResponseEntity<?> deleteById(Long id);

	List<Morador> getAllMoradores();

	List<Morador> getMoradoresByRepublica(Republica republica);

	List<Morador> getMoradoresByNome(String nome);

}
