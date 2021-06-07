package fr.aston.sqli.projet.canadagalerie.services;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.controllers.GuidedTourController;
import fr.aston.sqli.projet.canadagalerie.dao.IArtistRepository;
import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.dao.IWorkRepository;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Service
public class WorkService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkService.class);

	@Autowired
	private IWorkRepository workRepository;

	@Autowired
	private IGalleryRepository galleryRepository;

	@Autowired
	private IArtistRepository artistRepository;

	public List<Work> findAll() {
		return (List<Work>) workRepository.findAll();
	}

	public Work findById(Long id) throws Exception {

		Optional<Work> resu = this.workRepository.findById(id);

		if (resu.isPresent()) {
			Work w = resu.get();
			return w;
		}
		throw new Exception("L'oeuvre d'art " + id + " est introuvable");
	}

	public void saveOrUpdate(Work work) {

		workRepository.save(work);

	}

	public void deleteWorkById(Long id) throws Exception {

		try {
			workRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("L'oeuvre d'art " + id + " est introuvable");
		}

	}

	public void transferFromNoSQLToSQL(String titre) {
		
		List<Work>listWorks = (List<Work>) workRepository.findAll();

		Gallery g = galleryRepository.findByTitre(titre).get(0);
		
	
		Work w = new Work();

		List<Artist> artists = new ArrayList<Artist>();
		
		Artist ar = new Artist();
		ar.setNom(g.getArtistes().get(0).getNom());
		artistRepository.save(ar);
		artists.add(ar);
		System.out.println(ar.getNom());
		System.out.println(g.getArtistes().get(0).getNom());
		System.out.println(artists);
		
		System.out.println(listWorks);
		w.setCode(g.getId());
		w.setTitre(titre);
		w.setCollection(g.getCollection());
		w.setCulture(g.getCulture());
		w.setDescription(g.getDescription());
		w.setDimensions(g.getDimensions());
		w.setArtists(artists);
		w.setImage(g.getImage());
		w.setDateProduction(g.getDateProduction());
		w.setMateriaux(g.getMateriaux());
		System.out.println(listWorks.contains(w));
		
		workRepository.save(w);
		LOGGER.debug("Tentive échoué de transfère de l'oeuvre d'art {}", titre);
//		if(((List<Work>) this.workRepository.findAll()).contains(w) == false) {
//			workRepository.save(w);
//		}

	}
}
