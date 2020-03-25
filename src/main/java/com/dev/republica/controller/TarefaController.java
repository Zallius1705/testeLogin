package com.dev.republica.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.dto.TarefaDto;
import com.dev.republica.model.Morador;
import com.dev.republica.model.MoradorTarefa;
import com.dev.republica.model.Republica;
import com.dev.republica.model.Tarefa;
import com.dev.republica.repository.TarefaRepository;
import com.dev.republica.service.MoradorService;
import com.dev.republica.service.MoradorTarefaService;
import com.dev.republica.service.RepublicaService;
import com.dev.republica.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin//(origins = "http://localhost:4200")
public class TarefaController {

	TarefaService tarefaService;
	RepublicaService republicaService;
	MoradorTarefaService moradorTarefaService;
	MoradorService moradorService;
	TarefaRepository tarefaRepository;

	public TarefaController(TarefaService tarefaService, RepublicaService republicaService,
			MoradorTarefaService moradorTarefaService, MoradorService moradorService,
			TarefaRepository tarefaRepository) {
		this.tarefaService = tarefaService;
		this.republicaService = republicaService;
		this.moradorTarefaService = moradorTarefaService;
		this.moradorService = moradorService;
		this.tarefaRepository = tarefaRepository;
	}

	@GetMapping("republica/{id}")
	public List<Tarefa> getTarefasByRepublica(@PathVariable Long id) {
		Republica republica = republicaService.getRepublica(id);
		return tarefaService.getTarefaByRepublica(republica);
	}

	@GetMapping("republica/{idRepublica}/morador/{idMorador}")
	public List<Tarefa> getTarefasByMorador(@PathVariable Long idRepublica, @PathVariable Long idMorador) {
		Republica republica = republicaService.getRepublica(idRepublica);
		Morador morador = moradorService.getMorador(idMorador);
		if (morador.getRepublica() ==  republica) {
			return tarefaService.getTarefaByRepublicaAndMorador(republica, morador);
		}
		return null;
	}
	
	@PostMapping("/teste")
	public Tarefa create2(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	@PostMapping
	public Tarefa create(@RequestBody TarefaDto form) {
		Tarefa tarefa = new Tarefa();
		tarefa.setDataAgendamento(new Date());
		tarefa.setDataTermino(form.getDataTermino());
		tarefa.setDescricao(form.getDescricao());
		tarefa.setFinalizada(false);
		tarefa.setRepublica(form.getRepublica());
		tarefaService.save(tarefa);

		List<MoradorTarefa> moradorTarefas = new ArrayList<>();

		for (Morador morador : form.getMoradores()) {
			moradorTarefas.add(moradorTarefaService.save(new MoradorTarefa(morador, tarefa, "", false)));
		}

		tarefa.setMoradorTarefas(moradorTarefas);
		return tarefaService.save(tarefa);
	}

	@PutMapping("/{id}")
	public Tarefa update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		return tarefaService.update(id, tarefa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Tarefa tarefa = tarefaService.getTarefa(id);
		if (tarefa != null) {
			for (MoradorTarefa mt : tarefa.getMoradorTarefas()) {
				moradorTarefaService.deleteById(mt.getMorador(), mt.getTarefa());
			}
			return tarefaService.deleteById(id);
		}
		return null;
	}

	@PostMapping("republica/{idRepublica}/morador/{idMorador}/realizar")
	public void realizarTarefa(@PathVariable Long idRepublica, @PathVariable Long idMorador, @RequestBody MoradorTarefa moradorTarefa) {
		Morador morador = moradorService.getMorador(idMorador);
		Republica republica = republicaService.getRepublica(idRepublica);
		if (morador.getRepublica().equals(republica) && moradorTarefa != null) {
			moradorTarefaService.save(moradorTarefa);
		}
	}

}
