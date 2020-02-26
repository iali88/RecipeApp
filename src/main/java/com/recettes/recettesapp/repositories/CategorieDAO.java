package com.recettes.recettesapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.recettes.recettesapp.entity.Categorie;

public interface CategorieDAO extends CrudRepository<Categorie, Long> {

	Optional<Categorie> findByDescription(String description);
}
