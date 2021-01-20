package com.assignment.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.assignment.inventory.dao.EmployeeRepo;
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
		}
		// throw exception
		return null;
	}

	@Override
	public Employee getEmployee(Long id) {
		Optional<Employee> empDataOpt = employeeRepo.findById(id);
		if (empDataOpt.isPresent()) {
			return empDataOpt.get();
		}
		// throw exception
		return null;
	}

	@Override
	public Long addEmployee(Employee emp) {
		employeeRepo.save(emp);
		System.out.println(emp.getId());
		return emp.getId();
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
		// handle case if id not present
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		employeeRepo.updateEmployee(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getAge());
		return getEmployee(emp.getId());
	}

	@Override
	public void saveAll(List empList) {
		employeeRepo.saveAll(empList);
	}

}
