package com.rest.employee.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rest.employee.model.Employee;

@Repository
public class JdbcEmployeeRespository implements EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Employee employee) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("INSERT INTO employees (id, name, dept, salary) VALUES(?,?,?,?)",
				new Object[] { employee.getId(), employee.getName(), employee.getDept(), employee.getSalary() });

	}

	@Override
	public int update(Employee employee) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("UPDATE employees SET id=?, name=?, dept=?, salary=? WHERE id=?", new Object[] {
				employee.getId(), employee.getName(), employee.getDept(), employee.getSalary(), employee.getId() });

	}

	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		try {
			Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?",
					BeanPropertyRowMapper.newInstance(Employee.class), id);

			return employee;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);

	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * from employees", BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public List<Employee> findByName(String name) {
		// TODO Auto-generated method stub
		String query = "SELECT * from employees WHERE title ILIKE '%" + name + "%'";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public List<Employee> findByDept(String dept) {
		// TODO Auto-generated method stub
		String query = "SELECT * from employees WHERE title ILIKE '%" + dept + "%'";
		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));

	}

	@Override
	public List<Employee> findBySalary(Double salary) {
		String query = "SELECT * from employees WHERE title ILIKE '%" + salary + "%'";

		return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE from employees");
	}

}
