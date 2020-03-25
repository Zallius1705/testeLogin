package com.dev.republica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.MoradorTarefa;
import com.dev.republica.model.MoradorTarefaId;

public interface MoradorTarefaRepository extends JpaRepository<MoradorTarefa, MoradorTarefaId> {

}
