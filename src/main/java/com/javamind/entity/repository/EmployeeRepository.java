package com.javamind.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javamind.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
