package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IGuidedTourRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;

@Service
public class GuidedTourService {

	@Autowired
	private IGuidedTourRepository guidedTourRepository;

	public List<GuidedTour> findAll() {
		return (List<GuidedTour>) guidedTourRepository.findAll();
	}

	
	public GuidedTour findById(Long id) throws Exception{
		
		Optional<GuidedTour> resu  = this.guidedTourRepository.findById(id);
		if(resu.isPresent()) {
			GuidedTour gdt = resu.get();
			return gdt;
		}
		throw new Exception("La Visite guidée " + id + " est introuvable");
	}
	
	public void saveOrUpdate(GuidedTour guidedTour) {

		guidedTourRepository.save(guidedTour);

	}

	public void deleteGuidedTourById(Long id) throws Exception {
		try {
			guidedTourRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("La Visite guidée " + id + " est introuvable");
		}
		
	}

}
