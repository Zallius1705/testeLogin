package com.dev.republica.dto;

import java.util.Map;

public class DataChart {

	private long idRepublica;
	private Map<Integer, Float> despesas;
	private Map<Integer, Float> receitas;
	
	public DataChart(long idRepublica, Map<Integer, Float> despesas, Map<Integer, Float> receitas) {
		this.idRepublica = idRepublica;
		this.despesas = despesas;
		this.receitas = receitas;
	}

	public long getIdRepublica() {
		return idRepublica;
	}

	public Map<Integer, Float> getDespesas() {
		return despesas;
	}

	public Map<Integer, Float> getReceitas() {
		return receitas;
	}
	
}
