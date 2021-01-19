package com.assignment.inventory.service;

import java.util.List;

import com.assignment.inventory.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployee(Long id);
	
	public Long addEmployee(Employee emp);
	
	public void deleteEmployee(Long id);
	
	public Employee updateEmployee(Employee emp);
	
}
