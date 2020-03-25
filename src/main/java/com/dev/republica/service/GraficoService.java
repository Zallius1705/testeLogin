package com.dev.republica.service;

import java.util.Date;
import java.util.Map;

import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.Republica;

@Validated
public interface GraficoService {

	public Map<Integer, Float> getChartData(Republica republica, Date inicio, Date termino);
	
	public Map<Integer, Float> getTotalReceitas(Republica republica, Date inicio, Date termino);
	
	public Map<Integer, Float> getTotalDespesas(Republica republica, Date inicio, Date termino);

}