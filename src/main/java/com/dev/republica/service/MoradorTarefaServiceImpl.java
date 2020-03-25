package com.dev.republica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Morador;
import com.dev.republica.model.MoradorTarefa;
import com.dev.republica.model.MoradorTarefaId;
import com.dev.republica.model.Tarefa;
import com.dev.republica.repository.MoradorTarefaRepository;

@Service
@Transactional
public class MoradorTarefaServiceImpl implements MoradorTarefaService {

	@Autowired
	MoradorTarefaRepository moradorTarefaRepository;

	public MoradorTarefaServiceImpl(MoradorTarefaRepository moradorTarefaRepository) {
		this.moradorTarefaRepository = moradorTarefaRepository;
	}

	public MoradorTarefa save(MoradorTarefa moradorTarefa) {
		return moradorTarefaRepository.save(moradorTarefa);
	}

	public MoradorTarefa getMoradorTarefa(MoradorTarefaId id) {
		return moradorTarefaRepository.findById(id).orElse(null);
	}

	public ResponseEntity<?> deleteById(Morador morador, Tarefa tarefa) {
		MoradorTarefaId mtId = new MoradorTarefaId();
		mtId.setMorador(morador);
		mtId.setTarefa(tarefa);
		return moradorTarefaRepository.findById(mtId).map(record -> {
			moradorTarefaRepository.deleteById(mtId);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
