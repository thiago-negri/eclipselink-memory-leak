package com.service;

import javax.inject.Inject;

import com.dto.IdDTO;
import com.dto.PersonDTO;
import com.entity.Person;
import com.repository.PersonRepository;


public class PersonService {

	@Inject
	private PersonRepository personRepository;
	
	public IdDTO create(PersonDTO dto) {
		Person person = new Person();
		person.setName(dto.name);
		personRepository.create(person);
		IdDTO id = new IdDTO();
		id.id = person.getId();
		return id;
	}
}
