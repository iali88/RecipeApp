package com.recettes.recettesapp.services;

import java.util.Set;

import com.recettes.recettesapp.entity.Recette;

public interface RecetteService {

	Set<Recette> getRecettes();
	
	Recette findById(Long l);
	
}
