package com.recettes.recettesapp.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recettes.recettesapp.entity.Ingredient;
import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.repositories.IngredientDAO;
import com.recettes.recettesapp.repositories.RecetteDAO;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecetteDAO recetteDAO; 
	
	private final IngredientDAO ingredientDAO; 
	
	@Autowired
	public IngredientServiceImpl(RecetteDAO recetteDAO, IngredientDAO ingredientDAO) {
		this.recetteDAO = recetteDAO;
		this.ingredientDAO = ingredientDAO;
	}

	@Override
	public Ingredient findIngredientById(Long ingredientId) {
		
		Optional<Ingredient> ingredientOpt = ingredientDAO.findById(ingredientId);	
				
		return ingredientOpt.get();		
	}

	@Override
	public Ingredient findIngredientByRecetteAndIngredientId(Long recetteId, Long ingredientId) {
		
		// recherche de la recette 
		Optional<Recette> recetteOpt = recetteDAO.findById(recetteId);
		
		if(!recetteOpt.isPresent()) {
			// TODO : logger l'erreur 
		}
		
		Recette recette = recetteOpt.get();
		
		Optional<Ingredient> ingredientRecherche = recette.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
		
		if(!ingredientRecherche.isPresent()) {
			// TODO: logger l'erreur 
		}
		
		return ingredientRecherche.get();
	}

	@Override
	@Transactional
	public Ingredient saveOrUpdate(Ingredient ingredient, String recetteId) {

		Ingredient ingredientFinal;
		
		// création d'ingrédient
		if(ingredient.getId() == null) {
		
			Optional<Recette> recetteCourante = recetteDAO.findById(Long.valueOf(recetteId));
			ingredient.setRecette(recetteCourante.get()); 
			ingredientFinal = ingredientDAO.save(ingredient);
			
		} else {
			
			// update 
			Optional<Ingredient> ingredientOpt = ingredientDAO.findById(ingredient.getId());
			
			Ingredient ingredientBDD = ingredientOpt.get();
			
			// on set les nouvelles données
			ingredientBDD.setDescription(ingredient.getDescription());
			ingredientBDD.setQuantite(ingredient.getQuantite());
			ingredientBDD.setUniteDeMesure(ingredient.getUniteDeMesure());
			ingredientFinal = ingredientDAO.save(ingredientBDD);
		}

		return ingredientFinal;
	}

	@Override
	public void deleteById(Long ingredientId) {
		
		ingredientDAO.deleteById(ingredientId);
		
	}
	
	

}
