package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;
import fr.aston.sqli.projet.canadagalerie.services.GalleryService;

@RestController
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	
	@RequestMapping(value = "/api/gallery", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Gallery> getAllWorks()  {
	
		return galleryService.findAllWorks();
		//return galleryRepository.findAll();
	}
	
//	@RequestMapping(value = "/api/exploiters/{titre}", method = RequestMethod.GET)
//	public Optional<Gallery> findGalleryByTitre( String titre) {
//		return Optional.of(galleryService.findWorkByTitre(titre).get(0));
//	}
//	
	

}
