package fr.aston.sqli.projet.canadagalerie.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Repository
public interface IWorkRepository extends CrudRepository<Work, Long> {
	public Optional<Work> findByCode(String code);
}