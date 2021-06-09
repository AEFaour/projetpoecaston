package fr.aston.sqli.projet.canadagalerie.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		WorkService.LOGGER.debug("transferFromNoSQLToSQL {}", titre);
		Gallery g = galleryRepository.findByTitre(titre).get(0);
		
		Optional<Work> opWork = this.workRepository.findByCode(g.getId());
		if (opWork.isPresent()) {
			WorkService.LOGGER.warn("Work '{}' deja present sous l'id {}, on ne fait rien", titre,
					opWork.get().getId());
			return;
		}
		WorkService.LOGGER.debug("Work '{}' n'est pas present, on ne fait l'import", titre);
		Work w = new Work();
		w.setCode(g.getId());
		w.setTitre(titre);
		w.setCollection(g.getCollection());
		w.setCulture(g.getCulture());
		w.setDescription(g.getDescription());
		w.setDimensions(g.getDimensions());
		w.setImage(g.getImage());
		w.setDateProduction(g.getDateProduction());
		w.setMateriaux(g.getMateriaux());

		List<Artist> artists = new ArrayList<>();
		final String nomArtiste = g.getArtistes().get(0).getNom();
		Optional<Artist> opAr = this.artistRepository.findByNom(nomArtiste);
		if (opAr.isPresent()) {
			WorkService.LOGGER.debug("Artiste {} deja present en base", opAr.get());
			artists.add(opAr.get());
		} else {
			WorkService.LOGGER.debug("Artiste {} NON present en base, on l'ajoute", nomArtiste);
			Artist ar = new Artist();
			ar.setNom(nomArtiste);
			ar = this.artistRepository.save(ar);
			artists.add(ar);
			ar.setWorks(Arrays.asList(new Work[] { w }));
		}
		w.setArtists(artists);
		// On sauvegarde le work
		this.workRepository.save(w);
		WorkService.LOGGER.debug("Transfère de l'oeuvre d'art {} - OK", titre);

	}
}
