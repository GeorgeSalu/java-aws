package br.com.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.model.Person;

@Repository
public interface PErsonRepository extends JpaRepository<Person, Long>{

}
