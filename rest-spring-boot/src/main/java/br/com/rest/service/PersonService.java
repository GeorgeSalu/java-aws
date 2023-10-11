package br.com.rest.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rest.exceptions.ResourceNotFoundException;
import br.com.rest.model.Person;
import br.com.rest.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("findAll persons");
		return repository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("create person");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("update person");
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("delete person");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		repository.delete(entity);
	}

	public Person findById(Long id) {
		logger.info("find person");
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
}
