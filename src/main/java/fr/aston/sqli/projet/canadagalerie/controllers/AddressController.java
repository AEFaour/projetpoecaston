package fr.aston.sqli.projet.canadagalerie.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.services.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('address:read')")
	public ResponseEntity<?> findAllAddresses() {
		AddressController.LOGGER.info("GET /api/addresses");
		try {
			return ResponseEntity.ok().body(addressService.findAll());
		} catch (Exception e) {
			AddressController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('address:read')")
	public ResponseEntity<?> getAddressById(@PathVariable("id") Long id) {
		AddressController.LOGGER.info("GET /api/addresses/{}", id);
		try {
			return ResponseEntity.ok().body(addressService.findById(id));
		} catch (Exception e) {
			AddressController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('address:write')")
	public ResponseEntity<?> addOrUpdateAddress(@RequestBody Address address) {
		AddressController.LOGGER.info("POST /api/addresses");
		try {
			addressService.saveOrUpdate(address);
			return ResponseEntity.ok().body(address.getId());
		} catch (Exception e) {
			AddressController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('address:write')")
	public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) throws Exception {
		AddressController.LOGGER.info("DELETE /api/addresses/{}", id);
		try {
			addressService.deleteAddressById(id);
			return ResponseEntity.ok().body("L'adresse a été supprimée");
		} catch (Exception e) {
			AddressController.LOGGER.error("Erreur :", e);
			return ResponseEntity.badRequest().body("{'msg': 'probleme'}");
		}
	}

}
