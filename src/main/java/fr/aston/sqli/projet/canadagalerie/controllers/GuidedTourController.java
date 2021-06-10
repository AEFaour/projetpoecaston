package fr.aston.sqli.projet.canadagalerie.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.GuidedTourService;

@RestController
@RequestMapping("/api/guidedtours")
public class GuidedTourController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuidedTourController.class);
	
	@Autowired
	private GuidedTourService guidedTourService;

	@GetMapping
	@PreAuthorize("hasAuthority('guidedtour:read')")
	public ResponseEntity<?> findAllGuidedTours() {
		GuidedTourController.LOGGER.info("GET /api/guidedtours");
		try {
			return ResponseEntity.ok().body(guidedTourService.findAll());
		} catch (Exception e) {
			GuidedTourController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('guidedtour:read')")
	public ResponseEntity<?> getGuidedTourById(@PathVariable("id") Long id) {
		GuidedTourController.LOGGER.info("GET /api/guidedtours/{}", id);
		try {
			return ResponseEntity.ok().body(guidedTourService.findById(id));
		} catch (Exception e) {
			GuidedTourController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('guidedtour:write')")
	public ResponseEntity<?> addOrUpdateGuidedTour(@RequestBody GuidedTour guidedTour) {
		GuidedTourController.LOGGER.info("POST /api/guidedtours");
		try {
			guidedTourService.saveOrUpdate(guidedTour);
			return ResponseEntity.ok().body(guidedTour.getId());
		} catch (Exception e) {
			GuidedTourController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('guidedtour:write')")
	public ResponseEntity<?> deleteGuidedTour(@PathVariable("id") Long id) throws Exception {
		GuidedTourController.LOGGER.info("DELETE /api/guidedtours/{}", id);
		try {
			guidedTourService.deleteGuidedTourById(id);
			return ResponseEntity.ok().body("La visite guidée a été supprimée");
		} catch (Exception e) {
			GuidedTourController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
