package com.recettes.recettesapp.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recettes.recettesapp.services.RecetteService;

@Controller
public class IndexController {
	
	private static Logger LOG;
	
	private final RecetteService recetteService;
	
	@Autowired
	public IndexController(RecetteService recetteService) {
		this.recetteService = recetteService;
	}

	@RequestMapping({"", "/", "/index"})
    public String getIndex(Model model) {
		
		//LOG.debug("Dans IndexController_getIndex()");

        model.addAttribute("recettes", recetteService.getRecettes());

        return "index";
    }

}
