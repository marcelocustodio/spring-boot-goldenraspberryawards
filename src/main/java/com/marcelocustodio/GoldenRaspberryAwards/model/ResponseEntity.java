package com.marcelocustodio.GoldenRaspberryAwards.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseEntity {
	
	private List<ProdutorIntervalo> min;
	
	private List<ProdutorIntervalo> max;
	
	public ResponseEntity() {
		this.min = new ArrayList<ProdutorIntervalo>();
		this.max = new ArrayList<ProdutorIntervalo>();
	}

	public List<ProdutorIntervalo> getMin() {
		return min;
	}

	public void setMin(List<ProdutorIntervalo> min) {
		this.min = min;
	}

	public List<ProdutorIntervalo> getMax() {
		return max;
	}

	public void setMax(List<ProdutorIntervalo> max) {
		this.max = max;
	}

	public void addMin(ProdutorIntervalo m) {
		this.min.add(m);
	}

	public void addMax(ProdutorIntervalo m) {
		this.max.add(m);
	}
	
}
