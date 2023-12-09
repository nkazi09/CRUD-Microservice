package com.crudemployee.employee.service;

import java.util.List;

import com.crudemployee.employee.entity.Employee;


public interface EmployeeService {

	List<Employee> getAllEmployee();
	
	Employee createEmployee(Employee employee);
	
	Employee getEmployee(Long id);
	
	void deleteEmployee(Long id);
	
	Employee updateEmployee(Long id, Employee employee);
	
}
