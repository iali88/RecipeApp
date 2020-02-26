package com.recettes.recettesapp.entity;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class Categorie {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recette> recette;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Recette> getRecette() {
		return recette;
	}

	public void setRecette(Set<Recette> recette) {
		this.recette = recette;
	}
    
    

}
