package fr.aston.sqli.projet.canadagalerie.dao;


import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Repository
public interface IWorkRepository extends CrudRepository<Work, Long> {

}