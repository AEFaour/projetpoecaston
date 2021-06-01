package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IGuidedTourRepository;

import fr.aston.sqli.projet.canadagalerie.models.GuidedTour;

@Service
public class GuidedTourService {
	
	@Autowired
	private IGuidedTourRepository guidedTourRepository;
	
	public List<GuidedTour> findAll(){
		return (List<GuidedTour>) guidedTourRepository.findAll();
	}

}
