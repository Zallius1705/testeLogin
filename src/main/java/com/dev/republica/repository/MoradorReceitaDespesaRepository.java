package com.dev.republica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.MoradorReceitaDespesa;
import com.dev.republica.model.MoradorReceitaDespesaId;

public interface MoradorReceitaDespesaRepository extends JpaRepository<MoradorReceitaDespesa, MoradorReceitaDespesaId> {

}
