package fr.aston.sqli.projet.canadagalerie.dao;

//import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fr.aston.sqli.projet.canadagalerie.models.nosql.Gallery;

@Repository
public interface IGalleryRepository extends MongoRepository<Gallery, Integer> {

}
