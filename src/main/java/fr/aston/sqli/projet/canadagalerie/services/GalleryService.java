package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IGalleryRepository;
import fr.aston.sqli.projet.canadagalerie.exceptions.NotFoundWithSuchParameterException;
import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;

@Service
public class GalleryService {

	@Autowired
	private IGalleryRepository galleryRepository;

	public List<Gallery> findAllWorks() {
		return galleryRepository.findAll();
	}

	public Gallery findWorkByTitre(String titre) throws Exception {
		Optional<List<Gallery>> galleries = galleryRepository.findByTitre(titre);
		if (galleries.isPresent()) {
			return galleries.get().get(0);
		}
		throw new NotFoundWithSuchParameterException(
				"Entity does not exist with titre = " + titre + " => className: " + getClass().getSimpleName());
	}
}
