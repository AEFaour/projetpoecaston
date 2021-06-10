package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.services.ArtistService;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

	private static final Logger LOG = LogManager.getLogger();	
	
	@Autowired
	private ArtistService artistService;

	@GetMapping
	@PreAuthorize("hasAuthority('artist:read')")
	public ResponseEntity<List<Artist>> findAllArtists() {
		ArtistController.LOG.info("Listing all artists");
		List<Artist> artists = this.artistService.findAll();
		return new ResponseEntity<>(artists, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('artist:read')")
	public ResponseEntity<Artist> getArtistById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.artistService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			ArtistController.LOG.error("No artist present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('artist:write')")
	public ResponseEntity<Artist> addOrUpdateArtist(@RequestBody Artist artist) {
		ArtistController.LOG.info("Managing the artist [{}]", artist);
		Artist newArtist = this.artistService.saveOrUpdate(artist);
		return new ResponseEntity<>(newArtist, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('artist:write')")
	public ResponseEntity<?> deleteArtist(@PathVariable("id") Long id) {
		try {
			this.artistService.deleteArtistById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			ArtistController.LOG.error("No artist present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}