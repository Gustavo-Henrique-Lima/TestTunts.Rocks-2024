package com.gustavonascimento.TesteEstagioDevTrainingProgram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.dto.StudentDTO;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@Operation(description = "Returns a list of all students", summary = "Find all students", responses = {
			@ApiResponse(description = "Ok", responseCode = "200") })
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<StudentDTO> entities = service.getAllStudents();
		return ResponseEntity.ok(entities);
	}
}