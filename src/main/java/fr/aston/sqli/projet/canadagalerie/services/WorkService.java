package fr.aston.sqli.projet.canadagalerie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IArtistRepository;
import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.dao.IWorkRepository;
import fr.aston.sqli.projet.canadagalerie.exceptions.NotFoundWithSuchParameterException;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Artiste;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;
import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.Artist;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Service
public class WorkService {

	@Autowired
	private IWorkRepository workRepository;

	@Autowired
	private GalleryService galleryService;

	@Autowired
	private IArtistRepository artistRepository;

	public List<Work> findAll() {
		return (List<Work>) workRepository.findAll();
	}

	public Work findById(Long id) throws Exception {

		Optional<Work> work = workRepository.findById(id);
		if (work.isPresent()) {
			return work.get();
		}
		throw new NotFoundWithSuchParameterException("Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
	}

	public Work saveOrUpdate(Work work) {
		return workRepository.save(work);
	}

	public void deleteWorkById(Long id) throws Exception {

		try {
			workRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public Work transferFromNoSQLToSQL(String titre) throws Exception {

		try {
			Gallery g = galleryService.findWorkByTitre(titre);
			Work w = new Work();
			List<Artist> artists = new ArrayList<>();
			for (Artiste artiste : g.getArtistes()) {
				Artist artist = new Artist();
				artist.setNom(artiste.getNom());
				artistRepository.save(artist);
				artists.add(artist);
			}
			w.setArtists(artists);
			w.setCode(g.getId());
			w.setTitre(titre);
			w.setCollection(g.getCollection());
			w.setCulture(g.getCulture());
			w.setDescription(g.getDescription());
			w.setDimensions(g.getDimensions());
			w.setImage(g.getImage());
			w.setDateProduction(g.getDateProduction());
			w.setMateriaux(g.getMateriaux());
			return workRepository.save(w);

		} catch (Exception e) {
			throw new NotFoundWithSuchParameterException(
					"Entity does not exist with titre = " + titre + " => className: " + getClass().getSimpleName());
		}
	}

}