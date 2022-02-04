package com.rest.employee.repository;

import java.util.List;

import com.rest.employee.model.Employee;

public interface EmployeeRepository {

	int save(Employee employee);

	int update(Employee employee);

	Employee findById(Integer id);

	int deleteById(Integer id);

	List<Employee> findAll();

	List<Employee> findByName(String name);

	List<Employee> findByDept(String dept);

	List<Employee> findBySalary(Double salary);

	int deleteAll();

}
