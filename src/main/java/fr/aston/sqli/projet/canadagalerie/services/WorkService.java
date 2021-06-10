package fr.aston.sqli.projet.canadagalerie.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.aston.sqli.projet.canadagalerie.dao.IArtistRepository;
import fr.aston.sqli.projet.canadagalerie.dao.IWorkRepository;
import fr.aston.sqli.projet.canadagalerie.exceptions.NotFoundWithSuchParameterException;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Artiste;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Service
public class WorkService {

	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private IWorkRepository workRepository;
	@Autowired
	private GalleryService galleryRepository;
	@Autowired
	private IArtistRepository artistRepository;

	public List<Work> findAll() {
		return (List<Work>) this.workRepository.findAll();
	}

	public Work findById(Long id) throws Exception {

		Optional<Work> work = this.workRepository.findById(id);
		if (work.isPresent()) {
			return work.get();
		}
		throw new NotFoundWithSuchParameterException(
				"Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
	}

	public Work saveOrUpdate(Work work) {
		return this.workRepository.save(work);
	}

	public void deleteWorkById(Long id) throws Exception {

		try {
			this.workRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferFromNoSQLToSQL(String titre) throws Exception {
		WorkService.LOG.debug("transferFromNoSQLToSQL {}", titre);
		Gallery g = this.galleryRepository.findWorkByTitre(titre);

		// Le titre est il dejà dans les work ?
		Optional<Work> opWork = this.workRepository.findByCode(g.getId_work());
		if (opWork.isPresent()) {
			WorkService.LOG.warn("Work '{}' deja present sous l'id {}, on ne fait rien", titre,
					opWork.get().getId());
			return;
		}
		WorkService.LOG.debug("Work '{}' n'est pas present, on ne fait l'import", titre);
		Work w = new Work();
		w.setCode(g.getId_work());
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
			WorkService.LOG.debug("Artiste {} deja present en base", opAr.get());
			artists.add(opAr.get());
		} else {
			WorkService.LOG.debug("Artiste {} NON present en base, on l'ajoute", nomArtiste);
			Artist ar = new Artist();
			ar.setNom(nomArtiste);
			ar = this.artistRepository.save(ar);
			artists.add(ar);
			ar.setWorks(Arrays.asList(new Work[] { w }));
		}
		w.setArtists(artists);
		// On sauvegarde le work
		this.workRepository.save(w);
		WorkService.LOG.debug("Transfère de l'oeuvre d'art {} - OK", titre);
	}
}