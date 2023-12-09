package com.crudemployee.employee.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.*;

/*
 * Author: Nadia Kazi
 * This is the entity class represent an Employee
 */

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	private Long id;
	private String name;
	

	//Constructor, getter, and Setters
	public Employee() {}
	
	
	public Employee(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}	
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
}

