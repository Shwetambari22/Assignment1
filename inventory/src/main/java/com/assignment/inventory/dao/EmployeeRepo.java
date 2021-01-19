package com.assignment.inventory.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.inventory.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	@Transactional
	@Modifying
	@Query(value="update employee set first_name = :firstName, last_name = :lastName, age = :age where id = :id", nativeQuery = true)
	void updateEmployee(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, 
			@Param("age") int age);
		
}
