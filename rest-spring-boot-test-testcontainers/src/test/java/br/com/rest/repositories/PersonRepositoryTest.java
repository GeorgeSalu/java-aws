package br.com.rest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.rest.model.Person;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

	@Autowired
	private PersonRepository repository;
	
	private Person person0;
	
	@BeforeEach
	public void setup() {
		person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
	}
	
	@DisplayName("junit test for given person object when save then returns saved person")
	@Test
	void testGivenPersonObject_WhenSave_thenReturnsSavedPerson() {
		// Given / Arrange
		
		// When / Act
		Person savedPerson = repository.save(person0);
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}
	
	@DisplayName("junit test for given person list when find all then return person list")
	@Test
	void testGivenPersonList_WhenFindAll_thenReturnPersonList() {
		// Given / Arrange
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
	
	@DisplayName("junit test for given person object when find by id then return person object")
	@Test
	void testGivenPersonObject_WhenFindByID_thenReturnPersonObject() {
		// Given / Arrange
		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("junit test for given person object when find by email then return person object")
	@Test
	void testGivenPersonObject_WhenFindByEmail_thenReturnPersonObject() {
		// Given / Arrange
		
		repository.save(person0);
		// When / Act
		Person savedPerson = repository.findByEmail(person0.getEmail()).get();
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("junit test for given person object when update person then return updated person object")
	@Test
	void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {
		// Given / Arrange
		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findById(person0.getId()).get();
		savedPerson.setFirstName("Leonardo");
		
		Person updatedPerson = repository.save(savedPerson);
		
		
		// Then / Assert
		assertNotNull(updatedPerson);
		assertEquals("Leonardo", savedPerson.getFirstName());
	}
	
	@DisplayName("junit test for given person object when delete then remove person")
	@Test
	void testGivenPersonObject_WhenDelete_thenRemovePerson() {
		// Given / Arrange
		
		repository.save(person0);

		// When / Act
		repository.deleteById(person0.getId());
		
		Optional<Person> personalObject = repository.findById(person0.getId());
		
		
		// Then / Assert
		assertTrue(personalObject.isEmpty());
	}
	
	@DisplayName("junit test for given firstname and lastname when find jpql then return person object")
	@Test
	void testGivenFirstNameAndLastName_WhenFindJPQL_thenReturnPersonObject() {
		// Given / Arrange
		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findByJPQL("Leandro", "Costa");
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("junit test for given firstname and lastname when find jpql namedparamenters then return person object")
	@Test
	void testGivenFirstNameAndLastName_WhenFindJPQLNamedParamenters_thenReturnPersonObject() {
		// Given / Arrange

		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findByJPQLNamedParameters("Leandro", "Costa");
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("junit test for given firstname and lastname when find sql native then return person object")
	@Test
	void testGivenFirstNameAndLastName_WhenFindSQLNative_thenReturnPersonObject() {
		// Given / Arrange

		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findByNativeSQL("Leandro", "Costa");
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}
	
	@DisplayName("junit test for given firstname and lastname when find sql native with named  parameters then return person object")
	@Test
	void testGivenFirstNameAndLastName_WhenFindSQLNativeWithNamedParameters_thenReturnPersonObject() {
		// Given / Arrange

		repository.save(person0);

		// When / Act
		Person savedPerson = repository.findByNativeSQLWithNamedParameters("Leandro", "Costa");
		
		// Then / Assert
		assertNotNull(savedPerson);
		assertEquals(person0.getId(), savedPerson.getId());
	}

}
