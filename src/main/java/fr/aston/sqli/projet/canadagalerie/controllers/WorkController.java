package fr.aston.sqli.projet.canadagalerie.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
@RequestMapping("/api/works")
public class WorkController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);
	
	@Autowired
	private WorkService workService;
	
	@GetMapping
	public ResponseEntity<?> findAllWorks() {
		LOGGER.info("GET /api/works");
		try {
			return ResponseEntity.ok().body(workService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getWorkById(@PathVariable("id") Long id) {
		LOGGER.info("GET /api/works/{}", id);
		try {
			return ResponseEntity.ok().body(workService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addOrUpdateWork(@RequestBody Work work) {
		LOGGER.info("POST /api/works");
		try {
			this.workService.saveOrUpdate(work);
			return ResponseEntity.ok().body(work.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteArtist(@PathVariable("id") Long id) throws Exception{
		LOGGER.info("DELETE /api/works/{}", id);
		try {
			this.workService.deleteWorkById(id);
			return ResponseEntity.ok().body("L'oeuvre d'art a été supprimé");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping("/import/{titre}")
	public ResponseEntity<?> importWorkFromGallery(@PathVariable("titre") String titre){
		LOGGER.info("POST /api/works/import/{}", titre);
		try {
			this.workService.transferFromNoSQLToSQL(titre);
			return ResponseEntity.ok().body("L'oeuvre d'art '" + titre + "' a été importé");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
		
	}

}
