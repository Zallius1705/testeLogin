package com.dev.republica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.republica.model.Feedback;
import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	private MoradorService moradorService;
	private RepublicaService republicaService;

	@Override
	public Feedback save(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	@Override
	public Feedback getFeedbackById(Long id) {
		return feedbackRepository.findById(id).orElse(null);
	}

	@Override
	public List<Feedback> getFeedbackByRepublica(Republica republica) {
		return feedbackRepository.findByRepublica(republica);
	}

	@Override
	public List<Feedback> getFeedbackByRepublicaAndMorador(Long idRepublica, Long idMorador) {
		Republica republica = republicaService.getRepublica(idRepublica);
		Morador morador = moradorService.getMorador(idMorador);

		return feedbackRepository.findByRepublicaAndMorador(republica, morador);
	}

}
