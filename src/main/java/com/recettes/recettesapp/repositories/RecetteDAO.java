package com.recettes.recettesapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recettes.recettesapp.entity.Recette;

public interface RecetteDAO extends CrudRepository<Recette, Long>{

	
}
