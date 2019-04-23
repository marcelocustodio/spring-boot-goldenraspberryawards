package com.marcelocustodio.GoldenRaspberryAwards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marcelocustodio.GoldenRaspberryAwards.business.AcessoADados;
import com.marcelocustodio.GoldenRaspberryAwards.business.ProdutorComMaiorIntervalo;
import com.marcelocustodio.GoldenRaspberryAwards.business.ProdutorComMenorIntervalo;
import com.marcelocustodio.GoldenRaspberryAwards.service.ProducerService;

@Controller
public class RelatorioController {

	@Autowired
	private ProducerService producerService;
	
	@GetMapping("/relatorio")
	public String relatorio(Model model) {
		
		AcessoADados.lerDados(this.producerService, "movielist.csv");
		
		model.addAttribute("produtores", producerService.findAll());
		
		ProdutorComMaiorIntervalo.obterProdutorComMaiorIntervalo(producerService, model);
		
		ProdutorComMenorIntervalo.obterProdutorComMenorIntervalo(producerService, model);
		
		AcessoADados.excluirDados();
		
		return "relatorio";
	}
	
}
