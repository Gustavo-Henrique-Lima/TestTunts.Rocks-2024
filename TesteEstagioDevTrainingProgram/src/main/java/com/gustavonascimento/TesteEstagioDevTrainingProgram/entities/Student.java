package com.gustavonascimento.TesteEstagioDevTrainingProgram.entities;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_student")
public class Student implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(Student.class);

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String name;
	private Integer absences;
	private Double partialOne;
	private Double partialTwo;
	private Double partialThree;
	private String situation;
	private Double finalApprovalGrade;

	public Student() {
	}

	public Student(Long id, String name, Integer absences, Double partialOne, Double partialTwo, Double partialThree,
			String situation, Double finalApprovalGrade) {
		this.id = id;
		this.name = name;
		this.absences = absences;
		this.partialOne = partialOne;
		this.partialTwo = partialTwo;
		this.partialThree = partialThree;
		this.situation = situation;
		this.finalApprovalGrade = finalApprovalGrade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAbsences() {
		return absences;
	}

	public void setAbsences(Integer absences) {
		this.absences = absences;
	}

	public Double getPartialOne() {
		return partialOne;
	}

	public void setPartialOne(Double partialOne) {
		this.partialOne = partialOne;
	}

	public Double getPartialTwo() {
		return partialTwo;
	}

	public void setPartialTwo(Double partialTwo) {
		this.partialTwo = partialTwo;
	}

	public Double getPartialThree() {
		return partialThree;
	}

	public void setPartialThree(Double partialThree) {
		this.partialThree = partialThree;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Double getFinalApprovalGrade() {
		return finalApprovalGrade;
	}

	public void setFinalApprovalGrade(Double finalApprovalGrade) {
		this.finalApprovalGrade = finalApprovalGrade;
	}

	public void calculateSituation(int totalClasses) {
		logger.info("Calculating Situation for the Student: " + this.name);
		double average = (this.partialOne + this.partialTwo + this.partialThree) / 3;
		int attendanceThreshold = (int) (totalClasses * 0.25);
		if (absences > attendanceThreshold) {
			this.finalApprovalGrade = 0.0;
			this.situation = "Reprovado por Falta";
		} else {
			if (average < 5) {
				this.finalApprovalGrade = 0.0;
				this.situation = "Reprovado por Nota";
			} else if (average < 7) {
				this.situation = "Exame Final";
				calculateFinalApprovalGrade(average);
			} else {
				situation = "Aprovado";
				this.finalApprovalGrade = 0.0;
			}
		}

		logger.info("Result of situation for the student " + name + " situation: " + situation);
	}

	private void calculateFinalApprovalGrade(double average) {
		logger.info("The student: " + name + " goes to the final exam.");
		/*
		 * 5 <= (m + naf)/2
		 *  2*5 <=mnaf
		 *   10 <= mnaf (m - 10<=naf)*-1
		 */
		double naf = Math.round(((5 * 2) - average) * 1);
		this.finalApprovalGrade = naf;
		logger.info("He needs: " + this.finalApprovalGrade + " to pass.");
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id);
	}

}