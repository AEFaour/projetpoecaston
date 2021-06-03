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

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
@RequestMapping("/api/works")
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@GetMapping
	public ResponseEntity<List<Work>> findAllWorks() {
		List<Work> works = this.workService.findAll();
		return new ResponseEntity<>(works, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Work> getWorkById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.workService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Work> addOrUpdateWork(@RequestBody Work work) {
		Work newWork = this.workService.saveOrUpdate(work);
		return new ResponseEntity<>(newWork, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteArtist(@PathVariable("id") Long id) {
		try {
			this.workService.deleteWorkById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}