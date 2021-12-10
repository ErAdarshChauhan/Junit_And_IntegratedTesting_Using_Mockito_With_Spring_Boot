package com.javamind.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javamind.entity.Employee;
import com.javamind.service.EmployeeService;

//@ContextConfiguration
@AutoConfigureMockMvc
//@ComponentScan(basePackages = "com.javamind")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerMockMvcTest.class})
public class EmployeeControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	private List<Employee> employeeList;
	private Employee employee;
	
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllEmployees() throws Exception {
	
		employeeList = new ArrayList<>();
		employeeList.add(new Employee(1,"Adarsh Chauhan","Sr. Software Develop",34));
		employeeList.add(new Employee(1,"Ramesh Sippy","Film Director",80));
		
		when(employeeService.getEmployees()).thenReturn(employeeList);
		
		this.mockMvc.perform(get("/api/employee/all"))
					.andExpect(status().isFound())
					.andDo(print());
	}
	
	
	@Test
	@Order(2)
	public void test_getEmployeeById() throws Exception {
	
		employee = new Employee(1,"Adarsh Chauhan","Sr. Software Develop",34);
		
		
		when(employeeService.getEmployeeById(employee.getId())).thenReturn(employee);
		
		this.mockMvc.perform(get("/api/employee/{id}",employee.getId()))
					.andExpect(status().isFound())
					.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
					.andExpect(MockMvcResultMatchers.jsonPath(".name").value( "Adarsh Chauhan"))
					.andExpect(MockMvcResultMatchers.jsonPath(".designation").value("Sr. Software Develop"))
					.andExpect(MockMvcResultMatchers.jsonPath(".age").value(34))
					.andDo(print());
					
	}
	
	@Test
	@Order(3)
	public void test_addEmployee() throws Exception {
		
		employee = new Employee(1,"Adarsh Chauhan","Sr. Software Develop",34);
		
		when(employeeService.create(employee)).thenReturn(employee);

		ObjectMapper mapper = new ObjectMapper();
		String employeeJson = mapper.writeValueAsString(employee);
		
		this.mockMvc.perform(post("/api/employee/").content(employeeJson)
													.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated())
					.andDo(print());
	}
	
	
	
	@Test
	@Order(4)
	public void test_updateEmployee() throws Exception {
		
		Employee savedEmployee = new Employee(1,"Adarsh Chauhan","Sr. Software Develop",34);
		Employee toUpdateEmployee = new Employee(1,"Sushil Singh","Sr. Software Develop",30);
		
		when(employeeService.updateEmployee(savedEmployee.getId(), toUpdateEmployee)).thenReturn(toUpdateEmployee);

		ObjectMapper mapper = new ObjectMapper();
		String employeeJson = mapper.writeValueAsString(toUpdateEmployee);
		
		this.mockMvc.perform(put("/api/employee/{id}",savedEmployee.getId()).content(employeeJson)
													.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated())
					.andDo(print());
	}
	
	
	
	@Test
	@Order(5)
	public void test_deleteEmployee() throws Exception {
		
		Employee savedEmployee = new Employee(1,"Adarsh Chauhan","Sr. Software Develop",34);
				
		this.mockMvc.perform(delete("/api/employee/{id}",savedEmployee.getId()))
					.andExpect(status().isOk())
					.andDo(print());
	}
	
	
}
