package br.com.rest.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rest.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public Person findById(String id) {
		logger.info("Finding one person");
		Person person =  new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro");
		person.setLastName("costa");
		person.setAddress("Minas");
		person.setGender("Male");
		
		return person;
	}
	
}
