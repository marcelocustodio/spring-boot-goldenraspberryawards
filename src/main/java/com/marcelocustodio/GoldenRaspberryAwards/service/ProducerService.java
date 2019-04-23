package com.marcelocustodio.GoldenRaspberryAwards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;
import com.marcelocustodio.GoldenRaspberryAwards.persistence.repo.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository producerRepository;
	
	public List<Producer> findAll() {
        return producerRepository.findAll();
    }
	
	public Producer save(Producer producer) {
		return producerRepository.save(producer);
	}
	
	public void deleteAll() {
		producerRepository.deleteAll();
	}
}
