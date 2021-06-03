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

	public Address findById(Long id) throws Exception {

		Optional<Address> resu = this.addressRepository.findById(id);

		if (resu.isPresent()) {
			Address a = resu.get();
			return a;
		}

		throw new Exception("L'Adresse " + id + " est introuvable");
	}

	public void saveOrUpdate(Address address) {

		addressRepository.save(address);

	}

	public void deleteAddressById(Long id) throws Exception{
		
		try {
			addressRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("L'Adresse " + id + " est introuvable");
		}

	}

}
