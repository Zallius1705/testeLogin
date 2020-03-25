package com.dev.republica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.republica.model.Feedback;
import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	List<Feedback> findByRepublica(Republica republica);

	List<Feedback> findByRepublicaAndMorador(Republica republica, Morador morador);

}