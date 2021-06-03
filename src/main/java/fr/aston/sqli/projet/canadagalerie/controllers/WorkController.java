package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;
import fr.aston.sqli.projet.canadagalerie.services.WorkService;

@RestController
public class WorkController {
	
	@Autowired
	private WorkService workService;
	
	@RequestMapping(value = "/api/works", method = RequestMethod.GET)
	public List<Work> findAllWorks() {
		return workService.findAll();
	}
	
	@RequestMapping(value = "/api/works/{id}", method = RequestMethod.GET)
	public Optional<Work> getWorkById(@PathVariable Long id) {
		
		return workService.findById(id);
	}
	
	@RequestMapping(value = "/api/works", method = RequestMethod.POST)
	public Long addOrUpdateWork(@RequestBody Work work) {

		workService.saveOrUpdate(work);
		return work.getId();
	}
	
	@RequestMapping(value = "/api/works/{id}", method = RequestMethod.DELETE)
	public String deleteArtist(@PathVariable Long id) {
		workService.deleteWorkById(id);
		return "L'oeuvre d'art a été supprimé";
	}

}
