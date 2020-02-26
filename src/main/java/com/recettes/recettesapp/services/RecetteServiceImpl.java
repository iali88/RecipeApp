package com.recettes.recettesapp.services;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.repositories.RecetteDAO;

@Service
public class RecetteServiceImpl implements RecetteService {

	private final RecetteDAO recetteDAO;
	 
	private static Logger LOG;
	 	 
	@Autowired
	public RecetteServiceImpl(RecetteDAO recetteDAO) {
		this.recetteDAO = recetteDAO;
	}

	@Override
	public Set<Recette> getRecettes() {
		
//		LOG.debug("Dans RecetteService - getRecettes()");
		Set<Recette> recetteSet = new HashSet<>();
        
		recetteDAO.findAll().iterator().forEachRemaining(recetteSet::add);
        
		return recetteSet;
	}

	@Override
	public Recette findById(Long l) {
		// TODO 
		return null;
	}

}
