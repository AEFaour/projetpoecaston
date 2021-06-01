package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;

@RestController
public class GalleryController {
	
	@Autowired
	private IGalleryRepository galleryRepository;
	
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	
	@RequestMapping(value = "/api/gallery", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Gallery> getAllWorks()  {
	
		return galleryRepository.findAll();
		
	}

}
