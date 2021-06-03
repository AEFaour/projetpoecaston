package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private GuidedTourService guidedTourService;

	@GetMapping
	public ResponseEntity<List<GuidedTour>> findAllGuidedTours() {
		List<GuidedTour> works = this.guidedTourService.findAll();
		return new ResponseEntity<>(works, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GuidedTour> getGuidedTourById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.guidedTourService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<GuidedTour> addOrUpdateGuidedTour(@RequestBody GuidedTour guidedTour) {
		GuidedTour newTour = this.guidedTourService.saveOrUpdate(guidedTour);
		return new ResponseEntity<>(newTour, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGuidedTour(@PathVariable("id") Long id) {
		try {
			this.guidedTourService.deleteGuidedTourById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}