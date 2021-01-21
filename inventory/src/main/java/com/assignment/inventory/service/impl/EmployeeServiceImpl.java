package com.assignment.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment.inventory.dao.EmployeeRepo;
import com.assignment.inventory.exception.EmployeeNotFoundException;
import com.assignment.inventory.model.Employee;
import com.assignment.inventory.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = employeeRepo.findAll();
		if (!CollectionUtils.isEmpty(empList)) {
			return empList;
		} else {
			throw new EmployeeNotFoundException("Employee list empty!");
		}
	}

	@Override
	public Employee getEmployee(Long id) {
		Optional<Employee> empDataOpt = employeeRepo.findById(id);
		if (empDataOpt.isPresent()) {
			return empDataOpt.get();
		}else {
			throw new EmployeeNotFoundException("Employee not found with id " + id);
		}
	}

	@Override
	public Long addEmployee(Employee emp) {
		if(emp == null) {
			throw new EmployeeNotFoundException("Invaid employee data!");
		}
		employeeRepo.save(emp);
		return emp.getId();
	}

	@Override
	public void deleteEmployee(Long id) {
		if(getEmployee(id) != null) {
			employeeRepo.deleteById(id);
		} else {
			throw new EmployeeNotFoundException("Employee not found with id " + id);
		}
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		if(emp == null) {
			throw new EmployeeNotFoundException("Invaid employee data!");
		}
		if(getEmployee(emp.getId()) != null) {
			employeeRepo.updateEmployee(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getAge());
			return getEmployee(emp.getId());
		} else {
			throw new EmployeeNotFoundException("Employee not found with id " + emp.getId());
		}
	}

	@Override
	public void saveAll(List empList) {
		employeeRepo.saveAll(empList);
	}

}
