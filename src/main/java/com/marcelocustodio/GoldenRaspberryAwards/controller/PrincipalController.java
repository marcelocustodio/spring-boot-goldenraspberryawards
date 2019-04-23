package com.marcelocustodio.GoldenRaspberryAwards.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;
import com.marcelocustodio.GoldenRaspberryAwards.model.ProdutorIntervalo;
import com.marcelocustodio.GoldenRaspberryAwards.model.ResponseEntity;
import com.marcelocustodio.GoldenRaspberryAwards.service.ProducerService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@RestController
@RequestMapping("/api")
public class PrincipalController {

	@Autowired
	private ProducerService producerService;

	public void salvarProdutor(String producer_name, int year) {
		Producer producer = new Producer();
		producer.setProducer_name(producer_name);
		producer.setYear_victory(year);
		producerService.save(producer);
	}

	@RequestMapping(value = "/obterIntervaloDePremios", method = RequestMethod.GET)
	public ResponseEntity obterIntervaloDePremios() {

		Reader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get("movielist.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

		List<String[]> entradas = null;
		try {
			entradas = csvReader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String[] entrada : entradas) {
			String dado = "";
			for (String p : entrada) {
				dado += p + "_";
			}
			String delimitador = ";";
			String[] elementosDaEntrada = dado.split(delimitador);

			int ano = Integer.parseInt(elementosDaEntrada[0]);

			String dadosProdutores = elementosDaEntrada[3];

			if (elementosDaEntrada[4] != null && elementosDaEntrada[4].equals("yes_")) {

				if (dadosProdutores.contains("_ ")) {
					String[] produtores = dadosProdutores.split("_ ");
					dado = "";
					for (String p : produtores) {
						if (p.contains(" and ")) {
							String[] pComposto = p.split(" and ");
							salvarProdutor(pComposto[0], ano);
							salvarProdutor(pComposto[1], ano);
						} else {
							salvarProdutor(p, ano);
						}
					}
				} else {
					if (dadosProdutores.contains(" and ")) {
						String[] pComposto = dadosProdutores.split(" and ");
						salvarProdutor(pComposto[0], ano);
						salvarProdutor(pComposto[1], ano);
					} else {
						salvarProdutor(dadosProdutores, ano);
					}
				}

			}

		}

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

		lista = producerService.findAll();

		nomeProdutor = null;
		anoAnterior = 0;

		int menorDiferenca = 100;
		String nomeProdutorComMenorDiferenca = null;
		int anoAnteriorProdutorComMenorDiferenca = 0;
		int anoPosteriorProdutorComMenorDiferenca = 0;

		tamanhoListaDeProdutores = lista.size();
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
		
		producerService.deleteAll();
		
		ProdutorIntervalo produtorIntervaloMin = new ProdutorIntervalo();
		produtorIntervaloMin.setProducer(nomeProdutorComMenorDiferenca);
		produtorIntervaloMin.setInterval(menorDiferenca);
		produtorIntervaloMin.setPreviousWin(anoAnteriorProdutorComMenorDiferenca);
		produtorIntervaloMin.setFollowingWin(anoPosteriorProdutorComMenorDiferenca);
		
		ProdutorIntervalo produtorIntervaloMax = new ProdutorIntervalo();
		produtorIntervaloMax.setProducer(nomeProdutorComMaiorDiferenca);
		produtorIntervaloMax.setInterval(maiorDiferenca);
		produtorIntervaloMax.setPreviousWin(anoAnteriorProdutorComMaiorDiferenca);
		produtorIntervaloMax.setFollowingWin(anoPosteriorProdutorComMaiorDiferenca);
		
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.addMin(produtorIntervaloMin);
		responseEntity.addMax(produtorIntervaloMax);

		return responseEntity;

	}
}
