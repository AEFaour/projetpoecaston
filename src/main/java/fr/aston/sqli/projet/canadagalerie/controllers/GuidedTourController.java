package fr.aston.sqli.projet.canadagalerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<?> findAllGuidedTours() {
		try {
			return ResponseEntity.ok().body(guidedTourService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/guidedtours/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGuidedTourById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(guidedTourService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

	@RequestMapping(value = "/api/guidedtours", method = RequestMethod.POST)
	public ResponseEntity<?> addOrUpdateGuidedTour(@RequestBody GuidedTour guidedTour) {
		try {
			guidedTourService.saveOrUpdate(guidedTour);
			return ResponseEntity.ok().body(guidedTour.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/guidedtours/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGuidedTour(@PathVariable Long id) throws Exception {
		try {
			guidedTourService.deleteGuidedTourById(id);
			return ResponseEntity.ok().body("La visite guidée a été supprimée");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
