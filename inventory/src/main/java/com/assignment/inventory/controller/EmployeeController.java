package com.assignment.inventory.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.inventory.model.Employee;
import com.assignment.inventory.service.EmployeeService;
import com.mindstix.web.rest.baseline.common.model.ApiResponse;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all")
	public ApiResponse<List<Employee>> getAllEmployee() {
		ApiResponse<List<Employee>> apiResponse = new ApiResponse<List<Employee>>();
		apiResponse.setData(employeeService.getAllEmployee());
		apiResponse.setStatus(HttpStatus.OK.value());
		return apiResponse;
	}
	
	@GetMapping("/{id}")
	public ApiResponse<Employee> getEmployee(@PathVariable Long id) throws ParseException {
		ApiResponse<Employee> apiResponse = new ApiResponse<Employee>();
		apiResponse.setData(employeeService.getEmployee(id));
		apiResponse.setStatus(HttpStatus.OK.value());
		return apiResponse;
	}
	
	@PostMapping()
	public ApiResponse<Long> createEmployee(@Valid @RequestBody Employee emp) {
		ApiResponse<Long> apiResponse = new ApiResponse<Long>();
		apiResponse.setData(employeeService.addEmployee(emp));
		apiResponse.setStatus(HttpStatus.OK.value());
		return apiResponse;
	}
	
	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteEmployee(@PathVariable Long id) {
		ApiResponse<String> apiResponse = new ApiResponse<String>();
		employeeService.deleteEmployee(id);
		apiResponse.setData("Employee data is deleted for Id " + id + "!");
		apiResponse.setStatus(HttpStatus.OK.value());
		return apiResponse;
	}
	
	@PutMapping()
	public ApiResponse<Employee> updateEmployee(@Valid @RequestBody Employee emp) {
		ApiResponse<Employee> apiResponse = new ApiResponse<Employee>();
		apiResponse.setData(employeeService.updateEmployee(emp));
		apiResponse.setStatus(HttpStatus.OK.value());
		return apiResponse;
	}
	
}
