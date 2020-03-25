package com.dev.republica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
}