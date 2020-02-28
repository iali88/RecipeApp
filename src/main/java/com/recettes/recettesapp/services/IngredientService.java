package com.recettes.recettesapp.services;

import com.recettes.recettesapp.entity.Ingredient;

public interface IngredientService {

    Ingredient findIngredientByRecetteAndIngredientId(Long recipeId, Long ingredientId);

	Ingredient findIngredientById(Long ingredientId);

	Ingredient saveOrUpdate(Ingredient ingredient, String recetteId);	
	
	void deleteById(Long ingredientId);	

	
}
