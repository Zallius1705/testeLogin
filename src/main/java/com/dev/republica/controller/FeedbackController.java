package com.dev.republica.controller;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.model.Feedback;
import com.dev.republica.model.Republica;
import com.dev.republica.service.FeedbackService;
import com.dev.republica.service.RepublicaService;

@RestController
@RequestMapping("/feedback")
@CrossOrigin//(origins = "http://localhost:4200")
public class FeedbackController {

	private FeedbackService feedbackService;
	private RepublicaService republicaService;

	public FeedbackController(FeedbackService feedbackService, RepublicaService republicaService) {
		this.feedbackService = feedbackService;
		this.republicaService = republicaService;
	}

	@GetMapping("/{id}")
	public Feedback getFeedback(@PathVariable Long id) {
		return feedbackService.getFeedbackById(id);
	}

	@GetMapping("/republica/{id}")
	public List<Feedback> getFeedbackByRepublica(@PathVariable Long id) {
		Republica republica = republicaService.getRepublica(id);
		return feedbackService.getFeedbackByRepublica(republica);
	}

	@GetMapping("/republica/{idRepublica}/morador/{idMorador}")
	public List<Feedback> getFeedbackByMoradorAndRepublica(@PathVariable Long idRepublica,
			@PathVariable Long idMorador) {
		return feedbackService.getFeedbackByRepublicaAndMorador(idRepublica, idMorador);
	}

	@PostMapping
	public Feedback create(@RequestBody Feedback feedback) {
		feedback.setDataFeedback(new Date());
		feedback.setRepublica(feedback.getmorador().getRepublica());
		feedback.setIdade(0);
		feedback.setStatus("ABERTO");
		return feedbackService.save(feedback);
	}

	@DeleteMapping("/{id}")
	public Feedback delete(@PathVariable Long id) {
		Feedback feedback = feedbackService.getFeedbackById(id);
		feedback.setStatus("EXCLUIDO");
		return feedbackService.save(feedback);
	}

	@GetMapping("/{id}/resolver")
	public Feedback resolver(@PathVariable Long id) {
		Feedback feedback = feedbackService.getFeedbackById(id);
		feedback.setStatus("RESOLVIDO (PENDENTE)");
		int dias = Days.daysBetween(new DateTime(feedback.getDataFeedback()), new DateTime()).getDays();
		feedback.setIdade(dias);
		return feedbackService.save(feedback);
	}

}
