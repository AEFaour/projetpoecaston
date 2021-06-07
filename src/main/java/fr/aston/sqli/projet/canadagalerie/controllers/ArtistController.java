package fr.aston.sqli.projet.canadagalerie.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.services.ArtistService;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping
	public ResponseEntity<?> findAllArtists() {
		LOGGER.info("GET /api/artists");
		try {
			return ResponseEntity.ok().body(artistService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getArtistById(@PathVariable("id") Long id) {
		LOGGER.info("GET /api/artists/{}", id);
		try {
			return ResponseEntity.ok().body(artistService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addOrUpdateArtist(@RequestBody Artist artist) {
		LOGGER.info("POST /api/artists");
		try {
			artistService.saveOrUpdate(artist);
			return ResponseEntity.ok().body(artist.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteArtist(@PathVariable Long id) throws Exception{
		LOGGER.info("DELETE /api/artists/{}", id);
		try {
			artistService.deleteArtistById(id);
			return ResponseEntity.ok().body("L'artiste " + id + " a été supprimée");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
