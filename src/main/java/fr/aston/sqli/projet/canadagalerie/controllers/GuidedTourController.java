package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.GuidedTourService;

@RestController
public class GuidedTourController {
	
	@Autowired
	private GuidedTourService guidedTourService; 
	
	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.GET)
	public List<GuidedTour> findAllGuidedTour(){
		return guidedTourService.findAll();
	}

}
