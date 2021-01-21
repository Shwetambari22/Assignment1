package com.assignment.inventory.controller.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.inventory.controller.EmployeeController;
import com.assignment.inventory.model.Employee;
import com.assignment.inventory.service.EmployeeService;
import com.mindstix.web.rest.baseline.common.model.ApiResponse;

@SpringBootTest
class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService employeeService;

	@SuppressWarnings("deprecation")
	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllEmployeeTest() {
		ApiResponse<List<Employee>> apiResponse = new ApiResponse<List<Employee>>();
		apiResponse.setData(employeeService.getAllEmployee());
		assertNotNull(apiResponse);
	}

	@Test
	public void getEmployeeByIdTest() {
		ApiResponse<Employee> apiResponse = new ApiResponse<Employee>();
		apiResponse.setData(employeeService.getEmployee(1L));
		assertNotNull(apiResponse);
	}

}
