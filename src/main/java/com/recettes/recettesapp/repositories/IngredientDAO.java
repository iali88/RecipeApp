package com.recettes.recettesapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recettes.recettesapp.entity.Ingredient;

public interface IngredientDAO extends CrudRepository<Ingredient, Long>{

}
