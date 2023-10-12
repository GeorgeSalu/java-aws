package br.com.rest.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.rest.model.Person;

@DataJpaTest
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	
	@DisplayName("Given Person Object When Save then Returns Saved Person")
	@Test
	void testGivenPErsonObject_WhenSave_thenReturnsSavedPerson() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		// When / Act
		Person savedPerson = repository.save(person0);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}

}