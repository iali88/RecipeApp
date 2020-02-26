package com.recettes.recettesapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Commentaire {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recette recette;

    @Lob
    private String CommentaireRecette;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public String getCommentaireRecette() {
		return CommentaireRecette;
	}

	public void setCommentaireRecette(String commentaireRecette) {
		CommentaireRecette = commentaireRecette;
	}
    
    
}
