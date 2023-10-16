package br.com.rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByEmail(String email);
	
	// define custom query using JPQL with index parameters
	@Query("select p from Person p where p.firstName =?1 and p.lastName =?2")
	Person findByJPQL(String firstName, String lastName);
	
	@Query("select p from Person p where p.firstName =:firstName and p.lastName =:lastName")
	Person findByJPQLNamedParameters(@Param("firstName") String firstName,@Param("lastName") String lastName);
	
}
