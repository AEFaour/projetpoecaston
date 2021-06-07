package fr.aston.sqli.projet.canadagalerie.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;


@SpringBootTest
@Rollback(true)
@Transactional
class AddressServiceTest {

	@Autowired
	AddressService service;
	@Test
	void testSave() throws Exception {
		
		Address address = new Address();
		address.setCity("Strasbourg");
		address.setNum("5");
		address.setStreet("Place du Château");
		address.setZip("67000");
		Address res = service.saveOrUpdate(address);
		
		Assertions.assertNotNull(res, "Res ne doit pas etre null");
		Assertions.assertNotNull(res.getId(), "Res doit avoir un id");
	}

}
