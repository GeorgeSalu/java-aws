package br.com.rest.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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
	void testGivenPersonObject_WhenSave_thenReturnsSavedPerson() {
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
	
	@DisplayName("Given Person List When Find All then Return Person List")
	@Test
	void testGivenPersonList_WhenFindAll_thenReturnPersonList() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		Person person1 = new Person("Leonardo",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		repository.save(person0);
		repository.save(person1);

		// When / Act
		List<Person> personList = repository.findAll();
		
		// Then / Assert
		assertNotNull(personList);
		assertEquals(2, personList.size());
	}
	
	@DisplayName("Given Person Object When Find By ID then Return Person Object")
	@Test
	void testGivenPersonObject_WhenFindByID_thenReturnPersonObject() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("Given Person Object When Find By Email then Return Person Object")
	@Test
	void testGivenPersonObject_WhenFindByEmail_thenReturnPersonObject() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		repository.save(person0);
		// When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("Given Person Object When Update Person then Return Updated Person Object")
	@Test
	void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		savedPerson.setFirstName("Leonardo");
		
		Person updatedPerson = repository.save(savedPerson);
		
		
		// Then / Assert
		assertNotNull(updatedPerson);
		assertEquals("Leonardo", savedPerson.getFirstName());
	}
	
	@DisplayName("Given Person Object When Delete then Remove Person")
	@Test
	void testGivenPersonObject_WhenDelete_thenRemovePerson() {
		// Given / Arrange
		Person person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		repository.save(person0);

		// When / Act
		repository.deleteById(person0.getId());
		
		Optional<Person> personalObject = repository.findById(person0.getId());
		
		
		// Then / Assert
		assertTrue(personalObject.isEmpty());
	}

}
