package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;
import com.dev.republica.repository.HistoricoRepository;

@Service
@Transactional
public class HistoricoServiceImpl implements HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;

	public HistoricoServiceImpl(HistoricoRepository historicoRepository) {
		this.historicoRepository = historicoRepository;
	}

	public Historico getHistorico(Long id) {
		return historicoRepository.findById(id).orElse(null);
	}

	public Historico save(Historico historico) {
		return historicoRepository.save(historico);
	}

	public List<Historico> getMoradorHistoricoByMorador(Morador morador) {
		return historicoRepository.findByMorador(morador);
	}
}
