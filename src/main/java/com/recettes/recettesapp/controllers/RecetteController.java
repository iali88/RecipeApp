package com.recettes.recettesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recettes.recettesapp.services.RecetteService;

@Controller
@RequestMapping("/recette")
public class RecetteController {

	private final RecetteService recetteService;
	
	@Autowired
	public RecetteController(RecetteService recetteService) {
		this.recetteService = recetteService;
	}
	
	@GetMapping("/{id}/detail")
	public String voirRecetteById(@PathVariable String id, Model model) {
	
		model.addAttribute("recette", recetteService.findById(Long.valueOf(id)));
		
		return "recette/detail";
	}
	
	
}
