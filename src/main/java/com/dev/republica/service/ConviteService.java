package com.dev.republica.service;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.Convite;
import com.dev.republica.model.Republica;

@Validated
public interface ConviteService {
	List<Convite> getAllConvites();

	Convite getConvite(@Min(value = 1L, message = "ID Convite inválido.") Long id);

	Convite save(Convite convite);

	List<Convite> getConvitesByRepublica(Republica republica);
}
