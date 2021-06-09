package fr.aston.sqli.projet.canadagalerie.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUIDE')")
	public ResponseEntity<?> findAllArtists() {
		ArtistController.LOGGER.info("GET /api/artists");
		try {
			return ResponseEntity.ok().body(artistService.findAll());
		} catch (Exception e) {
			ArtistController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUIDE')")
	public ResponseEntity<?> getArtistById(@PathVariable("id") Long id) {
		ArtistController.LOGGER.info("GET /api/artists/{}", id);
		try {
			return ResponseEntity.ok().body(artistService.findById(id));
		} catch (Exception e) {
			ArtistController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('exploiter:write')")
	public ResponseEntity<?> addOrUpdateArtist(@RequestBody Artist artist) {
		ArtistController.LOGGER.info("POST /api/artists");
		try {
			artistService.saveOrUpdate(artist);
			return ResponseEntity.ok().body(artist.getId());
		} catch (Exception e) {
			ArtistController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('exploiter:write')")
	public ResponseEntity<?> deleteArtist(@PathVariable Long id) throws Exception{
		ArtistController.LOGGER.info("DELETE /api/artists/{}", id);
		try {
			artistService.deleteArtistById(id);
			return ResponseEntity.ok().body("L'artiste " + id + " a été supprimée");
		} catch (Exception e) {
			ArtistController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
