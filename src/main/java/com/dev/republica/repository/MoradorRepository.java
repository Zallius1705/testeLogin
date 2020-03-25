package com.dev.republica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;

public interface MoradorRepository extends JpaRepository<Morador, Long> {

	List<Morador> findByRepublica(Republica republica);

	List<Morador> findByNomeContaining(String nome);
}
