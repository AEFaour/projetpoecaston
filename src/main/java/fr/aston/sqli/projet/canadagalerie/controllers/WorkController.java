package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
@RequestMapping("/api/works")
public class WorkController {

	private static final Logger LOG = LogManager.getLogger();	
	
	@Autowired
	private WorkService workService;

	@GetMapping
	@PreAuthorize("hasAuthority('work:read')")
	public ResponseEntity<List<Work>> findAllWorks() {
		WorkController.LOG.info("Listing all works that have been selected to the visits");
		List<Work> works = this.workService.findAll();
		return new ResponseEntity<>(works, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('work:read')")
	public ResponseEntity<Work> getWorkById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.workService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			WorkController.LOG.error("No work present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('work:write')")
	public ResponseEntity<Work> addOrUpdateWork(@RequestBody Work work) {
		WorkController.LOG.info("Managing the work [{}]", work);
		Work newWork = this.workService.saveOrUpdate(work);
		return new ResponseEntity<>(newWork, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('work:write')")
	public ResponseEntity<?> deleteWork(@PathVariable("id") Long id) {
		try {
			this.workService.deleteWorkById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			WorkController.LOG.error("No work with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/import/{titre}")
	@PreAuthorize("hasAuthority('work:write')")
	public ResponseEntity<?> importWorkFromGallery(@PathVariable("titre") String titre) {

		try {
			this.workService.transferFromNoSQLToSQL(titre);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			WorkController.LOG.error("Error during attempting to transfer work from Gallery to Work for the given title => {}", titre);
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

}