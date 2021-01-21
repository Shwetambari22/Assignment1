package com.assignment.inventory.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.inventory.dao.FileDetailsRepo;
import com.assignment.inventory.exception.FileProcessingException;
import com.assignment.inventory.model.Employee;
import com.assignment.inventory.model.FileDetails;
import com.assignment.inventory.service.EmployeeService;
import com.assignment.inventory.service.FileProcessingService;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

	private static final String DIRECTORY = "tempFiles";

	@Value("${root_path}")
	private String rootPath;

	@Autowired
	private FileDetailsRepo fileDtlsRepo;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public String uploadFile(String name, MultipartFile file) {
		String taskId = "";
		try {
			byte[] bytes = file.getBytes();

			// Creating the directory to store file
			File dir = new File(rootPath + File.separator + DIRECTORY);
			if (!dir.exists())
				dir.mkdirs();

			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			// create task id
			taskId = UUID.randomUUID().toString();
			addNewTask(taskId, name);

		} catch (Exception e) {
			throw new FileProcessingException("Failed to upload file " + name + " => " + e.getMessage());
		}
		return taskId;
	}

	private void addNewTask(String taskId, String name) {
		FileDetails fileDtls = new FileDetails();
		fileDtls.setFileName(name);
		fileDtls.setTaskId(taskId);
		fileDtls.setStatus("NEW");

		fileDtlsRepo.save(fileDtls);
	}

	@Override
	public String getProcessingStatus(String taskId) {
		Optional<String> statusOpt = fileDtlsRepo.getStatus(taskId);
		if (statusOpt.isPresent()) {
			return statusOpt.get();
		} else {
			throw new FileProcessingException("Invalid task id: " + taskId);
		}
	}

	@Override
	public FileDetails getUnProcessedFile() {
		List<FileDetails> fileDetails;
		Optional<List<FileDetails>> fileDetailsOpt = fileDtlsRepo.getUnProcessedFile();
		if (!fileDetailsOpt.isEmpty()) {
			fileDetails = fileDetailsOpt.get();
			if (!CollectionUtils.isEmpty(fileDetails)) {
				return fileDetails.get(0);
			}
		}
		return null;
	}

	@Override
	public void updateStatus(String taskId, String status) {
		fileDtlsRepo.updateStatus(taskId, status);
	}

	@Override
	public void readFile(FileDetails fileDetails) {
		File file = FileUtils
				.getFile(rootPath + File.separator + DIRECTORY + File.separator + fileDetails.getFileName());
		List<Employee> empList = new ArrayList<Employee>();
		int count = 1;
		try (LineIterator lineIterator = FileUtils.lineIterator(file, "UTF-8")) {
			while (lineIterator.hasNext()) {
				if (count > 10) {
					count = 1;
					empList.clear();
				}
				String line = lineIterator.nextLine();
				String fields[] = line.split(" ");
				empList.add(getEmployeeObject(fields));

				if (!lineIterator.hasNext() || count == 10) {
					employeeService.saveAll(empList);
				}
				count++;
			}
		} catch (IOException e) {
			throw new FileProcessingException("Failed to read file" + fileDetails.getFileName());
		}
	}

	private Employee getEmployeeObject(String[] fields) {
		Employee emp = new Employee();
		emp.setFirstName(fields[0]);
		if (fields.length == 2) {
			emp.setAge(Integer.parseInt(fields[1]));
		} else {
			emp.setLastName(fields[1]);
			emp.setAge(Integer.parseInt(fields[2]));
		}
		return emp;
	}

}
