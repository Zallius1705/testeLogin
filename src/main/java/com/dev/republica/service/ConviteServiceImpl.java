package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Convite;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.ConviteRepository;

@Service
@Transactional
public class ConviteServiceImpl {
	
	@Autowired
	private ConviteRepository conviteRepository;

	public ConviteServiceImpl(ConviteRepository conviteRepository) {
		this.conviteRepository = conviteRepository;
	}

	public List<Convite> getAllConvites() {
		return conviteRepository.findAll();
	}

	public Convite getConvite(Long id) {
		return conviteRepository.findById(id).orElse(null);
	}

	public Convite save(Convite convite) {
		return conviteRepository.save(convite);
	}

	public List<Convite> getConviteByRepublica(Republica republica) {
		return conviteRepository.findByRepublica(republica);
	}
}
