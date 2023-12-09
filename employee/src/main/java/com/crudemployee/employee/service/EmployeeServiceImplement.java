package com.crudemployee.employee.service;

import java.util.List;

import com.crudemployee.employee.entity.Employee;

import EmployeeRepository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service

public class EmployeeServiceImplement implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
		
	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
    @Cacheable(value = "employees", key = "#id")
	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElse(null);

	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();

	}
	
	
	
	@Override
	public void deleteEmployee(Long id) {
		 employeeRepository.deleteById(id);

	}
	
	
	@Override
    @CachePut(value = "employees", key = "#id")
	public Employee updateEmployee(Long id, Employee employee) {
		if(employeeRepository.existsById(id)) {
			employee.setId(id);
			return employeeRepository.save(employee);
		}
		
		return null;
	}
	
	
	
}
