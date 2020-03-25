package com.dev.republica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;
import com.dev.republica.service.HistoricoService;
import com.dev.republica.service.MoradorService;

@RestController
@RequestMapping("/morador/historicos")
@CrossOrigin//(origins = "http://localhost:4200")
public class HistoricoController {

	private HistoricoService historicoService;
	private MoradorService moradorService;

	public HistoricoController(HistoricoService historicoService, MoradorService moradorService) {
		this.historicoService = historicoService;
		this.moradorService = moradorService;
	}

	@GetMapping("/{id}")
	public Historico getHistoricoById(@PathVariable Long id) {
		return historicoService.getHistorico(id);
	}

	@GetMapping("/morador/{id}")
	public List<Historico> getMoradorHistoricoByMorador(@PathVariable Long id) {
		Morador morador = moradorService.getMorador(id);
		return historicoService.getMoradorHistoricoByMorador(morador);
	}

}
