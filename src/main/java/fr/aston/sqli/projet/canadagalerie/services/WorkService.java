package fr.aston.sqli.projet.canadagalerie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aston.sqli.projet.canadagalerie.dao.IWorkRepository;
import fr.aston.sqli.projet.canadagalerie.models.sql.Address;
import fr.aston.sqli.projet.canadagalerie.models.sql.Work;

@Service
public class WorkService {

	@Autowired
	private IWorkRepository workRepository;

	public List<Work> findAll() {
		return (List<Work>) workRepository.findAll();
	}

	public Work findById(Long id) throws Exception {

		Optional<Work> work = workRepository.findById(id);
		if (work.isPresent()) {
			return work.get();
		}
		throw new Exception();
	}

	public Work saveOrUpdate(Work work) {
		return workRepository.save(work);
	}

	public void deleteWorkById(Long id) throws Exception {

		try {
			workRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception();
		}
	}
}