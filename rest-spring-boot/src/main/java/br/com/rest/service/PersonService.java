package br.com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rest.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	
	private Person mockPerson(int i) {
		
		Person person =  new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro "+i);
		person.setLastName("costa "+i);
		person.setAddress("Minas "+i);
		person.setGender("Male "+i);
		
		return person;
	}

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
