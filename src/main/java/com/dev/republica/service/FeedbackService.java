package com.dev.republica.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.dev.republica.model.Feedback;
import com.dev.republica.model.Republica;

@Validated
public interface FeedbackService {

	public Feedback save(Feedback feedback);

	public Feedback getFeedbackById(Long id);

	public List<Feedback> getFeedbackByRepublica(Republica republica);

	public List<Feedback> getFeedbackByRepublicaAndMorador(Long idRepublica, Long idMorador);

}