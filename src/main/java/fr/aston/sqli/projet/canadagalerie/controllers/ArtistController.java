package fr.aston.sqli.projet.canadagalerie.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.services.ArtistService;

@RestController
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(value = "/api/artists", method = RequestMethod.GET)
	public ResponseEntity<?> findAllArtists() {
		try {
			return ResponseEntity.ok().body(artistService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/artists/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getArtistById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(artistService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/artists", method = RequestMethod.POST)
	public ResponseEntity<?> addOrUpdateArtist(@RequestBody Artist artist) {	
		try {
			artistService.saveOrUpdate(artist);
			return ResponseEntity.ok().body(artist.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/artists/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteArtist(@PathVariable Long id) throws Exception{
		try {
			artistService.deleteArtistById(id);
			return ResponseEntity.ok().body("L'artiste " + id + " a été supprimée");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
