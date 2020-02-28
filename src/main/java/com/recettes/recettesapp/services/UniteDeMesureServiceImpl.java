package com.recettes.recettesapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recettes.recettesapp.entity.UniteDeMesure;
import com.recettes.recettesapp.repositories.UniteDeMesureDAO;

@Service
public class UniteDeMesureServiceImpl implements UniteDeMesureService {

	private UniteDeMesureDAO uniteDeMesureDAO; 
		
	@Autowired
	public UniteDeMesureServiceImpl(UniteDeMesureDAO uniteDeMesureDAO) {
		this.uniteDeMesureDAO = uniteDeMesureDAO;
	}

	@Override
	public List<UniteDeMesure> listeUnitesDeMesures() {
		
		List<UniteDeMesure> liste = new ArrayList<UniteDeMesure>();
		
		uniteDeMesureDAO.findAll().forEach(liste::add);
		
		return liste;
	}

}
