package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.aston.sqli.projet.canadagalerie.dao.IGuidedTourRepository;
import fr.aston.sqli.projet.canadagalerie.exceptions.NotFoundWithSuchParameterException;
import fr.aston.sqli.projet.canadagalerie.models.sql.Exploiter;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;

@Service
public class GuidedTourService {

	@Autowired
	private IGuidedTourRepository guidedTourRepository;

	public List<GuidedTour> findAll() {
		return (List<GuidedTour>) guidedTourRepository.findAll();
	}

	public GuidedTour findById(Long id) throws Exception {
		Optional<GuidedTour> guidedTour = guidedTourRepository.findById(id);
		if (guidedTour.isPresent()) {
			return guidedTour.get();
		}
		throw new NotFoundWithSuchParameterException(
				"Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
	}

	public GuidedTour saveOrUpdate(GuidedTour guidedTour) {
		return guidedTourRepository.save(guidedTour);
	}

	public void deleteGuidedTourById(Long id) throws Exception {
		try {
			guidedTourRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundWithSuchParameterException(
					"Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
		}
	}

}