package com.gustavonascimento.TesteEstagioDevTrainingProgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}