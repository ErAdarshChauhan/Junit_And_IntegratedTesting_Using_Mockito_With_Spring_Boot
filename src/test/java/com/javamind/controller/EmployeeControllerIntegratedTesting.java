package com.javamind.controller;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.javamind.entity.Employee;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {EmployeeControllerIntegratedTesting.class})
public class EmployeeControllerIntegratedTesting {

	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	@Order(1)
	public void integratedTest_getAllEmployees() throws JSONException {
		
		String expected = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"name\": \"Adarsh Chauhan\",\r\n"
				+ "        \"designation\": \"Sr. Software Developer\",\r\n"
				+ "        \"age\": 34\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"name\": \"Sushil Singh\",\r\n"
				+ "        \"designation\": \"Software Engineer\",\r\n"
				+ "        \"age\": 33\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"name\": \"Ramesh Sippy\",\r\n"
				+ "        \"designation\": \"Film Director\",\r\n"
				+ "        \"age\": 90\r\n"
				+ "    }\r\n"
				+ "]";
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9000/api/employee/all", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(),false);
		
	}
	
	
	
	@Test
	@Order(2)
	public void integratedTest_getEmployeeById() throws JSONException {
	
		String expected = "{\r\n"
				+ "    \"id\": 3,\r\n"
				+ "    \"name\": \"Ramesh Sippy\",\r\n"
				+ "    \"designation\": \"Film Director\",\r\n"
				+ "    \"age\": 90\r\n"
				+ "}";
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9000/api/employee/3", String.class);
	
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(),false);
	}

	
	@Test
	@Order(3)
	public void integratedTest_addEmployee() throws JSONException {

		Employee employee = new Employee(4,"Ram Singh","Accountant",35);
	
		String expected = "{\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"name\": \"Ram Singh\",\r\n"
				+ "    \"designation\": \"Construction\",\r\n"
				+ "    \"age\": 40\r\n"
				+ "}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//send RequestBody Employee along with HttpHeaders
		HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9000/api/employee/", httpEntity, String.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	
	@Test
	@Order(4)
	public void integratedTest_updateEmployee() throws JSONException {

		Employee employee = new Employee(4,"Ram Singh","Construction",35);
	
		String expected = "{\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"name\": \"Ram Singh\",\r\n"
				+ "    \"designation\": \"Construction\",\r\n"
				+ "    \"age\": 40\r\n"
				+ "}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//send RequestBody Employee along with HttpHeaders
		HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9000/api/employee/4", HttpMethod.PUT,httpEntity, String.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(5)
	public void integratedTest_deleteEmployee() throws JSONException {

		Employee employee = new Employee(4,"Ram Singh","Construction",35);
		
		String expected = "{\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"name\": \"Ram Singh\",\r\n"
				+ "    \"designation\": \"Construction\",\r\n"
				+ "    \"age\": 40\r\n"
				+ "}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//send RequestBody Employee along with HttpHeaders
		HttpEntity<Employee> httpEntity = new HttpEntity<Employee>(employee,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:9000/api/employee/4", HttpMethod.DELETE,httpEntity, String.class);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	
	}
}
