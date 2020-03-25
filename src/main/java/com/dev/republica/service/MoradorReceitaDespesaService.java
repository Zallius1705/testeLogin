package com.dev.republica.service;

import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.MoradorReceitaDespesa;
import com.dev.republica.model.MoradorReceitaDespesaId;

@Validated
public interface MoradorReceitaDespesaService {

	MoradorReceitaDespesa save(MoradorReceitaDespesa moradorReceitaDespesa);

	MoradorReceitaDespesa getMoradorReceitaDespesa(MoradorReceitaDespesaId id);

}
