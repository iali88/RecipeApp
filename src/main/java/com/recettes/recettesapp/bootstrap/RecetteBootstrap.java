package com.recettes.recettesapp.bootstrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recettes.recettesapp.entity.Categorie;
import com.recettes.recettesapp.entity.Commentaire;
import com.recettes.recettesapp.entity.Ingredient;
import com.recettes.recettesapp.entity.Niveau;
import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.entity.UniteDeMesure;
import com.recettes.recettesapp.repositories.CategorieDAO;
import com.recettes.recettesapp.repositories.RecetteDAO;
import com.recettes.recettesapp.repositories.UniteDeMesureDAO;

@Component
public class RecetteBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CategorieDAO categorieDAO;
	
	private final RecetteDAO recetteDAO; 
	
	private final UniteDeMesureDAO uniteDeMesureDAO;
	
	@Autowired
	public RecetteBootstrap(CategorieDAO categorieDAO, RecetteDAO recetteDAO, UniteDeMesureDAO uniteDeMesureDAO) {
		this.categorieDAO = categorieDAO;
		this.recetteDAO = recetteDAO;
		this.uniteDeMesureDAO = uniteDeMesureDAO;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recetteDAO.saveAll(getRecettes());
	}

	private List<Recette> getRecettes() {
		
		List<Recette> recette = new ArrayList<Recette>();
		
        UniteDeMesure udm_entier = ((Optional<UniteDeMesure>)uniteDeMesureDAO.findByDescription("Entier")).get();
        UniteDeMesure udm_ml = ((Optional<UniteDeMesure>)uniteDeMesureDAO.findByDescription("ml")).get();
        UniteDeMesure udm_pincee = ((Optional<UniteDeMesure>)uniteDeMesureDAO.findByDescription("Pincée")).get();
        UniteDeMesure udm_gramme = ((Optional<UniteDeMesure>)uniteDeMesureDAO.findByDescription("g")).get();
        
        Categorie categorieEntree = ((Optional<Categorie>)categorieDAO.findByDescription("Entrée")).get();
        Categorie categoriePlat = ((Optional<Categorie>)categorieDAO.findByDescription("Plat")).get();

        
		Recette gclRecette = new Recette();
		gclRecette.setDescription("Recette Guacamole");
		gclRecette.setTpsPreparation(10);
		gclRecette.setTpsCuisson(10);
		gclRecette.setNiveau(Niveau.FACILE);
		gclRecette.setImage(getImageByte("src/main/resources/static/images/guacamole400x400.jpg"));
		
		String instruction_gcl = "1. Préparer l'avocat : Couper l'avocat en deux. Enlever le noyau. Découper la chair de l'avocat avec un couteau, puis récupérer à l'aide d'une cuillère.\r\n" + 
								 "2. Écraser à l'aide d'une fourchette : Avec une fourcette écraser l'avocat" + 
				        "\r\n" + "3. Assaisonner : Salé et ajouter quelques gouttes de citron préssé. Le citron permettra une meilleur préservation du guacamole." + 
				                " Ajouter oignons, coriandre, poivre, piment (ajuster selon goût)." + 
				        "\r\n" + "4. Filmer et mettre au frais : Filmer avec du film alimentaire pour empécher l'oxydation. Mettre au frais." + 
				        "\r\n" + "Vous pouvez également rajouter des morceaux de tomates juste avant de servir." +
				        "\r\n";
		
		gclRecette.setInstructions(instruction_gcl);
		
		Commentaire gcl_commentaire = new Commentaire();
		String str_commentaire = "N'hésitez pas à expérimenter ! Essayer du guacamole avec des morceaux d'ananas, de mangues ou de fraises.\r\n" +
				                 "La version de base contient uniquement du sel et de l'avocat.\r\n" +
				                 "Laissez le noyau dans le guacamole pour le conserver plus longtemps !\r\n" +
				                 "Retrouvez le Mexique et ses saveurs sur le site www.mexique-fr.com.\r\n";
				
		gcl_commentaire.setCommentaireRecette(str_commentaire);
		gclRecette.setCommentaire(gcl_commentaire);
		
		gclRecette.ajouterIngredient(new Ingredient("Avocats mûrs", new BigDecimal(2), udm_entier));
		gclRecette.ajouterIngredient(new Ingredient("Sel", new BigDecimal("0"), udm_pincee));
		gclRecette.ajouterIngredient(new Ingredient("Jus de citron", new BigDecimal(30), udm_ml));
		gclRecette.ajouterIngredient(new Ingredient("Oignons", new BigDecimal(1), udm_entier));
		gclRecette.ajouterIngredient(new Ingredient("Piments", new BigDecimal(1), udm_entier));
        gclRecette.ajouterIngredient(new Ingredient("Coriandre", new BigDecimal(15), udm_gramme));
        gclRecette.ajouterIngredient(new Ingredient("Poivre", new BigDecimal(0), udm_pincee));
        gclRecette.ajouterIngredient(new Ingredient("Tomate", new BigDecimal("1"), udm_entier));

        gclRecette.getListeCategories().add(categorieEntree);
        gclRecette.getListeCategories().add(categoriePlat);
        gclRecette.setNbPersonnes(2);

        recette.add(gclRecette);
                       
        //-- 2e recette 
        
        Recette tacos = new Recette();
        tacos.setDescription("Recette Tacos");
        tacos.setTpsPreparation(10);
        tacos.setTpsCuisson(10);
        tacos.setNiveau(Niveau.FACILE);
        tacos.setImage(getImageByte("src/main/resources/static/images/tacos400x400.jpg"));
		
		String instructions_tacos = 
				"1 Préparer le grill.\r\n " +
                "2 Faire la marinade pour le poulet : Dans un saladier, mélanger poudre de piment, origan, cumin, sucre, sel, ail et un zeste d'orange. Ajouter le jus d'orange et l'huile d'olive pour faire une pâte."
                	+ "Ajouter le poulet et mélanger.\n" +
                "Mettre de coté pendant que le grill chauffe et preparer les garnitures.\r\n" +
                "3 Cuisson du poulet : Cuire le poulet 3-4 minutes sur chaque coté. Puis déposer le poulet sur une assiette et laisser refroidir 5 minutes.\r\n" +
                "4 Tortillas : Toaster les tortillas quelques secondes sur le grill ou sur une poele chaude.\r\n" +
                "Envelopper les tortillas dans un tissu afin de les conserver bien chaude.\r\n" +
                "5 Assemblage : Découper le poulet en tranches. Sur chaque tortilla, placer la salade, les tranches de poulet, les tranches d'avocats, les radis, les tomates, et les tranches d'oignons. Rajouter une cuillère de crème fraiche.\r\n" ;
		
		tacos.setInstructions(instructions_tacos);
		
		Commentaire tacos_commentaire = new Commentaire();
		String str_commentaire2 = "On peut accompagner ces tacos de guacamole et/ou de maïs. \r\nPour une version plus légère, les haricots rouges peuvent être supprimés.\r\n" +
				                 "Bon appétit !\n";
		tacos_commentaire.setCommentaireRecette(str_commentaire2);
		tacos.setCommentaire(tacos_commentaire);
		
		tacos.ajouterIngredient(new Ingredient("Poudre de piment", new BigDecimal(0), udm_pincee));
		tacos.ajouterIngredient(new Ingredient("Sel", new BigDecimal("0"), udm_pincee));
		tacos.ajouterIngredient(new Ingredient("Origan", new BigDecimal(0), udm_pincee));
		tacos.ajouterIngredient(new Ingredient("Cumin", new BigDecimal(0), udm_pincee));
		tacos.ajouterIngredient(new Ingredient("Gousse d'ail", new BigDecimal(1), udm_entier));
		tacos.ajouterIngredient(new Ingredient("Cuisse de poulet", new BigDecimal(4), udm_entier));
		tacos.ajouterIngredient(new Ingredient("Tomate", new BigDecimal("2"), udm_entier));
		tacos.ajouterIngredient(new Ingredient("Huile d'olive", new BigDecimal("30"), udm_ml));
		tacos.ajouterIngredient(new Ingredient("Tortilla", new BigDecimal("4"), udm_entier));
		tacos.ajouterIngredient(new Ingredient("Oignons", new BigDecimal("1"), udm_entier));
		tacos.ajouterIngredient(new Ingredient("Citron", new BigDecimal("1"), udm_entier));

		tacos.getListeCategories().add(categoriePlat);
		tacos.setNbPersonnes(2);

        recette.add(tacos);
        
		
		return recette;
	}

	private Byte[] getImageByte(String src) {

		byte[] bFile = null;

		try {
		
			bFile = Files.readAllBytes(new File(src).toPath());
		
		} catch (IOException e) {
			e.printStackTrace();
		}

		return toByteObject(bFile);
	}

	private Byte[] toByteObject(byte[] bytes) throws RuntimeException {
		
		if(bytes == null) {
			throw new RuntimeException("bytes == null") ;
		}
		
		Byte[] byteObjects = new Byte[bytes.length];
		
		int i=0;    

		for(byte b: bytes) {
		   byteObjects[i++] = b; 
		}
		
		return byteObjects;
	}

}
