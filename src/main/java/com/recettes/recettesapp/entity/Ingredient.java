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
	private Recette recette;
	
	public Ingredient() {
	}

	public Ingredient(String description, BigDecimal quantite, UniteDeMesure uniteDeMesure) {
		this.description = description;
		this.quantite = quantite;
		this.uniteDeMesure = uniteDeMesure;
	}

	public Ingredient(String description, BigDecimal quantite, UniteDeMesure uniteDeMesure, Recette recette) {
		this.description = description;
		this.quantite = quantite;
		this.uniteDeMesure = uniteDeMesure;
		this.recette = recette;
	}

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

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	

}
