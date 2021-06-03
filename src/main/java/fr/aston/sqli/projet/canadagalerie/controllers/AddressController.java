package fr.aston.sqli.projet.canadagalerie.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.GuidedTour;
import fr.aston.sqli.projet.canadagalerie.services.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/api/addresses", method = RequestMethod.GET)
	public List<Address> findAllAddresses() {
		return addressService.findAll();
	}
	
	@RequestMapping(value = "/api/addresses/{id}", method = RequestMethod.GET)
	public Optional<Address> getAddressById(@PathVariable Long id) {
		
		return addressService.findById(id);
	}
	
	@RequestMapping(value = "/api/addresses", method = RequestMethod.POST)
	public Long addOrUpdateAddress(@RequestBody Address address) {

		addressService.saveOrUpdate(address);
		return address.getId();
	}
	
	@RequestMapping(value = "/api/addresses/{id}", method = RequestMethod.DELETE)
	public String deleteAddress(@PathVariable Long id) {
		addressService.deleteAddressById(id);
		return "L'adresse a été supprimée";
	}

}
