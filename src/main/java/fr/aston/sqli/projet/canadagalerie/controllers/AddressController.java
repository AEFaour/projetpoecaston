package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	private static final Logger LOG = LogManager.getLogger();	
	@Autowired
	private AddressService addressService;

	@GetMapping
	@PreAuthorize("hasAuthority('address:read')")
	public ResponseEntity<List<Address>> findAllAddresses() {
		AddressController.LOG.info("Listing all addresses");
		List<Address> addresses = this.addressService.findAll();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('address:read')")
	public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(this.addressService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			AddressController.LOG.error("No address present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('address:write')")
	public ResponseEntity<Address> addOrUpdateAddress(@RequestBody Address address) {
		AddressController.LOG.info("Managing the address [{}]", address);
		Address newAddress = this.addressService.saveOrUpdate(address);
		return new ResponseEntity<>(newAddress, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('address:write')")
	public ResponseEntity<?> deleteAddress(@PathVariable("id") Long id) {
		try {
			this.addressService.deleteAddressById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			AddressController.LOG.error("No address present with such id => {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}