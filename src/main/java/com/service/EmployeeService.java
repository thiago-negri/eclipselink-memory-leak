package com.service;

import javax.inject.Inject;

import com.dto.EmployeeDTO;
import com.dto.IdDTO;
import com.entity.Employee;
import com.repository.EmployeeRepository;

public class EmployeeService {

	@Inject
	private EmployeeRepository employeeRepository;
	
	public IdDTO create(EmployeeDTO dto) {
		Employee employee = new Employee();
		employee.setWorkstation(dto.workstation);
		employee.setPersonId(dto.personId);
		employeeRepository.create(employee);
		IdDTO id = new IdDTO();
		id.id = employee.getId();
		return id;
	}
}
