package fr.aston.sqli.projet.canadagalerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@RequestMapping(value = "/api/works", method = RequestMethod.GET)
	public ResponseEntity<?> findAllWorks() {
		try {
			return ResponseEntity.ok().body(workService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/works/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getWorkById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(workService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/works", method = RequestMethod.POST)
	public ResponseEntity<?> addOrUpdateWork(@RequestBody Work work) {
		try {
			this.workService.saveOrUpdate(work);
			return ResponseEntity.ok().body(work.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/works/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteArtist(@PathVariable Long id) throws Exception{
		try {
			this.workService.deleteWorkById(id);
			return ResponseEntity.ok().body("L'oeuvre d'art a été supprimé");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
