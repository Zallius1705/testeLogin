package com.dev.republica.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.repository.EnderecoRepository;
import com.dev.republica.service.HistoricoService;
import com.dev.republica.service.MoradorService;
import com.dev.republica.service.RepublicaService;

@RestController
@RequestMapping("/republicas")
@CrossOrigin//(origins = "http://localhost:4200")
public class RepublicaController {

	private RepublicaService republicaService;
	private MoradorService moradorService;
	private HistoricoService historicoService;
	private EnderecoRepository enderecoRepository;

	public RepublicaController(RepublicaService republicaService, MoradorService moradorService,
			HistoricoService historicoService, EnderecoRepository enderecoRepository) {
		this.republicaService = republicaService;
		this.moradorService = moradorService;
		this.historicoService = historicoService;
		this.enderecoRepository = enderecoRepository;
	}

	@GetMapping("/{id}")
	public Republica getRepublicaById(@PathVariable Long id) {
		return republicaService.getRepublica(id);
	}

	@PostMapping
	public Republica create(@RequestBody Republica republica) {
		republica.setDataFundacao(new Date());
		republica.setNumeroVagasDisponiveis((byte) (republica.getNumeroVagas() - 1));
		Morador morador = moradorService.getMorador(republica.getRepresentante().getId()); // simular morador logado
		republica.setRepresentante(morador); // nao esta setando o novo representante como representante
		republica.addMorador(morador);
		republicaService.save(republica);
		morador.setRepublica(republica);
		moradorService.save(morador);
		historicoService.save(new Historico(morador, republica, new Date()));
		return republica;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Republica> update(@PathVariable Long id, @RequestBody Republica republica) {
		enderecoRepository.save(republica.getEndereco());
		return republicaService.update(id, republica);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		/*
		 * Republica republica = republicaService.getRepublica(id); for (ReceitaDespesa
		 * receitaDespesa : republica.getReceitaDespesas()) { if
		 * (receitaDespesa.getTipo() == "Despesa") if (!receitaDespesa.isEfetivado())
		 * return ResponseEntity.status(HttpStatus.FORBIDDEN).
		 * body("República possui lançamentos em débito!"); } for (Morador morador :
		 * republica.getMoradores()) { morador.setRepublica(null);
		 * morador.setRepresentante(false); moradorService.save(morador); }
		 */
		return republicaService.deleteById(id);
	}

	@GetMapping("/disponiveis")
	public List<Republica> getRepublicasDisponiveis() {
		return republicaService.getRepublicasDisponiveis();
	}

	@GetMapping("/{nome}/busca")
	public List<Republica> getRepublicasByNome(@PathVariable String nome) {
		return republicaService.getRepublicasByNome(nome);
	}
	
	/*
	 * @GetMapping("/{id}/adicionarmorador/{idMorador}") public boolean
	 * addMorador(@PathVariable Long id, @PathVariable Long idMorador) { Republica
	 * republica = republicaService.getRepublica(id); Morador morador =
	 * moradorService.getMorador(idMorador); if (republica != null && morador !=
	 * null) { morador.setRepublica(republica); moradorService.save(morador);
	 * historicoService.save(morador.getHistorico().get(morador.getHistorico().size(
	 * ) - 1)); if (morador.getHistorico().size() > 1)
	 * historicoService.save(morador.getHistorico().get(morador.getHistorico().size(
	 * ) - 2)); return true; } return false; }
	 */

	@PostMapping("/{idRepublica}/adicionarmorador")
	public boolean addMorador(@PathVariable Long idRepublica, @RequestBody Morador m) {

		Morador morador = moradorService.getMorador(m.getId());
		Republica republica = republicaService.getRepublica(idRepublica);

		// Verifica se a republica existe
		if (republica != null) {
			// Verifica se o morador já está na repúblicae
			if (!morador.getRepublica().equals(republica)) {
				// Verifica se existem vagas
				if (republica.getNumeroVagasDisponiveis() > 0) {
					morador.setRepublica(republica);
					republica.addMorador(morador);
					republicaService.save(republica);
					moradorService.save(morador);
					historicoService.save(new Historico(morador, republica, new Date()));
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@GetMapping("/{idRepublica}/removermorador/{idMorador}")
	public boolean deleteMorador(@PathVariable Long idRepublica, @PathVariable Long idMorador) {
		Morador morador = moradorService.getMorador(idMorador);
		if (morador.getRepublica().getId() == idRepublica && morador != null) {
			Republica republica = republicaService.getRepublica(idRepublica);
			// verificar se é representante ou se só possui ele na republica
			if (!(republica.getNumeroVagas() - republica.getNumeroVagasDisponiveis() == 1)) {
				if (republica.getRepresentante().equals(morador)) {
					int index = 0;
					while (republica.getMoradores().get(index) == morador)
						index++;
					republica.setRepresentante(republica.getMoradores().get(index));
					republica.getRepresentante().setRepresentante(true);
				}
				morador.setRepublica(null);
				Historico historico = historicoService.getMoradorHistoricoByMorador(morador)
						.get(historicoService.getMoradorHistoricoByMorador(morador).size() - 1);
				historico.setDataSaida(new Date());
				historicoService.save(historico);
				republicaService.save(republica);
				moradorService.save(morador);
				moradorService.save(republica.getRepresentante());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@GetMapping("/{idRepublica}/alterarrepresentante/{idNovoRepresentante}")
	public boolean alterarRepresentante(@PathVariable Long idRepublica, @PathVariable Long idNovoRepresentante) {
		Morador novoRepresentante = moradorService.getMorador(idNovoRepresentante);
		Republica republica = republicaService.getRepublica(idRepublica);
		if (novoRepresentante.getRepublica().equals(republica)) {
			Morador exRepresentante = republica.getRepresentante();
			exRepresentante.setRepresentante(false);
			republica.setRepresentante(novoRepresentante);
			novoRepresentante.setRepresentante(true);
			moradorService.save(novoRepresentante);
			moradorService.save(exRepresentante);
			republicaService.save(republica);
			return true;
		}
		return false;
	}

}
