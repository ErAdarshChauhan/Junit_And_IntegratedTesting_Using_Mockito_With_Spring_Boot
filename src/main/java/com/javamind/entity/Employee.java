package com.javamind.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6137086918405276252L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String designation;
	private int age;
	
}
