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

import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
@RequestMapping("/api/works")
public class WorkController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);
	
	@Autowired
	private WorkService workService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUIDE')")
	public ResponseEntity<?> findAllWorks() {
		WorkController.LOGGER.info("GET /api/works");
		try {
			return ResponseEntity.ok().body(workService.findAll());
		} catch (Exception e) {
			WorkController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUIDE')")
	public ResponseEntity<?> getWorkById(@PathVariable("id") Long id) {
		WorkController.LOGGER.info("GET /api/works/{}", id);
		try {
			return ResponseEntity.ok().body(workService.findById(id));
		} catch (Exception e) {
			WorkController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('exploiter:write')")
	public ResponseEntity<?> addOrUpdateWork(@RequestBody Work work) {
		WorkController.LOGGER.info("POST /api/works");
		try {
			this.workService.saveOrUpdate(work);
			return ResponseEntity.ok().body(work.getId());
		} catch (Exception e) {
			WorkController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('exploiter:write')")
	public ResponseEntity<?> deleteArtist(@PathVariable("id") Long id) throws Exception{
		WorkController.LOGGER.info("DELETE /api/works/{}", id);
		try {
			this.workService.deleteWorkById(id);
			return ResponseEntity.ok().body("L'oeuvre d'art a été supprimé");
		} catch (Exception e) {
			WorkController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping("/import/{titre}")
	@PreAuthorize("hasAuthority('exploiter:write')")
	public ResponseEntity<?> importWorkFromGallery(@PathVariable("titre") String titre){
		WorkController.LOGGER.info("POST /api/works/import/{}", titre);
		try {
			this.workService.transferFromNoSQLToSQL(titre);
			return ResponseEntity.ok().body("L'oeuvre d'art '" + titre + "' a été importé");
		} catch (Exception e) {
			WorkController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
		
	}

}
