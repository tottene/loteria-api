package br.com.loteria.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loteria.api.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByEmail(String email);
}
