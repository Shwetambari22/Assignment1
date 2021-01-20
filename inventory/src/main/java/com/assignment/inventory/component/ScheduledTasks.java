package com.assignment.inventory.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.inventory.model.FileDetails;
import com.assignment.inventory.service.FileProcessingService;

@Component
public class ScheduledTasks {

	private static final long INTERVAL = 2 * 60 * 1000;
	private static final String STATUS_IN_PROCESSING = "IN PROCESSING";
	private static final String STATUS_COMPLETED = "COMPLETED";

	@Autowired
	private FileProcessingService fileProcessingService;

	@Scheduled(fixedRate = INTERVAL)
	public void scheduleFileProcessing() {

		FileDetails fileDetails = fileProcessingService.getUnProcessedFile();
		if (fileDetails != null) {
			fileProcessingService.updateStatus(fileDetails.getTaskId(), STATUS_IN_PROCESSING);
			fileProcessingService.readFile(fileDetails);
			fileProcessingService.updateStatus(fileDetails.getTaskId(), STATUS_COMPLETED);
		}

	}
}
