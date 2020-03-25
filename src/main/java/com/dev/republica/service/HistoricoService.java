package com.dev.republica.service;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;

@Validated
public interface HistoricoService {
	
	Historico getHistorico(@Min(value = 1L, message = "ID Historico inválido.") Long id);
	
	Historico save(Historico historico);
	
	List<Historico> getMoradorHistoricoByMorador(Morador morador);
	
}
