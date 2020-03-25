package com.dev.republica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Republica;

public interface RepublicaRepository extends JpaRepository<Republica, Long> {

	List<Republica> findByNumeroVagasDisponiveisGreaterThanEqual(byte numeroVagasDisponiveis);

	List<Republica> findByNomeContaining(String nome);
}