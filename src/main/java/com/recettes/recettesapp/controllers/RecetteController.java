package com.recettes.recettesapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.exceptions.NotFoundException;
import com.recettes.recettesapp.services.CategorieService;
import com.recettes.recettesapp.services.RecetteService;

@Controller
@RequestMapping("/recette")
public class RecetteController {

	private final RecetteService recetteService;
	
	private final CategorieService categorieService;
	
	@Autowired
	public RecetteController(RecetteService recetteService, CategorieService categorieService) {
		this.recetteService = recetteService;
		this.categorieService = categorieService;
	}
	
	@GetMapping("/{id}/detail")
	public String voirRecetteById(@PathVariable String id, Model model) {
	
		model.addAttribute("recette", recetteService.findById(Long.valueOf(id)));
		
		return "recette/detail";
	}
	
    @GetMapping("/{id}/modifier")
	public String afficherFormModifierRecette(@PathVariable String id, Model model) {
    	
    	model.addAttribute("recette", recetteService.findById(Long.valueOf(id)));
    	model.addAttribute("categories", categorieService.getAllCategories());
    	
    	return "recette/recetteForm";
    }
    
	@PostMapping
	public ModelAndView saveOrUpdateRecette(@Valid @ModelAttribute Recette recette, BindingResult bindingResult ) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("redirect:/");
		
		if(bindingResult.hasErrors()) {
			
			modelView.addObject("categories", categorieService.getAllCategories());
			modelView.setViewName("recette/recetteForm");
			return modelView;
		}
		
		Recette recetteSaved = recetteService.saveOrUpdate(recette);
		
		return modelView;
	}
	
	@GetMapping("/{id}/supprimer")
	public String supprimerById(@PathVariable String id) {
    	
    	recetteService.supprimerById(Long.valueOf(id));
    	
    	return "redirect:/";
    }
	
	@GetMapping("/ajouter")
	public String newRecipe(HttpSession session, Model model) {
		
		Recette recette = new Recette();
		// marqueur nouvelle recette id==0
		recette.setId(0L);
				
		model.addAttribute("recette", recette);
		model.addAttribute("categories", categorieService.getAllCategories());

		return "recette/recetteForm";
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView methodeNotFound(Exception e) {
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("erreurs/erreur404");
		modelView.addObject("exception", e);
		
		return modelView;
	}
	
}
