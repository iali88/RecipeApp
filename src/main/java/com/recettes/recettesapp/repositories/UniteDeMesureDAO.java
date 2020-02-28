package com.recettes.recettesapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.recettes.recettesapp.entity.UniteDeMesure;

public interface UniteDeMesureDAO extends CrudRepository<UniteDeMesure, Long>{

	Optional<UniteDeMesure> findByDescription(String description);
	
	
}
