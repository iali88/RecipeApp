package com.recettes.recettesapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recettes.recettesapp.entity.Ingredient;
import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.entity.UniteDeMesure;
import com.recettes.recettesapp.repositories.RecetteDAO;
import com.recettes.recettesapp.services.IngredientService;
import com.recettes.recettesapp.services.RecetteService;
import com.recettes.recettesapp.services.UniteDeMesureService;

@Controller
@RequestMapping("/recette")
public class IngredientController {
	
	private final RecetteService recetteService;
	
	private final IngredientService ingredientService; 
	
	private final UniteDeMesureService uniteDeMesureService; 
	
	@Autowired
	public IngredientController(RecetteService recetteService, IngredientService ingredientService, UniteDeMesureService uniteDeMesureService) {
		this.recetteService = recetteService;
		this.ingredientService = ingredientService;
		this.uniteDeMesureService = uniteDeMesureService;
	}

	@GetMapping("/{recetteId}/ingredients")
	public String afficherListeIngredients(@PathVariable String recetteId, Model model) {
		
		model.addAttribute("recette", recetteService.findById(Long.valueOf(recetteId)));
		
		return "recette/ingredient/liste"; 
	}
	
	@GetMapping("/{recetteId}/ingredient/{ingredientId}/modifier")
	public String afficherFormulaireModifierIngredient(@PathVariable String recetteId, @PathVariable String ingredientId, Model model) {
		
		model.addAttribute("ingredient", ingredientService.findIngredientById(Long.valueOf(ingredientId)));
		
		// ajout de la liste des unités pour la selection
		model.addAttribute("listeUnites", uniteDeMesureService.listeUnitesDeMesures());
		
		return "recette/ingredient/ingredientForm"; 
	}

	@PostMapping("/{recetteId}/ingredient")
	public String saveOrUpdateIngredient(@PathVariable String recetteId, @ModelAttribute Ingredient ingredient ) {

		Ingredient ingredientSaved = ingredientService.saveOrUpdate(ingredient, recetteId);
		
		return "redirect:/recette/"+recetteId+"/ingredients" ;
	}
	
	@GetMapping("/{recetteId}/ingredient/ajouter")
	public String afficherFormulaireAjoutIngredient(@PathVariable String recetteId, Model model) {
		
		// ingrédient sans id, l'id sera généré par Hibernate lors de la sauvegarde
		Ingredient ingredient = new Ingredient();
		ingredient.setRecette(recetteService.findById(Long.valueOf(recetteId)));
		ingredient.setUniteDeMesure(new UniteDeMesure());
		
		model.addAttribute("ingredient", ingredient);
		
		// ajout de la liste des unités pour la selection
		model.addAttribute("listeUnites", uniteDeMesureService.listeUnitesDeMesures());
		
		return "recette/ingredient/ingredientForm"; 
	}
	
	@GetMapping("/{recetteId}/ingredient/{ingredientId}/supprimer")
	public String supprimerIngredient(@PathVariable String recetteId, @PathVariable String ingredientId, Model model) {
		
		ingredientService.deleteById(Long.valueOf(ingredientId));
		
		return "redirect:/recette/"+recetteId+"/ingredients" ;
	}
}
