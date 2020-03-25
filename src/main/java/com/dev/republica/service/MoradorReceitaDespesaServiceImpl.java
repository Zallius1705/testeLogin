package com.dev.republica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.MoradorReceitaDespesa;
import com.dev.republica.model.MoradorReceitaDespesaId;
import com.dev.republica.repository.MoradorReceitaDespesaRepository;

@Service
@Transactional
public class MoradorReceitaDespesaServiceImpl implements MoradorReceitaDespesaService {
	
	@Autowired
	MoradorReceitaDespesaRepository moradorReceitaDespesaRepository;

	public MoradorReceitaDespesaServiceImpl(MoradorReceitaDespesaRepository moradorReceitaDespesaRepository) {
		this.moradorReceitaDespesaRepository = moradorReceitaDespesaRepository;
	}

	public MoradorReceitaDespesa save(MoradorReceitaDespesa moradorReceitaDespesa) {
		return moradorReceitaDespesaRepository.save(moradorReceitaDespesa);
	}

	public MoradorReceitaDespesa getMoradorReceitaDespesa(MoradorReceitaDespesaId id) {
		return moradorReceitaDespesaRepository.findById(id).orElse(null);
	}

}
