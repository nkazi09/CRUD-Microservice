package com.crudemployee.employee.controller;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.crudemployee.employee.entity.Employee;
import com.crudemployee.employee.service.EmployeeService;

/*
 * Nadia Kazi
 * 
 * The Employee controller class responsible to process the requests received and return the response.
 * The controller is using /api/ to map the data to the root path.  
 * 
 * */


@RestController
@RequestMapping("/api")

public class EmployeeController {

	  /*
	   * This is the employeeService, is an object responsible to store all fields required in response.
	   */
	
	@Autowired
	private EmployeeService employeeService;

	// Accepts employee as parameter and creates an employee 
	// throws 500 error if failed to create an employee
    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, Principal principal) {
        ResponseEntity<Employee> authCheckResult = checkAuthAndAuthz(principal, "CREATE_EMPLOYEE");
        if (authCheckResult != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authCheckResult.getBody());
        }

        
        try {
        	
            Employee createEmployee = employeeService.createEmployee(employee); 
            return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
     

    }

    // Accepts employee id as parameter and retrieve the employee
    // Throws exception if not found
    @GetMapping("/employee{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id, Principal principal) {
    	
        ResponseEntity<Employee> authCheckResult = checkAuthAndAuthz(principal, "READ_EMPLOYEE");
        if (authCheckResult != null) {
            return authCheckResult;
        }
        
        if (id == null) {
        	throw new IllegalArgumentException("Please provide an employee id to retrieve the employee.");
        }
        try {
            Employee employee = employeeService.getEmployee(id);
            
            return (employee != null) ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Retrieves all current employees in the database
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee(Principal principal) {
        ResponseEntity<Employee> authCheckResult = checkAuthAndAuthz(principal, "READ_EMPLOYEE");
        if (authCheckResult != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }

        try {
            List<Employee> employees = employeeService.getAllEmployee();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // Updates an employee based on the given employee id and the new updated employee details
    // throws exception if id is null
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee, Principal principal) {
        
        ResponseEntity<Employee> authCheckResult = checkAuthAndAuthz(principal, "UPDATE_EMPLOYEE");

        if (authCheckResult != null) {
            return authCheckResult;
        }
        
        if (id == null) {
        	throw new IllegalArgumentException("Please provide an employee id to update the employee.");
        }

        try {
        	Employee updateEmployee = employeeService.updateEmployee(id, employee);
        	return (updateEmployee != null) ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
        	
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    

    // deletes an employee given the employee id
    // throws exception if id is null
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id, Principal principal) {
    	
        ResponseEntity<Employee> authCheckResult = checkAuthAndAuthz(principal, "DELETE_EMPLOYEE");
        if (authCheckResult != null) {
            return authCheckResult;
        }

        if (id == null) {
            throw new IllegalArgumentException("Please provide an employee id to delete the employee.");
        }

        try {
        	employeeService.deleteEmployee(id);
        	return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }

    // Checks if the user has required permissions
    private boolean hasPermission(Principal principal, String requiredPermission) {

        Authentication authentication = (Authentication) principal;
        return authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(requiredPermission));
    }
    
    //Check for any Unauthorized request  and respond
    private ResponseEntity<Employee> checkAuthAndAuthz(Principal principal, String requiredPermission) {
    	 	//Checks Authentication 
    		if (principal == null) {
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	    }

    	    // Checks Authorization 
    	    if (!hasPermission(principal, requiredPermission)) {
    	        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    	    }

    	    return ResponseEntity.ok().build();
    }

}
