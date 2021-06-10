package fr.aston.sqli.projet.canadagalerie.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.GuidedTourService;

@RestController
@RequestMapping("/api/guidedtours")
public class GuidedTourController {
	
	private static final Logger LOG = LogManager.getLogger();	

	@Autowired
	private GuidedTourService guidedTourService;

	@GetMapping
	@PreAuthorize("hasAuthority('guidedtour:read')")
	public ResponseEntity<List<GuidedTour>> findAllGuidedTours() {
		GuidedTourController.LOG.info("Listing all Guided_tours");
		List<GuidedTour> works = this.guidedTourService.findAll();
		return new ResponseEntity<>(works, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('guidedtour:read')")
	public ResponseEntity<GuidedTour> getGuidedTourById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.guidedTourService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			GuidedTourController.LOG.error("No guided tour present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('guidedtour:write')")
	public ResponseEntity<GuidedTour> addOrUpdateGuidedTour(@RequestBody GuidedTour guidedTour) {
		GuidedTourController.LOG.info("Managing the guided tour [{}]", guidedTour);
		GuidedTour newTour = this.guidedTourService.saveOrUpdate(guidedTour);
		return new ResponseEntity<>(newTour, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('guidedtour:write')")
	public ResponseEntity<?> deleteGuidedTour(@PathVariable("id") Long id) {
		try {
			this.guidedTourService.deleteGuidedTourById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			GuidedTourController.LOG.error("No guided tour present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}