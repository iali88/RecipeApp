package com.recettes.recettesapp.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private BigDecimal quantite;

	@OneToOne(fetch = FetchType.EAGER)
	private UniteDeMesure uniteDeMesure;

	@ManyToOne
	private Recette recipe;

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

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public UniteDeMesure getUniteDeMesure() {
		return uniteDeMesure;
	}

	public void setUniteDeMesure(UniteDeMesure uniteDeMesure) {
		this.uniteDeMesure = uniteDeMesure;
	}

	public Recette getRecipe() {
		return recipe;
	}

	public void setRecipe(Recette recipe) {
		this.recipe = recipe;
	}
	
	

}
