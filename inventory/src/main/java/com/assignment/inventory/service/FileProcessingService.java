package com.assignment.inventory.service;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.inventory.model.FileDetails;

public interface FileProcessingService {

	public String uploadFile(String name, MultipartFile file);

	public String getProcessingStatus(String taskId);

	public FileDetails getUnProcessedFile();

	public void updateStatus(String taskId, String status);

	public void readFile(FileDetails fileDetails);

}
