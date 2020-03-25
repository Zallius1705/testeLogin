package com.dev.republica.controller;

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

import com.dev.republica.model.Morador;
import com.dev.republica.service.MoradorService;

@RestController
@RequestMapping("/moradores")
@CrossOrigin//(origins = "http://localhost:4200")
public class MoradorController {

	private MoradorService moradorService;

	public MoradorController(MoradorService moradorService) {
		this.moradorService = moradorService;
	}

	@GetMapping
	public List<Morador> getMoradores() {
		return moradorService.getAllMoradores();
	}

	@GetMapping("/{id}")
	public Morador getMorador(@PathVariable Long id) {
		return moradorService.getMorador(id);
	}

	@PostMapping
	public Morador create(@RequestBody Morador morador) {
		morador.setRepresentante(false);
		return moradorService.save(morador);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Morador> update(@PathVariable("id") Long id, @RequestBody Morador morador) {
		return moradorService.update(id, morador);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return moradorService.deleteById(id);
	}

	@GetMapping("/{nome}/busca")
	public List<Morador> getMoradoresByName(@PathVariable String nome) {
		return moradorService.getMoradoresByNome(nome);
	}
}
