package com.repository;

import javax.inject.Inject;

import com.entity.Person;
import com.produce.RepositoryScopeJPA;

public class PersonRepository {
	
	private RepositoryScopeJPA scope;

	@Inject
	public PersonRepository(RepositoryScopeJPA scope) {
		this.scope = scope;
	}
	
	public void create(Person employee) {
		if (!scope.getContext().isJoinedToTransaction()) {
			scope.getContext().joinTransaction();
        }
		scope.getContext().persist(employee);
	}
}
