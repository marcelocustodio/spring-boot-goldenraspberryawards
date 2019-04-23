package com.marcelocustodio.GoldenRaspberryAwards.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcelocustodio.GoldenRaspberryAwards.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
	    
}