package com.dev.republica.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.republica.model.Morador;
import com.dev.republica.model.ReceitaDespesa;
import com.dev.republica.model.Republica;

public interface ReceitaDespesaRepository extends JpaRepository<ReceitaDespesa, Long> {

	List<ReceitaDespesa> findByRepublica(Republica republica);

	@Query("select rd from ReceitaDespesa rd join fetch rd.moradorReceitaDespesas mrd where rd.republica = ?1 and mrd.pk.morador = ?2")
	List<ReceitaDespesa> findByRepublicaAndMorador(Republica republica, Morador morador);
	
	@Query("select rd from ReceitaDespesa rd where rd.republica = ?1 and rd.tipo = ?2 and rd.dataLancamento between ?3 and ?4") 
	List<ReceitaDespesa> findByRepublicaAndTipoAndDataLancamentoBetween(Republica republica, String tipo, Date inicio, Date termino);

}
