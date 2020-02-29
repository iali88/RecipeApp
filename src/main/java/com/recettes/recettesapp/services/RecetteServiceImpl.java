package com.recettes.recettesapp.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recettes.recettesapp.entity.Ingredient;
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
	public Recette findById(Long id) {
		
		Optional<Recette> optRecette = recetteDAO.findById(id); 
		
		if (!optRecette.isPresent()) {
            throw new RuntimeException("Recette non trouvée !");
        }
		
		return optRecette.get();
	}

	@Override
	@Transactional
	public Recette saveOrUpdate(Recette recette) {
		
		Recette recetteFinal;
		
		if(recette.getId() == 0L) {
		
			// creation
			recette.setId(null);
			recetteFinal = recetteDAO.save(recette);
			
		} else {
			
			// update 
			Optional<Recette> recetteOpt = recetteDAO.findById(recette.getId());
			
			Recette recetteBDD = recetteOpt.get();
			
			// on set les nouvelles données
			recetteBDD.setDescription(recette.getDescription());
			recetteBDD.setTpsPreparation(recette.getTpsPreparation());
			recetteBDD.setTpsCuisson(recette.getTpsCuisson());
			recetteBDD.setNbPersonnes(recette.getNbPersonnes());
			recetteBDD.setInstructions(recette.getInstructions());
			recetteBDD.setIngredients(recette.getIngredients());
			recetteBDD.setNiveau(recette.getNiveau());
			recetteBDD.getCommentaire().setCommentaireRecette(recette.getCommentaire().getCommentaireRecette());
			recetteBDD.setListeCategories(recette.getListeCategories());
			
			recetteFinal = recetteDAO.save(recetteBDD);
		}

		return recetteFinal;
		
	}

	@Override
	public void supprimerById(Long recetteId) {
		recetteDAO.deleteById(recetteId);
	}

}
