package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.aston.sqli.projet.canadagalerie.dao.IAddressRepository;
import fr.aston.sqli.projet.canadagalerie.exceptions.NotFoundWithSuchParameterException;
import fr.aston.sqli.projet.canadagalerie.models.sql.Address;

@Service
public class AddressService {

	@Autowired
	private IAddressRepository addressRepository;

	public List<Address> findAll() {
		return (List<Address>) addressRepository.findAll();
	}

	public Address findById(Long id) throws Exception {
		Optional<Address> adress = addressRepository.findById(id);
		if (adress.isPresent()) {
			return adress.get();
		}
		throw new NotFoundWithSuchParameterException(
				"Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
	}

	public Address saveOrUpdate(Address address) {
		return addressRepository.save(address);
	}

	public void deleteAddressById(Long id) throws Exception {
		try {
			addressRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundWithSuchParameterException(
					"Entity does not exist with id = " + id + " => className: " + getClass().getSimpleName());
		}
	}

}