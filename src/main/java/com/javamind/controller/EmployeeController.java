package com.javamind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javamind.entity.Employee;
import com.javamind.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		
		List<Employee> employees = employeeService.getEmployees();
		if (employees.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employees);
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(employees);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
	
		Employee employee = employeeService.getEmployeeById(id);
	
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employee);
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(employee);
		}
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	
		Employee savedEmployee = employeeService.create(employee);
	
		if (savedEmployee == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(employee);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
	
		Employee savedEmployee = employeeService.updateEmployee(id,employee);
	
		if (savedEmployee == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(employee);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(employee);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
	
		employeeService.deleteEmployeeById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
	}
	
}
