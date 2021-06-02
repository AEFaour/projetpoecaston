package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.GuidedTourService;

@RestController
public class GuidedTourController {
	
	@Autowired
	private GuidedTourService guidedTourService; 
	
	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.GET)
	public List<GuidedTour> findAllGuidedTour(){
		return guidedTourService.findAll();
	}
	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.POST)
	public Long addOrUpdateExploiter(@RequestBody GuidedTour guidedTour) {
		guidedTourService.saveOrUpdate(guidedTour);
		return guidedTour.getId();
	}
	//to be contin

}
