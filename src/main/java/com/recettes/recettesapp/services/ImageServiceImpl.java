package com.recettes.recettesapp.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.repositories.RecetteDAO;

@Service
public class ImageServiceImpl implements ImageService {

	private final RecetteDAO recetteDAO;

	@Autowired
	public ImageServiceImpl(RecetteDAO recetteDAO) {
		this.recetteDAO = recetteDAO;
	}

	@Override
	@Transactional
	public void saveImage(Long recetteId, MultipartFile fichier) {

		try {
			
			Recette recette = recetteDAO.findById(recetteId).get();
			
			Byte[] byteObjects = new Byte[fichier.getBytes().length];

            int i = 0;

            for (byte b : fichier.getBytes()){
                byteObjects[i++] = b;
            }
            
            recette.setImage(byteObjects);
            
            recetteDAO.save(recette);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
