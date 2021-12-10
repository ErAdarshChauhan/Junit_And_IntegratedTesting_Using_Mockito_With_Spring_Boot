package com.javamind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamind.entity.Employee;
import com.javamind.entity.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).get();
	}

	public Employee create(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(int id, Employee employee) {
		Employee existedEmployee = employeeRepository.getById(id);
		existedEmployee.setName(employee.getName()); 
		existedEmployee.setDesignation(employee.getDesignation());
		existedEmployee.setAge(employee.getAge());
	  return employeeRepository.save(existedEmployee);
	}

	public void deleteEmployeeById(int id) {
		Employee existedEmployee = employeeRepository.getById(id);
		employeeRepository.delete(existedEmployee);
	}

}
