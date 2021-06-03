package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;
import fr.aston.sqli.projet.canadagalerie.services.GalleryService;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;


	@GetMapping
	public ResponseEntity<List<Gallery>> getAllWorks() {
			List<Gallery> works = this.galleryService.findAllWorks();
			return new ResponseEntity<>(works, HttpStatus.OK);
		}

	@GetMapping("/{titre}")
	public ResponseEntity<Gallery> findGalleryByTitre(@PathVariable("titre") String titre) {
		try {
			return new ResponseEntity<>(this.galleryService.findWorkByTitre(titre), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
