package com.assignment.inventory.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.inventory.dao.EmployeeRepo;
import com.assignment.inventory.model.Employee;
import com.assignment.inventory.service.impl.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeServiceImpl employeeservice;

	@Mock
	EmployeeRepo employeeRepo;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllEmployeeTest() {
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp1 = new Employee();
		emp1.setId(1L);
		emp1.setFirstName("Rahul");
		emp1.setLastName("Dravid");
		emp1.setAge(40);
		empList.add(emp1);

		Employee emp2 = new Employee();
		emp2.setId(1L);
		emp2.setFirstName("Sachin");
		emp2.setAge(45);
		empList.add(emp2);

		Mockito.when(employeeRepo.findAll()).thenReturn(empList);
		List<Employee> returnValue = employeeservice.getAllEmployee();
		assertEquals(returnValue, empList);
	}

	@Test
	public void getEmployeeByIdTest() {
		Employee emp1 = new Employee();
		emp1.setId(1L);
		emp1.setFirstName("Rahul");
		emp1.setLastName("Dravid");
		emp1.setAge(40);

		Optional<Employee> empOpt = Optional.of(emp1);
		Mockito.when(employeeRepo.findById(1L)).thenReturn(empOpt);
		Employee returnValue = employeeservice.getEmployee(1L);
		assertEquals(returnValue, emp1);
	}

}
