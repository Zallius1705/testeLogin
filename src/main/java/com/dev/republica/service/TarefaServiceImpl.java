package com.dev.republica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.model.Tarefa;
import com.dev.republica.repository.TarefaRepository;

@Service
@Transactional
public class TarefaServiceImpl implements TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;

	public TarefaServiceImpl(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}

	public Tarefa getTarefa(Long id) {
		return tarefaRepository.findById(id).orElse(null);
	}

	public List<Tarefa> getTarefaByRepublica(Republica republica) {
		return tarefaRepository.findByRepublica(republica);
	}

	public Tarefa save(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	public Tarefa update(Long id, Tarefa tarefa) {
		Tarefa t = tarefaRepository.getOne(id);
		t.setDataAgendamento(tarefa.getDataAgendamento());
		t.setDataTermino(tarefa.getDataTermino());
		t.setDescricao(tarefa.getDescricao());
		t.setFinalizada(tarefa.isFinalizada());
		t.setMoradorTarefas(tarefa.getMoradorTarefas());
		return tarefaRepository.save(t);
	}

	public ResponseEntity<?> deleteById(Long id) {
		return tarefaRepository.findById(id).map(record -> {
			tarefaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	public List<Tarefa> getTarefaByRepublicaAndMorador(Republica republica, Morador morador) {
		return tarefaRepository.findByRepublicaAndMorador(republica, morador);
	}

}
