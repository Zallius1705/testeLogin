package com.dev.republica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

	List<Historico> findByMorador(Morador morador);
}
