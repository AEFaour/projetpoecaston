package fr.aston.sqli.projet.canadagalerie.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.services.GalleryService;

@RestController
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	@RequestMapping(value = "/api/gallery", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<?> getAllWorks()  {
		try {
			return ResponseEntity.ok().body(galleryService.findAllWorks());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/gallery/works/", method = RequestMethod.GET)
	public ResponseEntity<?> findGalleryByTitre(@RequestParam("titre") String titre) {
		try {
			return ResponseEntity.ok().body(galleryService.findWorkByTitre(titre));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	

}
