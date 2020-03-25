package com.dev.republica.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.dto.DataChart;
import com.dev.republica.model.Republica;
import com.dev.republica.service.GraficoService;
import com.dev.republica.service.RepublicaService;

@RestController
@RequestMapping("/chart")
@CrossOrigin//(origins = "http://localhost:4200")
public class GraficoController {
	
	@Autowired
	private GraficoService graficoService;
	
	@Autowired
	private RepublicaService republicaService;

	@GetMapping("/{id}/{mes}/{ano}")
	public ResponseEntity<DataChart> getDespesas(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano) {

		Republica republica = this.republicaService.getRepublica(id);

		LocalDate inicio = LocalDate.of(ano, mes, 1);
		LocalDate termino = inicio.plusMonths(1);

		Map<Integer, Float> despesas = graficoService.getTotalDespesas(republica, Date.valueOf(inicio),
				Date.valueOf(termino));
		Map<Integer, Float> receitas = graficoService.getTotalReceitas(republica, Date.valueOf(inicio),
				Date.valueOf(termino));

		return ResponseEntity.ok().body(new DataChart(id, despesas, receitas));
	}

}
