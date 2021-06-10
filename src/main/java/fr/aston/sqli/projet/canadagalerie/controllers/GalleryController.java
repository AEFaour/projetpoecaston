package fr.aston.sqli.projet.canadagalerie.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.services.GalleryService;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);
	
	@Autowired
	private GalleryService galleryService;
	
	
	//@RequestMapping(value = "/api/gallery", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@GetMapping
	@PreAuthorize("hasAuthority('gallery:read')")
	public ResponseEntity<?> getAllWorks()  {
		GalleryController.LOGGER.info("GET /api/gallery");
		try {
			return ResponseEntity.ok().body(galleryService.findAllWorks());
		} catch (Exception e) {
			GalleryController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/works/")
	@PreAuthorize("hasAuthority('gallery:read')")
	public ResponseEntity<?> findGalleryByTitre(@RequestParam("titre") String titre) {
		GalleryController.LOGGER.info("GET /api/gallery/works/{}", titre);
		try {
			return ResponseEntity.ok().body(galleryService.findWorkByTitre(titre));
		} catch (Exception e) {
			GalleryController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	

}
