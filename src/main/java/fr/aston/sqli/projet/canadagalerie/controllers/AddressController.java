package fr.aston.sqli.projet.canadagalerie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.services.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/api/addresses", method = RequestMethod.GET)
	public ResponseEntity<?> findAllAddresses() {
		try {
			return ResponseEntity.ok().body(addressService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/addresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAddressById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(addressService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/addresses", method = RequestMethod.POST)
	public ResponseEntity<?> addOrUpdateAddress(@RequestBody Address address) {
		
		try {
			addressService.saveOrUpdate(address);
			return ResponseEntity.ok().body(address.getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@RequestMapping(value = "/api/addresses/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) throws Exception {
		try {
			addressService.deleteAddressById(id);
			return ResponseEntity.ok().body("L'adresse a été supprimée");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
