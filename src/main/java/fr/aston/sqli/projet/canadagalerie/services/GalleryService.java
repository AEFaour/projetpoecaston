package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;

@Service
public class GalleryService {
	
	@Autowired
	private IGalleryRepository galleryRepository;
	
	public List<Gallery> findAllWorks(){
		return galleryRepository.findAll();
	}
	
	public Gallery  findWorkByTitre(String titre) throws Exception {
		
//		List<Gallery> works = galleryRepository.findByTitre(titre);
//		return Optional.of(works.get(0));
		
		Optional<Gallery> resu = Optional.ofNullable(this.galleryRepository.findByTitre(titre).get(0));
		
		if(resu.isPresent()) {
			Gallery g = resu.get();
			return g;
		}
		throw new Exception("L'Oeuvre d'art : " + titre + " est introuvable");
	}
	

}
