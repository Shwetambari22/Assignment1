package com.assignment.inventory.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.inventory.model.FileDetails;

public interface FileDetailsRepo extends JpaRepository<FileDetails, Long> {

	@Query(value = "select status from file_details where task_id = :taskId", nativeQuery = true)
	public Optional<String> getStatus(@Param("taskId") String taskId);

	@Query(value = "select * from file_details where status = 'NEW'", nativeQuery = true)
	public Optional<List<FileDetails>> getUnProcessedFile();

	@Transactional
	@Modifying
	@Query(value = "update file_details set status = :status where task_id = :taskId", nativeQuery = true)
	public void updateStatus(@Param("taskId") String taskId, @Param("status") String status);

}
