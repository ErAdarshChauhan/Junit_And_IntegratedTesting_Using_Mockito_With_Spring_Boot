package com.javamind.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javamind.entity.Employee;
import com.javamind.service.EmployeeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerTest.class})
public class EmployeeControllerTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	private List<Employee> employeeList;
	//private Employee employee;

	@Test
	@Order(1)
	public void test_getAllEmployees() {
		
		Employee employee1 = new Employee();
		employee1.setId(1);
		employee1.setName("Ramesh Tyagi");
		employee1.setDesignation("Accountant");
		employee1.setAge(45);
		
		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("Sushil Singh");
		employee2.setDesignation("Sr. Software Engineer");
		employee2.setAge(30);
		
		
		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);
		
		when(employeeService.getEmployees()).thenReturn(employees);
		
		ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(2, response.getBody().size());
	}
	
	
	@Test
	@Order(2)
	public void test_getEmployeeById() {
		
		Employee employee1 = new Employee();
		employee1.setId(1);
		employee1.setName("Ramesh Tyagi");
		employee1.setDesignation("Accountant");
		employee1.setAge(45);
	
		int employeeId = 1;
		
		when(employeeService.getEmployeeById(employeeId)).thenReturn(employee1);
		
		ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);
		
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(employee1, response.getBody());
	}
	
	
	@Test
	@Order(3)
	public void test_addEmployee() {
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		when(employeeService.create(employee)).thenReturn(employee);
		
		ResponseEntity<Employee> response = employeeController.addEmployee(employee);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(employee, response.getBody());
	}
	
	@Test
	@Order(4)
	public void test_updateEmployee() {
		
		Employee savedEmployee = new Employee();
		savedEmployee.setId(1);
		savedEmployee.setName("Ramesh Tyagi");
		savedEmployee.setDesignation("Accountant");
		savedEmployee.setAge(45);
		
		
		Employee toUpdateEmployee = new Employee();
		toUpdateEmployee.setId(1);
		toUpdateEmployee.setName("Sushil Singh");
		toUpdateEmployee.setDesignation("Sr. Software Developer");
		toUpdateEmployee.setAge(30);
		
		
		when(employeeService.updateEmployee(savedEmployee.getId(),toUpdateEmployee)).thenReturn(toUpdateEmployee);
		
		ResponseEntity<Employee> response = employeeController.updateEmployee(toUpdateEmployee, savedEmployee.getId());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(toUpdateEmployee.getName(), response.getBody().getName());
		assertEquals(toUpdateEmployee.getDesignation(), response.getBody().getDesignation());
		assertEquals(toUpdateEmployee,response.getBody());
	}
	

	@Test
	@Order(5)
	public void test_deleteEmployee() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		employeeService.deleteEmployeeById(employee.getId());
		ResponseEntity<String> response = employeeController.deleteEmployee(employee.getId());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Employee Deleted", response.getBody());
	}
	
}
