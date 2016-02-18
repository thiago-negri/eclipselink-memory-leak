package com.repository;

import javax.inject.Inject;

import com.entity.Employee;
import com.produce.RepositoryScopeJPA;

public class EmployeeRepository {

	private RepositoryScopeJPA scope;

	@Inject
	public EmployeeRepository(RepositoryScopeJPA scope) {
		this.scope = scope;
	}
	
	public void create(Employee employee) {
		if (!scope.getContext().isJoinedToTransaction()) {
			scope.getContext().joinTransaction();
        }
		scope.getContext().persist(employee);
	}
}
