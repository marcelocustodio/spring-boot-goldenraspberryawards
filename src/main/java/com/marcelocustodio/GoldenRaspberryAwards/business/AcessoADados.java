package com.marcelocustodio.GoldenRaspberryAwards.business;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;
import com.marcelocustodio.GoldenRaspberryAwards.service.ProducerService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class AcessoADados {

	private static ProducerService producerService;
	private static String caminhoArquivoCSV;
	private static List<String[]> entradasDoArquivoCSV;
	
	private static void salvarProdutor(String producer_name, int year) {
		Producer producer = new Producer();
		producer.setProducer_name(producer_name);
		producer.setYear_victory(year);
		producerService.save(producer);
	}
	
	public static void lerDados(ProducerService p, String c) {
		
		producerService = p;
		
		caminhoArquivoCSV = c;
		
		lerArquivoCSV();
		
		popularBanco();
	}
	
	public static void excluirDados() {
		
		producerService.deleteAll();
	}
	
	private static void lerArquivoCSV() {
		
		Reader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get(caminhoArquivoCSV));
		} catch (IOException e) {
			e.printStackTrace();
		}
		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

		try {
			entradasDoArquivoCSV = csvReader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	private static void popularBanco() {

		for (String[] entrada : entradasDoArquivoCSV) {
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

	}
	
}
