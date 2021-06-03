package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.GuidedTourService;

@RestController
public class GuidedTourController {

	@Autowired
	private GuidedTourService guidedTourService;

	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.GET)
	public List<GuidedTour> findAllGuidedTours() {
		return guidedTourService.findAll();
	}
	
	@RequestMapping(value = "/api/guidedtours/{id}", method = RequestMethod.GET)
	public Optional<GuidedTour> getGuidedTourById(@PathVariable Long id) {
		
		return guidedTourService.findById(id);
	}

	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.POST)
	public Long addOrUpdateGuidedTour(@RequestBody GuidedTour guidedTour) {

		guidedTourService.saveOrUpdate(guidedTour);
		return guidedTour.getId();
	}
	
	@RequestMapping(value = "/api/guidedtours/{id}", method = RequestMethod.DELETE)
	public String deleteGuidedTour(@PathVariable Long id) {
		guidedTourService.deleteGuidedTourById(id);
		return "La visite guidée a été supprimée";
	}

}
