package com.marcelocustodio.GoldenRaspberryAwards.business;

import java.util.List;

import org.springframework.ui.Model;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;
import com.marcelocustodio.GoldenRaspberryAwards.service.ProducerService;

public class ProdutorComMenorIntervalo {

	public static void obterProdutorComMenorIntervalo(ProducerService producerService, Model model) {
		List<Producer> lista = producerService.findAll();

		String nomeProdutor = null;
		int anoAnterior = 0;
		int anoPosterior = 0;

		int menorDiferenca = 100;
		String nomeProdutorComMenorDiferenca = null;
		int anoAnteriorProdutorComMenorDiferenca = 0;
		int anoPosteriorProdutorComMenorDiferenca = 0;

		int tamanhoListaDeProdutores = lista.size();
		for (int x = 0; x < (tamanhoListaDeProdutores - 1); ++x) {
			anoAnterior = lista.get(x).getYear_victory();
			anoPosterior = 0;
			nomeProdutor = lista.get(x).getProducer_name();
			for (int y = x + 1; y < tamanhoListaDeProdutores; ++y) {
				if (lista.get(y).getProducer_name().equals(lista.get(x).getProducer_name())
						&& lista.get(y).getYear_victory() != anoAnterior) {
					anoPosterior = lista.get(y).getYear_victory();
				}
			}
			if (anoPosterior > 0) {
				int diferenca = (anoPosterior - anoAnterior);
				if (diferenca < menorDiferenca) {
					menorDiferenca = diferenca;
					nomeProdutorComMenorDiferenca = nomeProdutor;
					anoAnteriorProdutorComMenorDiferenca = anoAnterior;
					anoPosteriorProdutorComMenorDiferenca = anoPosterior;
				}
			}
		}
		
		model.addAttribute("nomeProdutorComMenorDiferenca", nomeProdutorComMenorDiferenca);
		model.addAttribute("menorDiferenca", menorDiferenca);
		model.addAttribute("anoAnteriorProdutorComMenorDiferenca", anoAnteriorProdutorComMenorDiferenca);
		model.addAttribute("anoPosteriorProdutorComMenorDiferenca", anoPosteriorProdutorComMenorDiferenca);

	}
	
}
