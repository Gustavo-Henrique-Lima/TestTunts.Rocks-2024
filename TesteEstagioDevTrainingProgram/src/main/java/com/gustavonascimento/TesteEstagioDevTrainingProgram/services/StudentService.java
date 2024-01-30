package com.gustavonascimento.TesteEstagioDevTrainingProgram.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.dto.StudentDTO;
import com.gustavonascimento.TesteEstagioDevTrainingProgram.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	@Transactional(readOnly = true)
	public List<StudentDTO> getAllStudents(){
		List<Student> entities = repository.findAll();
		List<StudentDTO> entitiesDto = entities.stream().map(student->new StudentDTO(student)).collect(Collectors.toList());
		return entitiesDto;
	}
}
