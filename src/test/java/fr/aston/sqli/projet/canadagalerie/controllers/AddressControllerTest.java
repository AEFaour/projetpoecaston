package fr.aston.sqli.projet.canadagalerie.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;

@SpringBootTest
@Rollback(true)
@Transactional
class AddressControllerTest {

//	AddressController controller; 
//	@Test
//	void testSave() throws Exception {
//		Address address = new Address();
//		address.setCity("Strasbourg");
//		address.setNum("5");
//		address.setStreet("Place du Château");
//		address.setZip("67000");
//		
//		ResponseEntity<?> res = controller.addOrUpdateAddress(address);
//		
//		Assertions.assertNotNull(res, "Res ne doit pas etre null");
//		Assertions.assertNotNull(res.getHeaders(), "Res doit avoir un header");
//	}

}
