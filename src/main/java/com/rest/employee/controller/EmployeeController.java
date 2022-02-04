package com.rest.employee.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.employee.model.Employee;
import com.rest.employee.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	 @GetMapping("/employees")
	  public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String name) {
	    try {
	      List<Employee> employee = new ArrayList<Employee>();

	      if (name == null)
	    	  employeeRepository.findAll().forEach(employee::add);
	      else
	    	  employeeRepository.findByName(name).forEach(employee::add);

	      if (employee.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(employee, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 
	 @GetMapping("/employees/{id}")
	  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
	    Employee employee = employeeRepository.findById(id);

	    if (employee != null) {
	      return new ResponseEntity<>(employee, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	 @PostMapping("/employees")
	  public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
	    try {
	    	employeeRepository.save(new Employee(employee.getId(),employee.getName(), employee.getDept(), employee.getSalary()));
	      return new ResponseEntity<>("Employee added successfully.", HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 
	 @PutMapping("/employees/{id}")
	  public ResponseEntity<String> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		 Employee employee1 = employeeRepository.findById(id);

	    if (employee1 != null) {
	      employee1.setId(id); 
	      employee1.setName(employee.getName());
	      employee1.setDept(employee.getDept());
	      employee1.setSalary(employee.getSalary());

	      employeeRepository.update(employee1);
	      return new ResponseEntity<>("Employee updated successfully.", HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Cannot update employee with id=" + id, HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	 @DeleteMapping("/employees/{id}")
	  public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
	    try {
	      int result = employeeRepository.deleteById(id);
	      if (result == 0) {
	        return new ResponseEntity<>("Can not find employee with id=" + id, HttpStatus.OK);
	      }
	      return new ResponseEntity<>("Employee Details deleted successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Can not delete employee..", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @DeleteMapping("/employees")
	  public ResponseEntity<String> deleteAllEmployees() {
	    try {
	      int numRows = employeeRepository.deleteAll();
	      return new ResponseEntity<>("Deleted " + numRows + " Employee(s) successfully.", HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Can't delete employee.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
	 
	 
	 
	 @GetMapping("/employee/{dept}")
	  public ResponseEntity<List<Employee>> findByDept(@PathVariable("dept") String dept) {
	    try {
	      List<Employee> employees = employeeRepository.findByDept(dept);

	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	 
	
	
	
	

}
