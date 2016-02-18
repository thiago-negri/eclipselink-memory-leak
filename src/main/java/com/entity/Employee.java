package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantTableDiscriminator;
import org.eclipse.persistence.annotations.TenantTableDiscriminatorType;

@Entity
@Table(name = "EMPLOYEE")
@Multitenant(value=MultitenantType.TABLE_PER_TENANT)
@TenantTableDiscriminator(type=TenantTableDiscriminatorType.SCHEMA)
public class Employee {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name="ID")
	private String id;
	
	@Column(name = "PERSON", length = 32)
    private String personId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON", referencedColumnName = "ID", insertable = false, updatable = false)
    private Person person;
    
    @Column(name = "WORKSTATION")
    private String workstation;

	public String getWorkstation() {
		return workstation;
	}

	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		this.personId = person == null ? null : person.getId();
	}
    
}
