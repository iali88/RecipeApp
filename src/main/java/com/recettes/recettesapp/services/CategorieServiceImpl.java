package com.recettes.recettesapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recettes.recettesapp.entity.Categorie;
import com.recettes.recettesapp.repositories.CategorieDAO;

@Service
public class CategorieServiceImpl implements CategorieService{

	private CategorieDAO categorieDAO;

	@Autowired
	public CategorieServiceImpl(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}
	
	@Override
	public List<Categorie> getAllCategories() {
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		liste = (ArrayList<Categorie>) categorieDAO.findAll();
		return liste;
	}
	
}
