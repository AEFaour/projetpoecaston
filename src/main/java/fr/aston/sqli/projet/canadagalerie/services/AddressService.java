package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IAddressRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Address;

@Service
public class AddressService {
	
	@Autowired
	private IAddressRepository addressRepository;
	
	public List<Address> findAll() {
		return (List<Address>) addressRepository.findAll();
	}

	public Optional<Address> findById(Long id){
		
		return addressRepository.findById(id);
	}
	
	public void saveOrUpdate(Address address) {

		addressRepository.save(address);

	}

	public void deleteAddressById(Long id) {
		
		addressRepository.deleteById(id);
	}

}
