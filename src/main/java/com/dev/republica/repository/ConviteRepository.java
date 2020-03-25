package com.dev.republica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Convite;
import com.dev.republica.model.Republica;

public interface ConviteRepository extends JpaRepository<Convite, Long> {

	List<Convite> findByRepublica(Republica republica);

}