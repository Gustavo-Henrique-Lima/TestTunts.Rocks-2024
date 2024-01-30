package com.gustavonascimento.TesteEstagioDevTrainingProgram.entities;

import static org.mockito.ArgumentMatchers.startsWith;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTests {

	@Test
	public void finalApprovalGradeShouldBeThree() {
		Student student = new Student();
		Double expectedValue = 3.0;
		student.setPartialOne(6.0);
		student.setPartialTwo(5.0);
		student.setPartialThree(9.0);
		student.setAbsences(0);
		student.calculateSituation(60);
		Assertions.assertEquals(expectedValue, student.getFinalApprovalGrade());
	}
	
	@Test
	public void situationShouldBeReprovadoPorFalta() {
		Student student = new Student();
		String expectedValue = "Reprovado por Falta";
		student.setPartialOne(10.0);
		student.setPartialTwo(10.0);
		student.setPartialThree(9.0);
		student.setAbsences(16);
		student.calculateSituation(60);
		Assertions.assertEquals(expectedValue, student.getSituation());
	}
	
	@Test
	public void situationShouldBeExameFinalAndApprovalGradeShouldBeThree() {
		Student student = new Student();
		String expectedValue = "Exame Final";
		student.setPartialOne(6.0);
		student.setPartialTwo(5.0);
		student.setPartialThree(9.0);
		student.setAbsences(1);
		student.calculateSituation(60);
		Assertions.assertEquals(3, student.getFinalApprovalGrade());
		Assertions.assertEquals(expectedValue, student.getSituation());
	}
	
	@Test
	public void finalApprovalGradeShouldBeZero() {
		Student student = new Student();
		Double expectedValue = 0.0;
		student.setPartialOne(6.0);
		student.setPartialTwo(8.0);
		student.setPartialThree(9.0);
		student.setAbsences(1);
		student.calculateSituation(60);
		Assertions.assertEquals(expectedValue, student.getFinalApprovalGrade());
	}
	
	@Test
	public void finalApprovalGradeShouldBeFiveAndsituationShouldBeExameFinal() {
		Student student = new Student();
		Double expectedValue = 5.0;
		student.setPartialOne(5.0);
		student.setPartialTwo(7.0);
		student.setPartialThree(4.0);
		student.setAbsences(10);
		student.calculateSituation(60);
		Assertions.assertEquals("Exame Final", student.getSituation());
		Assertions.assertEquals(expectedValue, student.getFinalApprovalGrade());
	}
	
	@Test
	public void finalApprovalGradeShouldBeZeroAndsituationShouldBeAprovado() {
		Student student = new Student();
		Double expectedValue = 0.0;
		student.setPartialOne(10.0);
		student.setPartialTwo(7.0);
		student.setPartialThree(5.0);
		student.setAbsences(10);
		student.calculateSituation(60);
		Assertions.assertEquals("Aprovado", student.getSituation());
		Assertions.assertEquals(expectedValue, student.getFinalApprovalGrade());
	}
}