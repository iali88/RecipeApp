package com.recettes.recettesapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recette {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    
    private Integer tpsPreparation;
    
    private Integer tpsCuisson;
    
    private Integer nbPersonnes;

    @Lob
    private String instructions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recette")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Niveau niveau;

    @OneToOne(cascade = CascadeType.ALL)
    private Commentaire commentaire;

    @ManyToMany
    @JoinTable(name = "recette_categorie",
        	   joinColumns = @JoinColumn(name = "recette_id"),
               inverseJoinColumns = @JoinColumn(name = "categorie_id"))
    private Set<Categorie> listeCategories = new HashSet<>();
    
    public Recette ajouterIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
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

	public Integer getTpsPreparation() {
		return tpsPreparation;
	}

	public void setTpsPreparation(Integer tpsPreparation) {
		this.tpsPreparation = tpsPreparation;
	}

	public Integer getTpsCuisson() {
		return tpsCuisson;
	}

	public void setTpsCuisson(Integer tpsCuisson) {
		this.tpsCuisson = tpsCuisson;
	}

	public Integer getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(Integer nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Commentaire getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Commentaire commentaire) {
		if (commentaire != null) {
            this.commentaire = commentaire;
            commentaire.setRecette(this);
        }
	}

	public Set<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(Set<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
    
    

}
