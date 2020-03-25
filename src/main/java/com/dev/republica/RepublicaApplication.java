package com.dev.republica;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.dev.republica.controller.TarefaController;
import com.dev.republica.dto.TarefaDto;
import com.dev.republica.model.Endereco;
import com.dev.republica.model.Feedback;
import com.dev.republica.model.Historico;
import com.dev.republica.model.Morador;
import com.dev.republica.model.Republica;
import com.dev.republica.model.Tarefa;
import com.dev.republica.repository.FeedbackRepository;
import com.dev.republica.repository.HistoricoRepository;
import com.dev.republica.repository.MoradorReceitaDespesaRepository;
import com.dev.republica.repository.MoradorRepository;
import com.dev.republica.repository.ReceitaDespesaRepository;
import com.dev.republica.repository.RepublicaRepository;
import com.dev.republica.repository.TarefaRepository;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class RepublicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepublicaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RepublicaRepository republicaRepository, MoradorRepository moradorRepository,
			ReceitaDespesaRepository receitaDespesaRepository, FeedbackRepository feedbackRepository,
			MoradorReceitaDespesaRepository moradorReceitaDespesaRepository, HistoricoRepository historicoRepository,
			TarefaRepository tarefaRepository, TarefaController tarefaController) {
		return args -> {

			Morador m1 = moradorRepository.save(new Morador(1L, "Abel Soares", "", "(28)981120043", "@abelsoares",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m2 = moradorRepository.save(new Morador(2L, "Amaral de Souza", "", "(28)999881043", "@amaralsouza",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m3 = moradorRepository.save(new Morador(3L, "Amanda Oliveira", "", "(28)997973553",
					"@oliveiramanda", "(28)981123443", "(28)981123433", "Feminino"));
			Morador m4 = moradorRepository.save(new Morador(4L, "Bruno Nogarol", "", "(28)997793443", "@brunogarol",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m5 = moradorRepository.save(new Morador(5L, "Beatriz Silva", "", "(28)999973443", "@biasilva",
					"(28)981123443", "(28)981123433", "Feminino"));
			Morador m6 = moradorRepository.save(new Morador(6L, "Bianca Bonner", "", "(28)996693443", "@bonnerb",
					"(28)981123443", "(28)981123433", "Feminino"));
			Morador m7 = moradorRepository.save(new Morador(7L, "Caio Daltio", "", "(28)996993443", "@cdaltio",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m8 = moradorRepository.save(new Morador(8L, "César Machado", "", "(28)9939123443", "@machado",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m9 = moradorRepository.save(new Morador(9L, "Cláudio Bento", "", "(28)981124545", "@claudinhobento",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m10 = moradorRepository.save(new Morador(10L, "Eduarda Cunha", "", "(28)981124444", "@eduardacunha",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m11 = moradorRepository.save(new Morador(11L, "Edson Castro", "", "(28)981124973", "@edsoncastro",
					"(28)981123443", "(28)981123433", "Masculino"));
			Morador m12 = moradorRepository.save(new Morador(12L, "Edmar Louzada", "", "(28)9811256535", "@edmarlou",
					"(28)981123443", "(28)981123433", "Masculino"));

			Republica r1 = republicaRepository.save(new Republica(1L, "Descabelados", new Date(), null,
					new Endereco(1L, "Avenida Brasil", "29500-000", "Guararema", "Alegre", "ES", null, null),
					"Vizinhança silenciosa", (byte) (4), (byte) (1), "Casa", "Mista", null, m1, null));
			Republica r2 = republicaRepository.save(new Republica(2L, "Filhos do Stewart", new Date(), null,
					new Endereco(2L, "Rua Alcino", "29500-000", "Guararema", "Alegre", "ES", null, null),
					"Aréa de churrasco", (byte) (2), (byte) (0), "Casa", "Masculina", null, m2, null));
			Republica r3 = republicaRepository.save(new Republica(3L, "Tristes em Alegre", new Date(), null,
					new Endereco(3L, "Rua Bernadino Monteiro", "29500-000", "Guararema", "Alegre", "ES", null, null),
					"Internet rápida", (byte) (3), (byte) (0), "Casa", "Feminina", null, m3, null));
			Republica r4 = republicaRepository.save(new Republica(4L, "Cachorrada", new Date(), null,
					new Endereco(4L, "Avenida Dona Jesus", "29500-000", "Guararema", "Alegre", "ES", null, null),
					"PS4 e XBOX ONE", (byte) (3), (byte) (1), "Casa", "Mista", null, m4, null));
			Republica r5 = republicaRepository.save(new Republica(5L, "Avalanche", new Date(), null,
					new Endereco(5L, "Rua Francisco de Assis", "29500-000", "Guararema", "Alegre", "ES", null, null),
					"Rock todo fim de semana", (byte) (2), (byte) (0), "Casa", "Masculina", null, m5, null));

			/*m1.setRepublica(r1);
			m1.setRepresentante(true);
			moradorRepository.save(m1);
			historicoRepository.save(new Historico(m1, r1, new Date()));
			*/
			m2.setRepresentante(true);
			m2.setRepublica(r2);
			moradorRepository.save(m2);
			historicoRepository.save(new Historico(m2, r2, new Date()));
			
			m3.setRepresentante(true);
			m3.setRepublica(r3);
			moradorRepository.save(m3);
			historicoRepository.save(new Historico(m3, r3, new Date()));
			
			m4.setRepresentante(true);
			m4.setRepublica(r4);
			moradorRepository.save(m4);
			historicoRepository.save(new Historico(m4, r4, new Date()));
			
			m5.setRepresentante(true);
			m5.setRepublica(r5);
			moradorRepository.save(m5);
			historicoRepository.save(new Historico(m5, r5, new Date()));
			
			m6.setRepublica(r3);
			moradorRepository.save(m6);
			historicoRepository.save(new Historico(m6, r3, new Date()));
			
			m7.setRepublica(r1);
			m7.setRepublica(r1);
			moradorRepository.save(m7);
			historicoRepository.save(new Historico(m7, r1, new Date()));
			
			m8.setRepublica(r1);
			moradorRepository.save(m8);
			historicoRepository.save(new Historico(m8, r1, new Date()));
			
			m9.setRepublica(r2);
			moradorRepository.save(m9);
			historicoRepository.save(new Historico(m9, r2, new Date()));
			
			m10.setRepublica(r3);
			moradorRepository.save(m10);
			historicoRepository.save(new Historico(m10, r3, new Date()));
			
			m11.setRepublica(r4);
			moradorRepository.save(m11);
			historicoRepository.save(new Historico(m11, r4, new Date()));
			
			m12.setRepublica(r5);
			moradorRepository.save(m12);
			historicoRepository.save(new Historico(m12, r5, new Date()));
			
			
			Tarefa t1 = tarefaController
					.create(new TarefaDto(r1, new Date(), List.of(m1, m7, m8), "Varrer", new Date()));
			Tarefa t2 = tarefaController
					.create(new TarefaDto(r1, new Date(), List.of(m1, m7), "Passar pano", new Date()));
			Tarefa t3 = tarefaController
					.create(new TarefaDto(r1, new Date(), List.of(m7, m8), "Lavar varanda", new Date()));
			Tarefa t4 = tarefaController
					.create(new TarefaDto(r2, new Date(), List.of(m2, m9), "Varrer sala", new Date()));
			Tarefa t5 = tarefaController
					.create(new TarefaDto(r2, new Date(), List.of(m2), "Limpar churrasqueira", new Date()));
			Tarefa t6 = tarefaController
					.create(new TarefaDto(r2, new Date(), List.of(m9), "Lavar banheiro", new Date()));
			Tarefa t7 = tarefaController
					.create(new TarefaDto(r3, new Date(), List.of(m3, m6), "Limpar móveis", new Date()));
			Tarefa t8 = tarefaController
					.create(new TarefaDto(r3, new Date(), List.of(m3), "Aspirar sofá", new Date()));
			Tarefa t9 = tarefaController
					.create(new TarefaDto(r3, new Date(), List.of(m6), "EStender roupas no varal", new Date()));
			Tarefa t10 = tarefaController
					.create(new TarefaDto(r4, new Date(), List.of(m4, m11), "Trocar lampadas", new Date()));
			Tarefa t11 = tarefaController
					.create(new TarefaDto(r4, new Date(), List.of(m4, m11), "Guardar as louças", new Date()));
			Tarefa t12 = tarefaController
					.create(new TarefaDto(r4, new Date(), List.of(m11), "Trocar fechadura", new Date()));
			Tarefa t13 = tarefaController
					.create(new TarefaDto(r4, new Date(), List.of(m4), "Pintar a sala", new Date()));
			Tarefa t14 = tarefaController
					.create(new TarefaDto(r5, new Date(), List.of(m5, m12), "Arrumar area externa", new Date()));
			Tarefa t15 = tarefaController
					.create(new TarefaDto(r5, new Date(), List.of(m12), "Jogar lixo fora", new Date()));
			Tarefa t16 = tarefaController
					.create(new TarefaDto(r5, new Date(), List.of(m12), "Pagar contas", new Date()));

			Feedback f1 = feedbackRepository.save(new Feedback(1L, "Reclamação", new Date(), "Casa esta muito suja", m1, true, m1.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f2 = feedbackRepository.save(new Feedback(2L, "Reclamação", new Date(), "Estão comendo meus biscoitos", m1, false, m2.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f3 = feedbackRepository.save(new Feedback(3L, "Sugestão", new Date(), "Comprar lampadas novas", m1, true, m3.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f4 = feedbackRepository.save(new Feedback(4L, "Sugestão", new Date(), "Contratar uma funcionaria", m1, false, m4.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f5 = feedbackRepository.save(new Feedback(5L, "Sugestão", new Date(), "Lavar a varanda", m1, true, m5.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f6 = feedbackRepository.save(new Feedback(6L, "Reclamação", new Date(), "Não tem louça limpa", m1, false, m6.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f7 = feedbackRepository.save(new Feedback(7L, "Reclamação", new Date(), "Alguém esta deixando a porta aberta durante a noite", m1, true, m7.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f8 = feedbackRepository.save(new Feedback(8L, "Sugestão", new Date(), "Comprar uma TV nova", m1, false, m8.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f9 = feedbackRepository.save(new Feedback(9L, "Sugestão", new Date(), "Varrer a casa diariamente", m1, false, m9.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f10 = feedbackRepository.save(new Feedback(10L, "Reclamação", new Date(), "Roubaram meu pendrive", m1, true, m10.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f11 = feedbackRepository.save(new Feedback(11L, "Reclamação", new Date(), "As festas estão causando prejuizo", m1, true, m11.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f12 = feedbackRepository.save(new Feedback(12L, "Sugestão", new Date(), "Fazer mais festas para arrecadar mais dinheiro", m1, true, m12.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f13 = feedbackRepository.save(new Feedback(13L, "Sugestão", new Date(), "Vender móveis que não usamos mais", m1, true, m10.getRepublica(), new Date(), 0, "ABERTO"));
			Feedback f14 = feedbackRepository.save(new Feedback(14L, "Reclamação", new Date(), "Muito barulho durante a noite", m1, true, m1.getRepublica(), new Date(), 0, "ABERTO"));

		};
	}

}
