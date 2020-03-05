package com.recettes.recettesapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		
//		Gestion spécifique au code erreur HTTP -------------------------------------
		
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//	    if (status != null) {
//	       
//	    	Integer statusCode = Integer.valueOf(status.toString());
//	     
//	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
//	            return "recette/error-404";
//	        }
//	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//	            return "recette/error-500";
//	        }
//	    }
//		----------------------------------------------------------------------------
		
		return "erreurs/custom404";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
