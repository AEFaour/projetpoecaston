package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IArtistRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;

@Service
public class ArtistService {

	@Autowired
	private IArtistRepository artistRepository;
	
	public List<Artist> findAll() {
		return (List<Artist>) artistRepository.findAll();
	}

	public Optional<Artist> findById(Long id){
		
		return artistRepository.findById(id);
	}
	
	public void saveOrUpdate(Artist artist) {

		artistRepository.save(artist);

	}

	public void deleteArtistById(Long id) {
		
		artistRepository.deleteById(id);
	}
}
