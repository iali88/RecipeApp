package com.recettes.recettesapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	void saveImage(Long recetteId, MultipartFile fichier);

}
