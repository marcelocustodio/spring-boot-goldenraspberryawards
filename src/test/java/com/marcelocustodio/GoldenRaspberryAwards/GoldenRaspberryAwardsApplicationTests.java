package com.marcelocustodio.GoldenRaspberryAwards;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelocustodio.GoldenRaspberryAwards.model.ProdutorIntervalo;
import com.marcelocustodio.GoldenRaspberryAwards.model.ResponseEntity;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GoldenRaspberryAwardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void obterProdutores() throws Exception {
		
		ProdutorIntervalo produtorIntervaloMin = new ProdutorIntervalo();
		produtorIntervaloMin.setProducer("Joel Silver");
		produtorIntervaloMin.setInterval(1);
		produtorIntervaloMin.setPreviousWin(1990);
		produtorIntervaloMin.setFollowingWin(1991);
		
		ProdutorIntervalo produtorIntervaloMax = new ProdutorIntervalo();
		produtorIntervaloMax.setProducer("Matthew Vaughn");
		produtorIntervaloMax.setInterval(13);
		produtorIntervaloMax.setPreviousWin(2002);
		produtorIntervaloMax.setFollowingWin(2015);
		
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.addMin(produtorIntervaloMin);
		responseEntity.addMax(produtorIntervaloMax);
		
		mockMvc.perform(get("/api/obterIntervaloDePremios")
						.content(objectMapper.writeValueAsString(responseEntity)))
			            .andExpect(status().isOk());
		;
	}

}
