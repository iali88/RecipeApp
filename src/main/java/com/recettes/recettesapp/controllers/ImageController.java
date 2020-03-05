package com.recettes.recettesapp.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recettes.recettesapp.entity.Recette;
import com.recettes.recettesapp.services.ImageService;
import com.recettes.recettesapp.services.RecetteService;

@Controller
@RequestMapping("/recette")
public class ImageController {

	private final RecetteService recetteService;
	
	private final ImageService imageService; 
	
	@Autowired
	public ImageController(RecetteService recetteService, ImageService imageService) {
		this.recetteService = recetteService;
		this.imageService = imageService;
	}



	@GetMapping("/{id}/image")
	public String afficherFormulaireImage(@PathVariable String id, Model model) {
	
		model.addAttribute("recette", recetteService.findById(Long.valueOf(id)));
	
		return "/recette/imageForm";
	}
	
	@PostMapping("/{id}/image")
	public String uploadImage(@PathVariable String id, @RequestParam("fichierImg") MultipartFile fichier) {
		
		imageService.saveImage(Long.valueOf(id), fichier);
		
		return "redirect:/recette/" + id + "/detail"; 
	}
	
	
	@GetMapping("/{id}/recetteImage")
	public void afficherImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        
		Recette recette = recetteService.findById(Long.valueOf(id));

        if (recette.getImage() != null) {
        	
            byte[] byteArray = new byte[recette.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recette.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
	
	
}
