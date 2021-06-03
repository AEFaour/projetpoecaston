package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.services.ArtistService;

@RestController
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@RequestMapping(value = "/api/artists", method = RequestMethod.GET)
	public List<Artist> findAllArtists() {
		return artistService.findAll();
	}
	
	@RequestMapping(value = "/api/artists/{id}", method = RequestMethod.GET)
	public Optional<Artist> getArtistById(@PathVariable Long id) {
		
		return artistService.findById(id);
	}
	
	@RequestMapping(value = "/api/artists", method = RequestMethod.POST)
	public Long addOrUpdateArtist(@RequestBody Artist artist) {

		artistService.saveOrUpdate(artist);
		return artist.getId();
	}
	
	@RequestMapping(value = "/api/artists/{id}", method = RequestMethod.DELETE)
	public String deleteArtist(@PathVariable Long id) {
		artistService.deleteArtistById(id);
		return "L'artiste a été supprimée";
	}

}
