package com.marcelocustodio.GoldenRaspberryAwards.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producer")
public class Producer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private int year_victory;
	
	private String producer_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear_victory() {
		return year_victory;
	}

	public void setYear_victory(int year_victory) {
		this.year_victory = year_victory;
	}

	public String getProducer_name() {
		return producer_name;
	}

	public void setProducer_name(String producer_name) {
		this.producer_name = producer_name;
	}

}
