package fr.aston.sqli.projet.canadagalerie.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;

@Repository
public interface IGuidedTourRepository extends CrudRepository<GuidedTour, Integer> {

}
