package br.com.rest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}
