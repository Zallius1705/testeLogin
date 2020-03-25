package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Solicitacao;
import com.dev.republica.repository.SolicitacaoRepository;

@Service
@Transactional
public class SolicitacaoServiceImpl implements SolicitacaoService{

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository) {
		this.solicitacaoRepository = solicitacaoRepository;
	}

	@Override
	public Solicitacao save(Solicitacao solicitacao) {
		return solicitacaoRepository.save(solicitacao);
	}

	@Override
	public List<Solicitacao> getSolicitacoes() {
		return solicitacaoRepository.findAll();
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		return solicitacaoRepository.findById(id).map(record -> {
			solicitacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}