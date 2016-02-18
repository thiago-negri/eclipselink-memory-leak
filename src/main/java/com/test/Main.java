package com.test;

import javax.ws.rs.core.Response;

import com.dto.EmployeeDTO;
import com.dto.IdDTO;
import com.dto.PersonDTO;

public class Main {

	public static void main(String[] args) {
		RestClient client = new RestClient();
		
		for (int i = 0 ; i < 9000 ; i++) {
			PersonDTO person = new PersonDTO();
			person.name = "Marcos";
			Response post = client.post("person", person);
			IdDTO idPerson = post.readEntity(IdDTO.class);
			post.close();
			
			for (int j = 0 ; j < 9000 ; j++) {
				EmployeeDTO employee = new EmployeeDTO();
				employee.workstation = "Workstation";
				employee.personId = idPerson.id;
				client.post("employee", employee).close();
			}
		}		
	}

}
