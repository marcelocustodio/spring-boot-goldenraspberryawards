package com.marcelocustodio.GoldenRaspberryAwards.business;

import java.util.List;

import org.springframework.ui.Model;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;
import com.marcelocustodio.GoldenRaspberryAwards.service.ProducerService;

public class ProdutorComMaiorIntervalo {

	public static void obterProdutorComMaiorIntervalo(ProducerService producerService, Model model) {
		List<Producer> lista = producerService.findAll();
		String nomeProdutor = null;
		int anoAnterior = 0;
		int anoPosterior = 0;

		int maiorDiferenca = 0;
		String nomeProdutorComMaiorDiferenca = null;
		int anoAnteriorProdutorComMaiorDiferenca = 0;
		int anoPosteriorProdutorComMaiorDiferenca = 0;

		int tamanhoListaDeProdutores = lista.size();
		for (int x = 0; x < (tamanhoListaDeProdutores - 1); ++x) {
			anoAnterior = lista.get(x).getYear_victory();
			nomeProdutor = lista.get(x).getProducer_name();
			for (int y = x + 1; y < tamanhoListaDeProdutores; ++y) {
				if (lista.get(y).getProducer_name().equals(lista.get(x).getProducer_name())
						&& lista.get(y).getYear_victory() != anoAnterior) {
					anoPosterior = lista.get(y).getYear_victory();
				}
			}
			int diferenca = (anoPosterior - anoAnterior);
			if (diferenca > maiorDiferenca) {
				maiorDiferenca = diferenca;
				nomeProdutorComMaiorDiferenca = nomeProdutor;
				anoAnteriorProdutorComMaiorDiferenca = anoAnterior;
				anoPosteriorProdutorComMaiorDiferenca = anoPosterior;
			}
		}
		
		model.addAttribute("nomeProdutorComMaiorDiferenca", nomeProdutorComMaiorDiferenca);
		model.addAttribute("maiorDiferenca", maiorDiferenca);
		model.addAttribute("anoAnteriorProdutorComMaiorDiferenca", anoAnteriorProdutorComMaiorDiferenca);
		model.addAttribute("anoPosteriorProdutorComMaiorDiferenca", anoPosteriorProdutorComMaiorDiferenca);

	}
	
}
