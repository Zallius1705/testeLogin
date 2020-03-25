package com.dev.republica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.republica.model.Solicitacao;

public interface SolicitacaoService {

	public Solicitacao save(Solicitacao solicitacao);

	public List<Solicitacao> getSolicitacoes();
	
	public ResponseEntity<?> deleteById(Long id);

}