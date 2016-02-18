package com.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dto.EmployeeDTO;
import com.dto.IdDTO;
import com.dto.PersonDTO;
import com.service.EmployeeService;
import com.service.PersonService;

@Path("link")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MainAPI {

	@Inject
	private PersonService personService;
	
	@Inject
	private EmployeeService employeeService;
	
    @POST
    @Path("person")
    public IdDTO createPerson(PersonDTO person) {
        return personService.create(person);
    }
    
    @POST
    @Path("employee")
    public IdDTO createEmployee(EmployeeDTO employee) {
    	return employeeService.create(employee);
    }
}
