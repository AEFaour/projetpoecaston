  
package fr.aston.sqli.projet.canadagalerie.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;

@Repository
public interface IAddressRepository extends CrudRepository<Address, Long>{

}