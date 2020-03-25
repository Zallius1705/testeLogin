package com.dev.republica.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;
import com.dev.republica.model.ReceitaDespesa;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.RepublicaRepository;

@Service
@Transactional
public class RepublicaServiceImpl implements RepublicaService {
	
	@Autowired
	private RepublicaRepository republicaRepository;
	
	@Autowired
	private MoradorService moradorService;
	
	@Autowired
	private HistoricoService historicoService;

	public RepublicaServiceImpl(RepublicaRepository republicaRepository, MoradorService moradorService,
			HistoricoService historicoService) {
		this.republicaRepository = republicaRepository;
		this.moradorService = moradorService;
		;
		this.historicoService = historicoService;
	}

	public Republica getRepublica(Long id) {
		return republicaRepository.findById(id).orElse(null);
	}

	public Republica save(Republica republica) {
		return republicaRepository.save(republica);
	}

	public ResponseEntity<Republica> update(Long id, Republica republica) {
		return republicaRepository.findById(id).map(record -> {
			record.setNome(republica.getNome());
			record.setVantagens(republica.getVantagens());
			if (record.getNumeroVagas() != republica.getNumeroVagas()) {
				if (republica.getNumeroVagas() >= record.getMoradores().size())
					record.setNumeroVagas(republica.getNumeroVagas());
			}
			record.setGenero(republica.getGenero());
			record.setLinkEstatuto(republica.getLinkEstatuto());
			Republica updated = save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<?> deleteById(Long id) {
		return republicaRepository.findById(id).map(republica -> {
			boolean flag = true;

			for (ReceitaDespesa receitaDespesa : republica.getReceitaDespesas()) {
				if (!receitaDespesa.isEfetivado())
					flag = false;
			}

			if (flag) {
				for (Morador morador : republica.getMoradores()) {
					morador.setRepublica(null);
					morador.setRepresentante(false);
					Historico historico = historicoService.getMoradorHistoricoByMorador(morador)
							.get(historicoService.getMoradorHistoricoByMorador(morador).size() - 1);
					historico.setDataSaida(new Date());
					historicoService.save(historico);
					moradorService.save(morador);
				}
				republicaRepository.deleteById(id);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}

	public List<Republica> getRepublicasDisponiveis() {
		return republicaRepository.findByNumeroVagasDisponiveisGreaterThanEqual((byte) 1);
	}

	public List<Republica> getRepublicasByNome(String nome) {
		return republicaRepository.findByNomeContaining(nome);
	}
	
}
