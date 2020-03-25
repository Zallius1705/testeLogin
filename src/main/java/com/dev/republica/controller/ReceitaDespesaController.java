package com.dev.republica.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.dto.ReceitaDespesaDto;
import com.dev.republica.model.Morador;
import com.dev.republica.model.MoradorReceitaDespesa;
import com.dev.republica.model.MoradorReceitaDespesaId;
import com.dev.republica.model.ReceitaDespesa;
import com.dev.republica.model.Republica;
import com.dev.republica.service.MoradorReceitaDespesaService;
import com.dev.republica.service.MoradorService;
import com.dev.republica.service.ReceitaDespesaService;
import com.dev.republica.service.RepublicaService;

@RestController
@RequestMapping("/republica/receitasdespesas")
@CrossOrigin//(origins = "http://localhost:4200")
public class ReceitaDespesaController {

	private ReceitaDespesaService receitaDespesaService;
	private RepublicaService republicaService;
	private MoradorReceitaDespesaService moradorReceitaDespesaService;
	private MoradorService moradorService;

	public ReceitaDespesaController(ReceitaDespesaService receitaDespesaService, RepublicaService republicaService,
			MoradorReceitaDespesaService moradorReceitaDespesaService, MoradorService moradorService) {
		this.receitaDespesaService = receitaDespesaService;
		this.republicaService = republicaService;
		this.moradorReceitaDespesaService = moradorReceitaDespesaService;
		this.moradorService = moradorService;
	}

	@GetMapping("/{id}")
	public ReceitaDespesa getReceitaDespesaById(@PathVariable Long id) {
		return receitaDespesaService.getReceitaDespesa(id);
	}

	@PostMapping
	public ReceitaDespesa create(@RequestBody ReceitaDespesaDto form) {
		ReceitaDespesa receitaDespesa = new ReceitaDespesa();
		receitaDespesa.setRepublica(form.getRepublica());
		receitaDespesa.setTipo(form.getTipo());
		receitaDespesa.setDescricao(form.getDescricao());
		receitaDespesa.setValor(form.getValor());
		receitaDespesa.setNumeroParcelas(form.getNumeroParcelas());
		receitaDespesa.setDataLancamento(new Date());
		receitaDespesa.setDataVencimentoRecebimento(form.getDataVencimentoRecebimento());
		receitaDespesa.setEfetivado(false);
		receitaDespesa.setAprovado(false);
		receitaDespesaService.save(receitaDespesa);

		List<MoradorReceitaDespesa> moradorReceitaDespesas = new ArrayList<>();

		float valorDividido = (float) form.getValor() / form.getMoradores().size();

		for (Morador morador : form.getMoradores()) {
			moradorReceitaDespesas.add(moradorReceitaDespesaService
					.save(new MoradorReceitaDespesa(morador, receitaDespesa, valorDividido, 0)));
		}
		receitaDespesa.setMoradorReceitaDespesas(moradorReceitaDespesas);
		return receitaDespesaService.save(receitaDespesa);
	}

	@PostMapping("/estornar")
	public boolean estornar(@RequestBody ReceitaDespesa receitaDespesa) {

		receitaDespesa = receitaDespesaService.getReceitaDespesa(receitaDespesa.getId());

		for (MoradorReceitaDespesa moradorReceitaDespesa : receitaDespesa.getMoradorReceitaDespesas()) {
			if (moradorReceitaDespesa.getValorPago() != 0)
				return false;
		}

		ReceitaDespesa rdEstorno = new ReceitaDespesa();
		rdEstorno.setRepublica(receitaDespesa.getRepublica());
		rdEstorno.setDescricao("Estorno ".concat(receitaDespesa.getDescricao()));
		rdEstorno.setValor(receitaDespesa.getValor());
		rdEstorno.setNumeroParcelas(receitaDespesa.getNumeroParcelas());
		rdEstorno.setDataLancamento(new Date());
		rdEstorno.setDataVencimentoRecebimento(receitaDespesa.getDataVencimentoRecebimento());
		rdEstorno.setEfetivado(receitaDespesa.isEfetivado());

		if (receitaDespesa.getTipo().equals("Despesa"))
			receitaDespesa.setTipo("Receita");
		else
			receitaDespesa.setTipo("Despesa");

		receitaDespesaService.save(receitaDespesa);

		List<MoradorReceitaDespesa> moradorReceitaDespesas = new ArrayList<>();

		for (MoradorReceitaDespesa moradorReceitaDespesa : receitaDespesa.getMoradorReceitaDespesas()) {
			moradorReceitaDespesas.add(moradorReceitaDespesaService
					.save(new MoradorReceitaDespesa(moradorReceitaDespesa.getPk().getMorador(), receitaDespesa,
							moradorReceitaDespesa.getValor(), moradorReceitaDespesa.getValor())));
			moradorReceitaDespesa.setValorPago(moradorReceitaDespesa.getValor());
		}

		receitaDespesa.setMoradorReceitaDespesas(moradorReceitaDespesas);

		receitaDespesaService.save(receitaDespesa);

		return true;
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReceitaDespesa> update(@PathVariable Long id, @RequestBody ReceitaDespesa receitaDespesa) {
		return receitaDespesaService.update(id, receitaDespesa);
	}

	@GetMapping("republica/{id}")
	public List<ReceitaDespesa> getReceitaDespesaByRepublica(@PathVariable Long id) {
		Republica republica = republicaService.getRepublica(id);
		return receitaDespesaService.getReceitaDespesaByRepublica(republica);
	}

	@GetMapping("republica/{idRepublica}/morador/{idMorador}")
	public List<ReceitaDespesa> getReceitaDespesaByMorador(@PathVariable Long idRepublica,
			@PathVariable Long idMorador) {
		Republica republica = republicaService.getRepublica(idRepublica);
		Morador morador = moradorService.getMorador(idMorador);
		return receitaDespesaService.getReceitaDespesaByRepublicaAndMorador(republica, morador);
	}

	@GetMapping("republica/{idRepublica}/morador/{idMorador}/pagar/{idReceitaDespesa}")
	public void pagarReceitaDespesa(@PathVariable Long idRepublica, @PathVariable Long idMorador,
			@PathVariable Long idReceitaDespesa) {
		Morador morador = moradorService.getMorador(idMorador);
		ReceitaDespesa receitaDespesa = receitaDespesaService.getReceitaDespesa(idReceitaDespesa);
		MoradorReceitaDespesaId mreceitaDespesaId = new MoradorReceitaDespesaId();
		mreceitaDespesaId.setMorador(morador);
		mreceitaDespesaId.setReceitaDespesa(receitaDespesa);
		MoradorReceitaDespesa mreceitaDespesa = moradorReceitaDespesaService
				.getMoradorReceitaDespesa(mreceitaDespesaId);
		mreceitaDespesa.setValorPago(mreceitaDespesa.getValor());
		moradorReceitaDespesaService.save(mreceitaDespesa);
	}

}
