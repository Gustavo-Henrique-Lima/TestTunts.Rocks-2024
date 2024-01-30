package com.gustavonascimento.TesteEstagioDevTrainingProgram;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.component.ConvertSheetDataToClass;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.repositories.StudentRepository;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.services.GoogleSheetsService;

@SpringBootApplication
public class TesteEstagioDevTrainingProgramApplication {
	
	/**
	 * The main application class for the TesteEstagioDevTrainingProgram.
	 * Initializes and runs the Spring Boot application, downloads spreadsheet data,
	 * converts it to Student entities, saves them to the database, updates the
	 * spreadsheet, and logs relevant information. Uses GoogleSheetsService and
	 * StudentRepository for data operations.
	 */

	private final GoogleSheetsService googleSheetsService;
	private final StudentRepository studentRepository;
	private static Logger logger = LoggerFactory.getLogger(TesteEstagioDevTrainingProgramApplication.class);
	private int totalClassesSemester;

	public TesteEstagioDevTrainingProgramApplication(GoogleSheetsService googleSheetsService,
			StudentRepository studentRepository) {
		this.googleSheetsService = googleSheetsService;
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(TesteEstagioDevTrainingProgramApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData() {
		return args -> {
			try {
				List<List<Object>> spreadsheetData = googleSheetsService.getSpreadsheetData();
				logger.info("Spreadsheet data downloaded successfully: " + spreadsheetData);
				totalClassesSemester = googleSheetsService.getTotalClassesSemester(spreadsheetData);
				List<Student> students = ConvertSheetDataToClass.convertToStudents(spreadsheetData,
						totalClassesSemester);
				studentRepository.saveAll(students);
				logger.info("Try uploaded data");
				googleSheetsService.updateSpreadsheet(students);
				logger.info("Spreadsheet data uploaded successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}

}