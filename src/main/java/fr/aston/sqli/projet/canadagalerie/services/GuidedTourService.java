package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IGuidedTourRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;

@Service
public class GuidedTourService {

	@Autowired
	private IGuidedTourRepository guidedTourRepository;

	public List<GuidedTour> findAll() {
		return (List<GuidedTour>) guidedTourRepository.findAll();
	}

	public Optional<GuidedTour> findById(Long id){
		
		return guidedTourRepository.findById(id);
	}
	
	public void saveOrUpdate(GuidedTour guidedTour) {

		guidedTourRepository.save(guidedTour);

	}

	public void deleteGuidedTourById(Long id) {
		
		 guidedTourRepository.deleteById(id);
	}

}
