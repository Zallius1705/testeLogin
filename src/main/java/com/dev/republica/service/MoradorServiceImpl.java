package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.MoradorRepository;

@Service
@Transactional
public class MoradorServiceImpl implements MoradorService {

	@Autowired
	private MoradorRepository moradorRepository;

	public MoradorServiceImpl(MoradorRepository moradorRepository) {
		this.moradorRepository = moradorRepository;
	}

	public List<Morador> getAllMoradores() {
		return moradorRepository.findAll();
	}

	public Morador getMorador(Long id) {
		return moradorRepository.findById(id).orElse(null);
	}

	public Morador save(Morador morador) {
		return moradorRepository.save(morador);
	}

	public ResponseEntity<Morador> update(Long id, Morador morador) {
		return moradorRepository.findById(id).map(record -> {
			record.setNome(morador.getNome());
			record.setApelido(morador.getApelido());
			record.setTelefone(morador.getTelefone());
			record.setLinkRedeSocial(morador.getLinkRedeSocial());
			record.setTelefoneResponsavel1(morador.getTelefoneResponsavel1());
			record.setTelefoneResponsavel2(morador.getTelefoneResponsavel2());
			record.setSexo(morador.getSexo());
			Morador updated = save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<?> deleteById(Long id) {
		return moradorRepository.findById(id).map(record -> {
			moradorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	public List<Morador> getMoradoresByRepublica(Republica republica) {
		return moradorRepository.findByRepublica(republica);
	}

	public List<Morador> getMoradoresByNome(String nome) {
		return moradorRepository.findByNomeContaining(nome);
	}
	
}
