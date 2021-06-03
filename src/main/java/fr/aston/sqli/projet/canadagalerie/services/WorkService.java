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

	public Optional<Work> findById(Long id){
		
		return workRepository.findById(id);
	}
	
	public void saveOrUpdate(Work work) {

		workRepository.save(work);

	}

	public void deleteWorkById(Long id) {
		
		workRepository.deleteById(id);
	}
}
