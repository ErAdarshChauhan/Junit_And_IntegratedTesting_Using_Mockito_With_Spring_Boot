package com.javamind.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.javamind.entity.Employee;
import com.javamind.entity.repository.EmployeeRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeServiceTest.class})
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	private List<Employee> employees;
	
	@Test
	@Order(1)
	public void test_getEmployees() {

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
		
		//mock services
		
		when(employeeRepository.findAll()).thenReturn(employees);
		
		assertEquals(2, employeeService.getEmployees().size());
		
	}
	

	@Test
	@Order(2)
	public void test_getEmployeeById() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		int id = 1;

		when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
		Employee actualEmployee = employeeService.getEmployeeById(id);
		assertEquals(1, actualEmployee.getId());
	}
	

	@Test
	@Order(3)
	public void test_addEmployee() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		assertEquals("Ramesh Tyagi",employeeService.create(employee).getName());
		assertEquals(employee,employeeService.create(employee));
		
	}

	
	@Test
	@Order(4)
	public void test_updateEmployee() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		int id = 1;
		
		when(employeeRepository.getById(id)).thenReturn(employee);
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		assertEquals("Ramesh Tyagi",employeeService.create(employee).getName());
				
	}

	@Test
	@Order(5)
	public void test_deleteEmployee() {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Ramesh Tyagi");
		employee.setDesignation("Accountant");
		employee.setAge(45);
		
		int id = 1;

		when(employeeRepository.getById(id)).thenReturn(employee);
	
		employeeService.deleteEmployeeById(id);
		
		verify(employeeRepository,times(1)).delete(employee);
	}

}
