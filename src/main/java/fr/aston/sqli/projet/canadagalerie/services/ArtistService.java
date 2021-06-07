package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IArtistRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;

@Service
public class ArtistService {

	@Autowired
	private IArtistRepository artistRepository;
	
	public List<Artist> findAll() {
		return (List<Artist>) artistRepository.findAll();
	}

	public Artist findById(Long id) throws Exception{
		
		Optional<Artist> resu =  this.artistRepository.findById(id);
		
		if(resu.isPresent()) {
			Artist ar = resu.get();
			return ar;
		}

		throw new Exception("L'Artiste " + id + " est introuvable");
	}
	
	
	public Artist saveOrUpdate(Artist artist) {

		artistRepository.save(artist);
		
		return artist;

	}

	public void deleteArtistById(Long id) throws Exception {
		try {
			artistRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("L'Artiste " + id + " est introuvable");
		}
		
	}
}
