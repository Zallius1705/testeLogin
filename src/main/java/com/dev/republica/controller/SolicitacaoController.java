package com.dev.republica.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.model.Solicitacao;
import com.dev.republica.service.MoradorService;
import com.dev.republica.service.RepublicaService;
import com.dev.republica.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
@CrossOrigin//(origins = "http://localhost:4200")
public class SolicitacaoController {

	@Autowired
	private RepublicaService republicaService;
	@Autowired
	private MoradorService moradorService;
	@Autowired
	private SolicitacaoService solicitacaoService;

	@PostMapping
	public Solicitacao solicitar(@RequestBody Long[] ids) {

		Republica republica = this.republicaService.getRepublica(ids[0]);
		Morador morador = this.moradorService.getMorador(ids[1]);

		Solicitacao solicitacao = null;

		if (republica != null && morador != null) {
			solicitacao = new Solicitacao(0L, morador, republica);
			this.solicitacaoService.save(solicitacao);
		}
		return solicitacao;
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return solicitacaoService.deleteById(id);
	}
	
	@GetMapping(path = { "/{id}" })
	public List<Solicitacao> getSolicitacoes(@PathVariable Long id) {
		List<Solicitacao> solicitacoes = this.solicitacaoService.getSolicitacoes();
		List<Solicitacao> solicitacoesRepublica = new ArrayList<>();

		for (Solicitacao s : solicitacoes) {
			if (s.getRepublica().getRepresentante().getId().equals(id)) {
					solicitacoesRepublica.add(s);
			}
		}
		return solicitacoesRepublica;
	}

}
