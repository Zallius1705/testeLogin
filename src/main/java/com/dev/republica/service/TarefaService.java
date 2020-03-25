package com.dev.republica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.model.Tarefa;

public interface TarefaService {

	Tarefa getTarefa(Long id);

	List<Tarefa> getTarefaByRepublica(Republica republica);

	List<Tarefa> getTarefaByRepublicaAndMorador(Republica republica, Morador morador);

	Tarefa save(Tarefa tarefa);

	Tarefa update(Long id, Tarefa tarefa);

	ResponseEntity<?> deleteById(Long id);

}
