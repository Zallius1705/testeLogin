package com.dev.republica.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.ReceitaDespesa;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.ReceitaDespesaRepository;

@Service
@Transactional
public class GraficoServiceImpl implements GraficoService {

	@Autowired
	private ReceitaDespesaRepository receitaDespesaRepository;

	@Override
	public Map<Integer, Float> getChartData(Republica republica, Date inicio, Date termino) {
		return null;
	}

	@Override
	public Map<Integer, Float> getTotalReceitas(Republica republica, Date inicio, Date termino) {
		Map<Integer, Float> map = new HashMap<>();
		List<ReceitaDespesa> receitas = getReceitas(republica, inicio, termino);

		receitas.forEach(receita -> {
			LocalDate dataLancamento = receita.getDataLancamento().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();

			float valor = map.getOrDefault(dataLancamento.getDayOfMonth(), 0f);

			map.put(dataLancamento.getDayOfMonth(), valor + receita.getValor());
		});

		return map;
	}
	
	@Override
	public Map<Integer, Float> getTotalDespesas(Republica republica, Date inicio, Date termino) {
		
		Map<Integer, Float> map = new HashMap<>();
		List<ReceitaDespesa> despesas = getDespesas(republica, inicio, termino);

		despesas.forEach(despesa -> {
			LocalDate dataLancamento = despesa.getDataLancamento().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();

			float valor = map.getOrDefault(dataLancamento.getDayOfMonth(), 0f);

			map.put(dataLancamento.getDayOfMonth(), valor - despesa.getValor());
		});

		return map;
	}

	public List<ReceitaDespesa> getReceitas(Republica republica, Date inicio, Date termino) {

		return receitaDespesaRepository.findByRepublicaAndTipoAndDataLancamentoBetween(republica, "Receita", inicio,
				termino);

	}

	public List<ReceitaDespesa> getDespesas(Republica republica, Date inicio, Date termino) {

		return receitaDespesaRepository.findByRepublicaAndTipoAndDataLancamentoBetween(republica, "Despesa", inicio,
				termino);
	}
}
