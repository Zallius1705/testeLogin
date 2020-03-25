package com.dev.republica.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.Morador;
import com.dev.republica.model.MoradorTarefa;
import com.dev.republica.model.MoradorTarefaId;
import com.dev.republica.model.Tarefa;

@Validated
public interface MoradorTarefaService {

	MoradorTarefa save(MoradorTarefa moradorTarefa);
	
	MoradorTarefa getMoradorTarefa(MoradorTarefaId id);
	
	ResponseEntity<?> deleteById(Morador morador, Tarefa tarefa);
	
}
