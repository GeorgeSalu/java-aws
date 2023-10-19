package br.com.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rest.exceptions.ResourceNotFoundException;
import br.com.rest.model.Person;
import br.com.rest.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonService services;
	
	private Person person0;
	
	@BeforeEach
	public void setup() {
		person0 = new Person("Leandro",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandrao@gmail.com.br");
	}
	
	@DisplayName("Given Person Object When Save Person then Return Person Object")
	@Test
	void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {
		// Given / Arrange
		
		given(repository.findByEmail(anyString())).willReturn(Optional.empty());
		given(repository.save(person0)).willReturn(person0);
		
		// When / Act
		Person savedPerson = services.create(person0);
		
		// Then / Assert
		assertNotNull(savedPerson);
	}
	
	@DisplayName("Given Existing Email When Save Person then Throws Exception")
	@Test
	void testGivenExistingEmail_WhenSavePerson_thenThrowsException() {
		// Given / Arrange
		
		given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));
		
		
		// When / Act
		assertThrows(ResourceNotFoundException.class, () -> {
			services.create(person0);
		});
		
		
		// Then / Assert
		// o metodo save nunca "never()" deve ser chamado quando passarmos qualquer "any()" instancia de Person.class
		verify(repository, never()).save(any(Person.class));
	}
	
	@DisplayName("Given Persons List When Find All Persons then Return Persons List")
	@Test
	void testGivenPersonsList_WhenFindAllPersons_thenReturnPersonsList() {
		// Given / Arrange
		
		Person person1 = new Person("Leonardo",
				"Costa", 
				"Uberlandia - Minas Gerais - Brasil",
				 "Male",
				"leandro@gmail.com.br");
		
		given(repository.findAll()).willReturn(List.of(person0, person1));
		
		
		// When / Act
		List<Person> personsList = services.findAll();
		
		// Then / Assert
		assertNotNull(personsList);
		assertEquals(2, personsList.size());
	}
	
	@DisplayName("Given Empty Persons List When Find All Persons then Return Persons List")
	@Test
	void testGivenEmptyPersonsList_WhenFindAllPersons_thenReturnPersonsList() {
		// Given / Arrange
		
		given(repository.findAll()).willReturn(Collections.EMPTY_LIST);
		
		
		// When / Act
		List<Person> personsList = services.findAll();
		
		// Then / Assert
		assertTrue(personsList.isEmpty());
		assertEquals(0, personsList.size());
	}
	
	@DisplayName("Given PersonId Object When Save Person then Return Person Object")
	@Test
	void testGivenPersonId_WhenSavePerson_thenReturnPersonObject() {
		// Given / Arrange
		
		given(repository.findById(anyLong())).willReturn(Optional.of(person0));
		
		
		// When / Act
		Person savedPerson = services.findById(1L);
		
		// Then / Assert
		assertNotNull(savedPerson);
	}

}
