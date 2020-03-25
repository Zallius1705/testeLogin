package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Morador;
import com.dev.republica.model.ReceitaDespesa;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.ReceitaDespesaRepository;

@Service
@Transactional
public class ReceitaDespesaServiceImpl implements ReceitaDespesaService {

	@Autowired
	private ReceitaDespesaRepository receitaDespesaRepository;

	public ReceitaDespesaServiceImpl(ReceitaDespesaRepository receitaDespesaRepository) {
		this.receitaDespesaRepository = receitaDespesaRepository;
	}

	public ReceitaDespesa getReceitaDespesa(Long id) {
		return receitaDespesaRepository.findById(id).orElse(null);
	}

	public ReceitaDespesa save(ReceitaDespesa receitaDespesa) {
		return receitaDespesaRepository.save(receitaDespesa);
	}

	public ResponseEntity<ReceitaDespesa> update(Long id, ReceitaDespesa receitaDespesa) {
		return receitaDespesaRepository.findById(id).map(record -> {
			record.setRepublica(receitaDespesa.getRepublica());
			record.setTipo(receitaDespesa.getTipo());
			record.setDescricao(receitaDespesa.getDescricao());
			record.setValor(receitaDespesa.getValor());
			record.setNumeroParcelas(receitaDespesa.getNumeroParcelas());
			record.setDataLancamento(receitaDespesa.getDataLancamento());
			record.setDataVencimentoRecebimento(receitaDespesa.getDataVencimentoRecebimento());
			ReceitaDespesa updated = save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<?> deleteById(Long id) {
		return receitaDespesaRepository.findById(id).map(record -> {
			receitaDespesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	public List<ReceitaDespesa> getReceitaDespesaByRepublica(Republica republica) {
		return receitaDespesaRepository.findByRepublica(republica);
	}

	public List<ReceitaDespesa> getReceitaDespesaByRepublicaAndMorador(Republica republica, Morador morador) {
		return receitaDespesaRepository.findByRepublicaAndMorador(republica, morador);
	}

}
